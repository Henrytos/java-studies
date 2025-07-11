package br.com.course_rocket.api_course_rocket_seat.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfigure {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf((csrf) -> {
            csrf.disable();
        }).authorizeHttpRequests(auth -> {
            auth.requestMatchers("/course/**").permitAll();
        });

        return httpSecurity.build();
    }
}
