package com.log.dev.api.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.log.dev.api.providers.JWTProviderService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilterUser extends OncePerRequestFilter {

    @Autowired
    private JWTProviderService jwtProviderService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if (header != null && request.getRequestURI().startsWith("/articles")) {
            DecodedJWT decodedJWT = this.jwtProviderService.getDecodedToken(header);

            if (decodedJWT == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                return;
            }

            String subjectId = decodedJWT.getSubject();

            request.setAttribute("userId", subjectId);

            var claims = decodedJWT.getClaim("roles").asList(String.class);

            var grants = claims.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_".concat(role.toString().toUpperCase()))).toList();

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(subjectId,
                    null,
                    grants);

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            ;
        }

        filterChain.doFilter(request, response);
    }

}
