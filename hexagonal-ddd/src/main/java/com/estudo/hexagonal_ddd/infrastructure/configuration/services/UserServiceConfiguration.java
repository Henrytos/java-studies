package com.estudo.hexagonal_ddd.infrastructure.configuration.services;

import com.estudo.hexagonal_ddd.application.ports.inbound.TokenService;
import com.estudo.hexagonal_ddd.application.ports.inbound.UserServicePort;
import com.estudo.hexagonal_ddd.application.ports.outbound.HashEncoder;
import com.estudo.hexagonal_ddd.application.services.UserServiceImpl;
import com.estudo.hexagonal_ddd.domain.repositories.UserRepository;
import com.estudo.hexagonal_ddd.infrastructure.adapters.outbound.persistence.database.repository.JPAUserRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceConfiguration {

    @Bean
    public UserServicePort userService(
            JPAUserRepositoryImpl jpaUserRepository,
            HashEncoder hashEncoder,
            TokenService tokenService
    ){
        return  new UserServiceImpl(
                jpaUserRepository,
                hashEncoder,
                tokenService
        );
    }

    @Bean
    public  UserRepository userRepository(){
        return new JPAUserRepositoryImpl();
    }

}
