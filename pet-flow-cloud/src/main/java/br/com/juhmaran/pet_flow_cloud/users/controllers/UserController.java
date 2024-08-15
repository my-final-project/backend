package br.com.juhmaran.pet_flow_cloud.users.controllers;

import br.com.juhmaran.pet_flow_cloud.users.dto.UserRequest;
import br.com.juhmaran.pet_flow_cloud.users.dto.UserResponse;
import br.com.juhmaran.pet_flow_cloud.users.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRequest userRequest) {
        UserResponse response = userService.registerUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
