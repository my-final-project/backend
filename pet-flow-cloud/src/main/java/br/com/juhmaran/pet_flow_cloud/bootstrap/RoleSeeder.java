package br.com.juhmaran.pet_flow_cloud.bootstrap;

import br.com.juhmaran.pet_flow_cloud.exceptions.RoleCreationException;
import br.com.juhmaran.pet_flow_cloud.roles.entities.Role;
import br.com.juhmaran.pet_flow_cloud.roles.entities.RoleType;
import br.com.juhmaran.pet_flow_cloud.roles.repositories.RoleRepository;
import br.com.juhmaran.pet_flow_cloud.utils.enums.EnumUtils;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
@AllArgsConstructor
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final MessageSource messageSource;
    private final RoleRepository roleRepository;

    @Override
    public void onApplicationEvent(@Nonnull ContextRefreshedEvent event) {
        seedRoles();
    }

    private void seedRoles() {
        try {
            Arrays.stream(RoleType.values()).forEach(roleType -> {
                if (roleRepository.findByName(roleType).isEmpty()) {
                    String description = EnumUtils.getDescription(roleType, messageSource);
                    Role role = Role.builder()
                            .name(roleType)
                            .description(description)
                            .build();
                    roleRepository.save(role);
                    log.info("[Role Seeder] - Role {} created successfully.", roleType);
                }
            });
        } catch (Exception ex) {
            log.error("[Role Seeder] - Error creating roles: {}", ex.getMessage(), ex);
            String errorMessage = messageSource.getMessage("error.creating.roles",
                    new Object[]{ex.getMessage()}, LocaleContextHolder.getLocale());
            throw new RoleCreationException(errorMessage);
        }
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }

}
