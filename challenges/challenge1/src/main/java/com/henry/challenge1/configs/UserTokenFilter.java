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
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
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

        if (Arrays.asList(SpringWebConfiguration.ROUTES_PUBLIC).contains(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }


        String authorizationHeader = request.getHeader("Authorization");
        String token = authorizationHeader == null ? null : authorizationHeader.replace("Bearer ", "");

        System.out.println("authorizationHeader=" + authorizationHeader);
        System.out.println("token=" + token);

        if (StringUtils.isEmpty(token) || StringUtils.isEmpty(authorizationHeader)) {
            throw new AuthenticationCredentialsNotFoundException("Token ausente ou inválido");        }

        String subject = this.tokenUseCase.getSubject(token);
        /*
        * throw new AuthenticationCredentialsNotFoundException("Token ausente ou inválido"); // 401
        * throw new BadCredentialsException("Token inválido"); // 401
        * throw new AccessDeniedException("Usuário não encontrado"); // 403
        */
        if (StringUtils.isEmpty(subject))
            throw new BadCredentialsException("Token inválido");

        Optional<UserEntity> user = this.jpaUserRepository.findByUsername(subject);

        if (user.isEmpty())
            throw new AccessDeniedException("Usuário não encontrado");

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.get(),
                null,
                user.get().getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
