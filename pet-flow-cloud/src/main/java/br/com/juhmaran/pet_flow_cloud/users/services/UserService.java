package br.com.juhmaran.pet_flow_cloud.users.services;

import br.com.juhmaran.pet_flow_cloud.exceptions.BadRequestException;
import br.com.juhmaran.pet_flow_cloud.exceptions.ConflictException;
import br.com.juhmaran.pet_flow_cloud.exceptions.ResourceNotFoundException;
import br.com.juhmaran.pet_flow_cloud.roles.entities.Role;
import br.com.juhmaran.pet_flow_cloud.roles.entities.RoleType;
import br.com.juhmaran.pet_flow_cloud.users.dto.request.ChangePassword;
import br.com.juhmaran.pet_flow_cloud.users.dto.request.UserRequest;
import br.com.juhmaran.pet_flow_cloud.users.dto.request.UserUpdateRequest;
import br.com.juhmaran.pet_flow_cloud.users.dto.response.UserResponse;
import br.com.juhmaran.pet_flow_cloud.users.entities.User;
import br.com.juhmaran.pet_flow_cloud.users.entities.UserStatus;
import br.com.juhmaran.pet_flow_cloud.users.mapping.UserMapper;
import br.com.juhmaran.pet_flow_cloud.users.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final MessageSource messageSource;

    public UserResponse createUser(@Valid UserRequest userRequest) {

        log.debug("### [UserService] - Creating user with email: {}", userRequest.getEmail());

        if (!userRequest.getPassword().equals(userRequest.getConfirmPassword())) {
            log.error("### [UserService] - Password and confirm password do not match for email: {}",
                    userRequest.getEmail());
            throw new BadRequestException(
                    messageSource.getMessage("error.passwords_do_not_match", null, Locale.getDefault()));
        }

        if (userRepository.existsByCpf(userRequest.getCpf())) {
            log.error("### [UserService] - CPF already exists: {}", userRequest.getCpf());
            throw new ConflictException(
                    messageSource.getMessage("error.cpf_already_exists", null, Locale.getDefault()));
        }

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            log.error("### [UserService] - Email already exists: {}", userRequest.getEmail());
            throw new ConflictException(
                    messageSource.getMessage("error.email_already_exists", null, Locale.getDefault()));
        }

        User user = userMapper.toEntity(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setStatus(UserStatus.ACTIVE);
        user.getRoles().add(new Role(RoleType.USER, "Usuário padrão"));

        User savedUser = userRepository.save(user);
        log.info("### [UserService] - User created successfully with ID: {}", savedUser.getId());
        return userMapper.toResponse(savedUser);
    }

    public String updateUser(Long id, @Valid UserUpdateRequest userUpdateRequest) {
        log.debug("### [UserService] - Updating user with ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("### [UserService] - User not found with ID: {}", id);
                    return new ResourceNotFoundException(
                            messageSource.getMessage("user.not.found", new Object[]{id}, Locale.getDefault()));
                });

        Optional.ofNullable(userUpdateRequest.getName()).ifPresent(user::setName);
        Optional.ofNullable(userUpdateRequest.getCpf()).ifPresent(user::setCpf);
        Optional.ofNullable(userUpdateRequest.getEmail()).ifPresent(user::setEmail);

        User updatedUser = userRepository.save(user);
        log.info("### [UserService] - User updated successfully with ID: {}", updatedUser.getId());

        return messageSource.getMessage("user.updated.success",
                new Object[]{updatedUser.getId()}, Locale.getDefault());
    }

    public String changePassword(Long id, @Valid ChangePassword changePassword) {
        log.debug("### [UserService] - Changing password for user with ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("### [UserService] - User not found with ID: {}", id);
                    return new ResourceNotFoundException(
                            messageSource.getMessage("user.not.found", new Object[]{id}, Locale.getDefault()));
                });

        if (!passwordEncoder.matches(changePassword.getCurrentPassword(), user.getPassword())) {
            log.error("### [UserService] - Current password does not match for user ID: {}", id);
            throw new BadRequestException(
                    messageSource.getMessage("error.current_password_invalid", null, Locale.getDefault()));
        }

        if (!changePassword.getNewPassword().equals(changePassword.getConfirmPassword())) {
            log.error("### [UserService] - New password and confirm password do not match for user ID: {}", id);
            throw new BadRequestException(
                    messageSource.getMessage("error.passwords_do_not_match", null, Locale.getDefault()));
        }

        user.setPassword(passwordEncoder.encode(changePassword.getNewPassword()));
        userRepository.save(user);

        log.info("### [UserService] - Password changed successfully for user with ID: {}", id);
        return messageSource.getMessage("user.password.updated.success", new Object[]{id}, Locale.getDefault());
    }

    public UserResponse getUserById(Long id) {
        log.debug("### [UserService] - Fetching user with ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("### [UserService] - User not found with ID: {}", id);
                    return new ResourceNotFoundException(
                            messageSource.getMessage("user.not.found", new Object[]{id}, Locale.getDefault()));
                });

        return userMapper.toResponse(user);
    }

    public List<UserResponse> getAllUsers() {
        log.debug("### [UserService] - Fetching all users");

        List<User> users = userRepository.findAll();
        return userMapper.toResponseList(users);
    }

    public Page<UserResponse> searchUsers(String name, String cpf, String email, int page, int size) {
        log.debug("### [UserService] - Searching users with name: {}, cpf: {}, email: {} (page: {}, size: {})",
                name, cpf, email, page, size);

        Pageable pageable = PageRequest.of(page, size);
        Page<User> usersPage = userRepository
                .findByNameContainingAndCpfContainingAndEmailContaining(name, cpf, email, pageable);
        return usersPage.map(userMapper::toResponse);
    }

    public String deleteUser(Long id) {
        log.debug("### [UserService] - Deleting user with ID: {}", id);

        if (!userRepository.existsById(id)) {
            log.error("### [UserService] - User not found with ID: {}", id);
            throw new ResourceNotFoundException(
                    messageSource.getMessage("user.not.found", new Object[]{id}, Locale.getDefault()));
        }
        userRepository.deleteById(id);
        log.info("### [UserService] - User deleted successfully with ID: {}", id);

        // Retorna a mensagem de sucesso
        return messageSource.getMessage("user.deleted.success", new Object[]{id}, Locale.getDefault());
    }

}