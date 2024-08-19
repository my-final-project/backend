package br.com.juhmaran.pet_flow_cloud.security.services;

import br.com.juhmaran.pet_flow_cloud.security.dto.Login;
import br.com.juhmaran.pet_flow_cloud.security.dto.TokenResponse;
import br.com.juhmaran.pet_flow_cloud.security.dto.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationManager authenticationManager;

    public TokenResponse authenticate(Login loginRequest) {
        try {
            authenticateUser(loginRequest);
            String jwt = generateJwtForUser(loginRequest.getUsername());
            return new TokenResponse(jwt);
        } catch (BadCredentialsException e) {
            log.warn("Invalid username or password for user: {}", loginRequest.getUsername());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
    }

    private void authenticateUser(Login loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String generateJwtForUser(String username) {
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
        return jwtService.generateToken(userDetails);
    }

}
