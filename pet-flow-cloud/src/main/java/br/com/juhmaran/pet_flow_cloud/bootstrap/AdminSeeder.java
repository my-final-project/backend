package br.com.juhmaran.pet_flow_cloud.bootstrap;

import br.com.juhmaran.pet_flow_cloud.roles.entities.Role;
import br.com.juhmaran.pet_flow_cloud.roles.entities.RoleType;
import br.com.juhmaran.pet_flow_cloud.roles.repositories.RoleRepository;
import br.com.juhmaran.pet_flow_cloud.users.dto.request.UserRequest;
import br.com.juhmaran.pet_flow_cloud.users.entities.User;
import br.com.juhmaran.pet_flow_cloud.users.entities.UserStatus;
import br.com.juhmaran.pet_flow_cloud.users.repositories.UserRepository;
import br.com.juhmaran.pet_flow_cloud.utils.constants.ApplicationConstants;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;

@Slf4j
@Component
@AllArgsConstructor
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(@Nonnull ContextRefreshedEvent event) {
        seedAdminUser();
    }

    private void seedAdminUser() {
        log.info("### [AdminSeeder] - Starting admin user seeding...");

        if (isAdminUserAlreadyExists()) {
            return;
        }

        Role adminRole = roleRepository.findByName(RoleType.ADMIN)
                .orElseThrow(() -> {
                    log.error("### [AdminSeeder] - Admin role '{}' not found. Unable to create admin user.",
                            RoleType.ADMIN);
                    return new ServiceException("Admin role not found");
                });

        UserRequest adminRequest = createAdminRequest();
        User adminUser = createAdminUser(adminRequest, adminRole);

        userRepository.save(adminUser);
        log.info("### [AdminSeeder] - Admin user created successfully with email '{}'", adminRequest.getEmail());
    }

    private boolean isAdminUserAlreadyExists() {
        if (userRepository.existsByRoleName(RoleType.ADMIN)) {
            log.info("### [AdminSeeder] - Admin user with role '{}' already exists. Skipping seeding.",
                    RoleType.ADMIN);
            return true;
        }

        if (userRepository.existsByEmail(ApplicationConstants.ADMIN_EMAIL)) {
            log.info("### [AdminSeeder] - Admin user with email '{}' already exists. Skipping seeding.",
                    ApplicationConstants.ADMIN_EMAIL);
            return true;
        }

        if (userRepository.existsByCpf(ApplicationConstants.ADMIN_CPF)) {
            log.info("### [AdminSeeder] - Admin user with CPF '{}' already exists. Skipping seeding.",
                    ApplicationConstants.ADMIN_CPF);
            return true;
        }

        return false;
    }

    private UserRequest createAdminRequest() {
        return UserRequest.builder()
                .name("Admin")
                .cpf(ApplicationConstants.ADMIN_CPF)
                .email(ApplicationConstants.ADMIN_EMAIL)
                .password(passwordEncoder.encode(ApplicationConstants.ADMIN_PASSWORD))
                .build();
    }

    private User createAdminUser(UserRequest adminRequest, Role adminRole) {
        return User.builder()
                .name(adminRequest.getName())
                .cpf(adminRequest.getCpf())
                .email(adminRequest.getEmail())
                .password(adminRequest.getPassword())
                .status(UserStatus.ACTIVE)
                .roles(new HashSet<>(Collections.singletonList(adminRole)))
                .build();
    }

}