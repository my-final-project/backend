package br.com.juhmaran.pet_flow_cloud.security.filters;

import br.com.juhmaran.pet_flow_cloud.security.services.JwtService;
import br.com.juhmaran.pet_flow_cloud.security.services.UserDetailsServiceImpl;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Slf4j
@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request,
                                    @Nonnull HttpServletResponse response,
                                    @Nonnull FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (isInvalidAuthorizationHeader(authHeader)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            processJwtAuthentication(authHeader, request);
            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            log.error("JWT authentication failed: {}", exception.getMessage());
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }

    }

    private boolean isInvalidAuthorizationHeader(String authHeader) {
        return authHeader == null || !authHeader.startsWith("Bearer ");
    }

    private void processJwtAuthentication(String authHeader, HttpServletRequest request) {
        String jwt = authHeader.substring(7);
        String userEmail = jwtService.extractEmail(jwt);
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(jwt, userDetails)) {
                setAuthenticationForUser(request, userDetails);
            }
        }
    }

    private void setAuthenticationForUser(HttpServletRequest request, UserDetails userDetails) {
        var authToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }


}
