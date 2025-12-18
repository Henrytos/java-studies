package com.henry.challenge1.configs;

import com.henry.challenge1.configs.dtos.ErrorMessageResponseDTO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tools.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringWebConfiguration {

    public static final String[] ROUTES_PUBLIC = {
            "/api/v1/auth/sign-in",
            "/api/v1/auth/sign-up"
    };

    public static final String[] ROUTES_PRIVATE = {
            "/api/v1/categories",
            "/api/v1/videos",
    };

    private final UserTokenFilter userTokenFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();


        return http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS
                        ))
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(ROUTES_PUBLIC).permitAll()
                                .requestMatchers(ROUTES_PRIVATE).authenticated()
                )
                /*
                 * throw new AuthenticationCredentialsNotFoundException("Token ausente ou inválido"); // 401
                 * throw new BadCredentialsException("Token inválido"); // 401
                 * throw new AccessDeniedException("Usuário não encontrado"); // 403
                 */
                .exceptionHandling(exceptionHandler ->
                        exceptionHandler
                                .authenticationEntryPoint((req, res, ex) -> { //401
                                    ErrorMessageResponseDTO errorMessageResponseDTO = new ErrorMessageResponseDTO(ex.getMessage(), HttpServletResponse.SC_UNAUTHORIZED);
                                                    res.setStatus(errorMessageResponseDTO.statusCode());
                                    res.setContentType("application/json");
                                    res.getWriter().write(objectMapper.writeValueAsString(errorMessageResponseDTO));
                                })
                                .accessDeniedHandler((req, res, ex) -> { // 403
                                    ErrorMessageResponseDTO errorMessageResponseDTO = new ErrorMessageResponseDTO(ex.getMessage(), HttpServletResponse.SC_FORBIDDEN);

                                    res.setStatus(errorMessageResponseDTO.statusCode());
                                    res.setContentType("application/json");
                                    res.getWriter().write(objectMapper.writeValueAsString(errorMessageResponseDTO));
                                })
                )
                .addFilterBefore(userTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
