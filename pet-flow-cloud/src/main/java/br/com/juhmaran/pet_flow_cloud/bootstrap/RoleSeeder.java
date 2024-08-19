package br.com.juhmaran.pet_flow_cloud.bootstrap;

import br.com.juhmaran.pet_flow_cloud.roles.entities.Role;
import br.com.juhmaran.pet_flow_cloud.roles.entities.RoleType;
import br.com.juhmaran.pet_flow_cloud.roles.repositories.RoleRepository;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;

@Slf4j
@Component
@AllArgsConstructor
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepository roleRepository;

    @Override
    public void onApplicationEvent(@Nonnull ContextRefreshedEvent event) {
        this.seedRoles();
    }

    private void seedRoles() {
        log.info("### [RoleSeeder] - Starting role seeding...");

        Map<RoleType, String> roleDescriptionMap = getRoleDescriptions();

        Arrays.stream(RoleType.values()).forEach(roleType ->
                roleRepository.findByName(roleType).ifPresentOrElse(
                        existingRole -> log.info("### [RoleSeeder] - Role '{}' already exists: {}", roleType, existingRole),
                        () -> createAndSaveRole(roleType, roleDescriptionMap.get(roleType))
                )
        );

        log.info("### [RoleSeeder] - Role seeding completed.");
    }

    private Map<RoleType, String> getRoleDescriptions() {
        return Map.of(
                RoleType.ADMIN, "System Administrator.",
                RoleType.USER, "Standard User.",
                RoleType.OWNER, "PetShop Owner.",
                RoleType.EMPLOYEE, "PetShop Employee.",
                RoleType.VETERINARIAN, "Veterinarian."
        );
    }

    private void createAndSaveRole(RoleType roleType, String description) {
        Role newRole = new Role();
        newRole.setName(roleType);
        newRole.setDescription(description);
        Role savedRole = roleRepository.save(newRole);
        log.info("### [RoleSeeder] - Role '{}' created successfully: {}", roleType, savedRole);
    }

}