package com.henry.challenge1.configs;

import com.henry.challenge1.modules.auth.UserEntity;
import com.henry.challenge1.modules.auth.repositories.JpaUserRepository;
import com.henry.challenge1.modules.auth.useCases.TokenUseCase;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserTokenFilter extends OncePerRequestFilter {

    private final TokenUseCase tokenUseCase;
    private final JpaUserRepository jpaUserRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        String token = authorizationHeader == null ? null : authorizationHeader.replace("Bearer ", "");

        // Rotas públicas não precisam de autenticação
        if (Arrays.asList(SpringWebConfiguration.ROUTES_PUBLIC).contains(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extrai o subject do token
        String subject = this.tokenUseCase.getSubject(token);

        if (StringUtils.isEmpty(subject)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED); // 401
            return; // não continua o chain
        }

        Optional<UserEntity> user = this.jpaUserRepository.findByUsername(subject);

        if (user.isEmpty()) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN); // 403
            return; // não continua o chain
        }

        // Cria autenticação com o usuário encontrado
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.get(), // principal é o próprio UserEntity
                null,       // credenciais não são necessárias aqui
                user.get().getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
