package com.henry.gestao_de_vagas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

// TODO: saber o que é BasicAuthenticationFilter e sua autilização

@Configuration
@EnableMethodSecurity
public class SecurityConfigure {

    @Autowired
    private SecurityCompanyFilter securityCompanyFilter;

    @Autowired
    private SecurityCandidateFilter securityCandidateFilter;

    private static final String[] PERMIT_ALL_LIST = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/actuator/**"
    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> {
            csrf.disable();
        })
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/candidate/").permitAll()
                            .requestMatchers("/company/").permitAll()
                            .requestMatchers("/auth/company").permitAll()
                            .requestMatchers("/auth/candidate").permitAll()
                            .requestMatchers("/candidate/profile").permitAll()
                            .requestMatchers("/candidate/jobs").permitAll()
                            .requestMatchers("/company/job/").permitAll()
                            .requestMatchers(PERMIT_ALL_LIST).permitAll();
                    auth.anyRequest().authenticated();
                })
                .addFilterBefore(this.securityCandidateFilter, BasicAuthenticationFilter.class)
                .addFilterBefore(this.securityCompanyFilter, BasicAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
