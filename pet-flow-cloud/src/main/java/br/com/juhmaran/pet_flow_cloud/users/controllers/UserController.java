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
        log.debug("Received request to create user with email: {}", userRequest.getEmail());

        UserResponse userResponse = userService.createUser(userRequest);

        log.info("User created successfully with ID: {}", userResponse.getId());
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id,
                                             @RequestBody @Valid UserUpdateRequest userUpdateRequest) {
        log.debug("Received request to update user with ID: {}", id);
        log.debug("User update details: {}", userUpdateRequest);

        String message = userService.updateUser(id, userUpdateRequest);

        log.info("User updated successfully with ID: {}", id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/{id}/change-password")
    public ResponseEntity<String> changePassword(@PathVariable Long id,
                                                 @Valid @RequestBody ChangePassword changePassword) {
        log.debug("Received request to change password for user with ID: {}", id);
        log.debug("Change password details: {}", changePassword);

        String message = userService.changePassword(id, changePassword);

        log.info("Password changed successfully for user with ID: {}", id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        log.debug("Received request to get user by ID: {}", id);

        UserResponse userResponse = userService.getUserById(id);

        log.info("User retrieved successfully with ID: {}", id);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        log.debug("Received request to get all users");

        List<UserResponse> userResponses = userService.getAllUsers();

        log.info("Retrieved {} users", userResponses.size());
        return new ResponseEntity<>(userResponses, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<UserResponse>> searchUsers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String cpf,
            @RequestParam(required = false) String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.debug("Received request to search users with name: {}, cpf: {}, email: {} (page: {}, size: {})",
                name, cpf, email, page, size);

        Page<UserResponse> userResponses = userService.searchUsers(name, cpf, email, page, size);

        log.info("Found {} users matching search criteria", userResponses.getTotalElements());
        return new ResponseEntity<>(userResponses, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        log.debug("Received request to delete user with ID: {}", id);

        String message = userService.deleteUser(id);

        log.info("User deleted successfully with ID: {}", id);
        return new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
    }

}
