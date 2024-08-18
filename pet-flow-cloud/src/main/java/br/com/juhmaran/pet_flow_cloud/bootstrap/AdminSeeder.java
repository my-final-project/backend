package br.com.juhmaran.pet_flow_cloud.bootstrap;

import br.com.juhmaran.pet_flow_cloud.exceptions.BadRequestException;
import br.com.juhmaran.pet_flow_cloud.roles.entities.Role;
import br.com.juhmaran.pet_flow_cloud.roles.entities.RoleType;
import br.com.juhmaran.pet_flow_cloud.roles.repositories.RoleRepository;
import br.com.juhmaran.pet_flow_cloud.users.entities.User;
import br.com.juhmaran.pet_flow_cloud.users.entities.UserStatus;
import br.com.juhmaran.pet_flow_cloud.users.repositories.UserRepository;
import br.com.juhmaran.pet_flow_cloud.utils.constants.ApplicationConstants;
import br.com.juhmaran.pet_flow_cloud.utils.enums.EnumUtils;
import jakarta.annotation.Nonnull;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final MessageSource messageSource;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(@Nonnull ContextRefreshedEvent event) {
        seedAdminUser();
    }

    private void seedAdminUser() {

        String adminDescription = EnumUtils.getDescription(RoleType.ADMIN, messageSource);
        String adminEmail = ApplicationConstants.ADMIN_EMAIL;
        String adminCpf = ApplicationConstants.ADMIN_CPF;
        String adminPassword = ApplicationConstants.ADMIN_PASSWORD;

        if (userRepository.existsByEmail(adminEmail)) {
            log.info("### [Admin Seeder] - Admin user with email {} already exists.", adminEmail);
            return;
        }

        if (userRepository.existsByCpf(adminCpf)) {
            log.info("### [Admin Seeder] - Admin user with CPF {} already exists.", adminCpf);
            return;
        }

        User adminUser = User.builder()
                .name("Admin")
                .cpf(adminCpf)
                .email(adminEmail)
                .password(passwordEncoder.encode(adminPassword))
                .status(UserStatus.ACTIVE)
                .roles(new HashSet<>())
                .build();

        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<User>> violations = validator.validate(adminUser);

            if (!violations.isEmpty()) {
                String errorMessage = violations.stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.joining(", "));
                log.error("### [Admin Seeder] - Validation errors: {}", errorMessage);
                throw new BadRequestException(messageSource.getMessage("error.validation",
                        new Object[]{errorMessage}, LocaleContextHolder.getLocale()));
            }
        }

        Role adminRole = roleRepository.findByName(RoleType.ADMIN)
                .orElseGet(() -> roleRepository.save(Role.builder()
                        .name(RoleType.ADMIN)
                        .description(adminDescription)
                        .build()));

        adminUser.getRoles().add(adminRole);

        userRepository.save(adminUser);
        log.info("### [Admin Seeder] - Admin user created successfully.");

    }

}
