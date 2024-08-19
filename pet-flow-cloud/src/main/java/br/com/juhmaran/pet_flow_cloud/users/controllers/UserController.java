package br.com.juhmaran.pet_flow_cloud.users.controllers;

import br.com.juhmaran.pet_flow_cloud.users.dto.request.ChangePassword;
import br.com.juhmaran.pet_flow_cloud.users.dto.request.UserRequest;
import br.com.juhmaran.pet_flow_cloud.users.dto.request.UserUpdateRequest;
import br.com.juhmaran.pet_flow_cloud.users.dto.response.UserResponse;
import br.com.juhmaran.pet_flow_cloud.users.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) {
        log.debug("### [UserController] - Received request to create user with email: {}", userRequest.getEmail());

        UserResponse userResponse = userService.createUser(userRequest);

        log.info("### [UserController] - User created successfully with ID: {}", userResponse.getId());
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> updateUser(@PathVariable Long id,
                                             @RequestBody @Valid UserUpdateRequest userUpdateRequest) {
        log.debug("### [UserController] - Received request to update user with ID: {}", id);
        log.debug("### [UserController] - User update details: {}", userUpdateRequest);

        String message = userService.updateUser(id, userUpdateRequest);

        log.info("### [UserController] - User updated successfully with ID: {}", id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/{id}/change-password")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> changePassword(@PathVariable Long id,
                                                 @Valid @RequestBody ChangePassword changePassword) {
        log.debug("### [UserController] - Received request to change password for user with ID: {}", id);
        log.debug("### [UserController] - Change password details: {}", changePassword);

        String message = userService.changePassword(id, changePassword);

        log.info("### [UserController] - Password changed successfully for user with ID: {}", id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        log.debug("### [UserController] - Received request to get user by ID: {}", id);

        UserResponse userResponse = userService.getUserById(id);

        log.info("### [UserController] - User retrieved successfully with ID: {}", id);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'OWNER')")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        log.debug("### [UserController] - Received request to get all users");

        List<UserResponse> userResponses = userService.getAllUsers();

        log.info("### [UserController] - Retrieved {} users", userResponses.size());
        return new ResponseEntity<>(userResponses, HttpStatus.OK);
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN', 'OWNER')")
    public ResponseEntity<Page<UserResponse>> searchUsers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String cpf,
            @RequestParam(required = false) String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.debug("### [UserController] - Received request to search users with name: {}, " +
                        "cpf: {}, email: {} (page: {}, size: {})",
                name, cpf, email, page, size);

        Page<UserResponse> userResponses = userService.searchUsers(name, cpf, email, page, size);

        log.info("### [UserController] - Found {} users matching search criteria", userResponses.getTotalElements());
        return new ResponseEntity<>(userResponses, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        log.debug("### [UserController] - Received request to delete user with ID: {}", id);

        String message = userService.deleteUser(id);

        log.info("### [UserController] - User deleted successfully with ID: {}", id);
        return new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
    }

}
