package br.com.juhmaran.pet_flow_cloud.security.filters;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class CsrfCookieFilter extends OncePerRequestFilter {

    private static final String CSRF_TOKEN_HEADER_NAME = "XSRF-TOKEN";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @Nonnull HttpServletResponse response,
                                    @Nonnull FilterChain filterChain) throws ServletException, IOException {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

        if (csrfToken != null) {
            addCsrfTokenToResponse(request, response, csrfToken);
        }
        filterChain.doFilter(request, response);
    }

    private void addCsrfTokenToResponse(HttpServletRequest request,
                                        HttpServletResponse response, CsrfToken csrfToken) {
        response.setHeader(csrfToken.getHeaderName(), csrfToken.getToken());
        Cookie csrfCookie = new Cookie(CSRF_TOKEN_HEADER_NAME, csrfToken.getToken());
        csrfCookie.setPath("/");
        csrfCookie.setHttpOnly(false);
        csrfCookie.setSecure(request.isSecure());
        response.addCookie(csrfCookie);
    }

}
