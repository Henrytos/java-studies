package com.estudo.hexagonal_ddd.infrastructure.configuration.services;

import com.estudo.hexagonal_ddd.application.ports.inbound.UserServicePort;
import com.estudo.hexagonal_ddd.application.services.UserServiceImpl;
import com.estudo.hexagonal_ddd.infrastructure.adapters.outbound.persistence.database.repository.UserRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceConfiguration {

    @Bean
    public UserServicePort  userService(
            UserRepositoryImpl userRepository
    ){
        return new UserServiceImpl(userRepository);
    }
}
