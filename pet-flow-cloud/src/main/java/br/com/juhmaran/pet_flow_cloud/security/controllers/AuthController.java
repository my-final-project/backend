package br.com.juhmaran.pet_flow_cloud.security.controllers;

import br.com.juhmaran.pet_flow_cloud.security.dto.Login;
import br.com.juhmaran.pet_flow_cloud.security.dto.TokenResponse;
import br.com.juhmaran.pet_flow_cloud.security.dto.UserDetailsImpl;
import br.com.juhmaran.pet_flow_cloud.security.services.AuthService;
import br.com.juhmaran.pet_flow_cloud.users.dto.response.UserResponse;
import br.com.juhmaran.pet_flow_cloud.users.mapping.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody @Valid Login loginRequest) {
        TokenResponse tokenResponse = authService.authenticate(loginRequest);
        return ResponseEntity.ok(tokenResponse);
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserResponse> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        UserResponse response = UserMapper.INSTANCE.toResponse(userDetails.getUser());
        return ResponseEntity.ok(response);
    }

}
