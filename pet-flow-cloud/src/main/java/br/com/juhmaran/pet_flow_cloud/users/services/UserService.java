package br.com.juhmaran.pet_flow_cloud.users.services;

import br.com.juhmaran.pet_flow_cloud.utils.constants.ApplicationConstants;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse createUser(@Valid UserRequest userRequest) {

        log.debug("Creating user with email: {}", userRequest.getEmail());

        if (!userRequest.getPassword().equals(userRequest.getConfirmPassword())) {
            log.error("Password and confirm password do not match for email: {}", userRequest.getEmail());
            throw new BadRequestException(ApplicationConstants.PASSWORDS_DO_NOT_MATCH);
        }

        if (userRepository.existsByCpf(userRequest.getCpf())) {
            log.error("CPF already exists: {}", userRequest.getCpf());
            throw new ConflictException(ApplicationConstants.CPF_ALREADY_EXISTS);
        }

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            log.error("Email already exists: {}", userRequest.getEmail());
            throw new ConflictException(ApplicationConstants.EMAIL_ALREADY_EXISTS);
        }

        User user = userMapper.toEntity(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setStatus(UserStatus.ACTIVE);
        user.getRoles().add(new Role(RoleType.USER, "Usuário padrão"));

        User savedUser = userRepository.save(user);
        log.info("User created successfully with ID: {}", savedUser.getId());
        return userMapper.toResponse(savedUser);
    }

    public String updateUser(Long id, @Valid UserUpdateRequest userUpdateRequest) {
        log.debug("Updating user with ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("User not found with ID: {}", id);
                    return new ResourceNotFoundException(ApplicationConstants.USER_NOT_FOUND);
                });

        Optional.ofNullable(userUpdateRequest.getName()).ifPresent(user::setName);
        Optional.ofNullable(userUpdateRequest.getCpf()).ifPresent(user::setCpf);
        Optional.ofNullable(userUpdateRequest.getEmail()).ifPresent(user::setEmail);

        User updatedUser = userRepository.save(user);
        log.info("User updated successfully with ID: {}", updatedUser.getId());
        return "Usuário atualizado com sucesso.";
    }

    public String changePassword(Long id, @Valid ChangePassword changePassword) {
        log.debug("Changing password for user with ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("User not found with ID: {}", id);
                    return new ResourceNotFoundException(ApplicationConstants.USER_NOT_FOUND);
                });

        if (!passwordEncoder.matches(changePassword.getCurrentPassword(), user.getPassword())) {
            log.error("Current password does not match for user ID: {}", id);
            throw new BadRequestException(ApplicationConstants.CURRENT_PASSWORD_INVALID);
        }

        if (!changePassword.getNewPassword().equals(changePassword.getConfirmPassword())) {
            log.error("New password and confirm password do not match for user ID: {}", id);
            throw new BadRequestException(ApplicationConstants.PASSWORDS_DO_NOT_MATCH);
        }

        user.setPassword(passwordEncoder.encode(changePassword.getNewPassword()));
        userRepository.save(user);

        log.info("Password changed successfully for user with ID: {}", id);
        return "Senha atualizada com sucesso.";
    }

    public UserResponse getUserById(Long id) {
        log.debug("Fetching user with ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("User not found with ID: {}", id);
                    return new ResourceNotFoundException(ApplicationConstants.USER_NOT_FOUND);
                });

        return userMapper.toResponse(user);
    }

    public List<UserResponse> getAllUsers() {
        log.debug("Fetching all users");

        List<User> users = userRepository.findAll();
        return userMapper.toResponseList(users);
    }

    public Page<UserResponse> searchUsers(String name, String cpf, String email, int page, int size) {
        log.debug("Searching users with name: {}, cpf: {}, email: {} (page: {}, size: {})", name, cpf, email, page, size);

        Pageable pageable = PageRequest.of(page, size);
        Page<User> usersPage = userRepository
                .findByNameContainingAndCpfContainingAndEmailContaining(name, cpf, email, pageable);
        return usersPage.map(userMapper::toResponse);
    }

    public String deleteUser(Long id) {
        log.debug("Deleting user with ID: {}", id);

        if (!userRepository.existsById(id)) {
            log.error("User not found with ID: {}", id);
            throw new ResourceNotFoundException(ApplicationConstants.USER_NOT_FOUND);
        }
        userRepository.deleteById(id);
        log.info("User deleted successfully with ID: {}", id);
        return "Usuário excluído com sucesso.";
    }

}