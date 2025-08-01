package com.log.dev.api.modules.user.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.log.dev.api.dtos.CreateUserRequestDTO;
import com.log.dev.api.exceptions.WrongCredentialsException;
import com.log.dev.api.modules.user.UserEntity;
import com.log.dev.api.modules.user.repositories.UserRepository;

@Service
public class CreateUserUseCase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity execute(CreateUserRequestDTO dto) throws WrongCredentialsException {
        this.userRepository.findByUsernameOrEmail(dto.getUsername(), dto.getEmail()).ifPresent((user) -> {
            throw new WrongCredentialsException();
        });

        String passwordHash = this.passwordEncoder.encode(dto.getPassword());

        UserEntity user = UserEntity.builder().email(dto
                .getEmail()).username(dto.getUsername()).password(passwordHash).build();

        return this.userRepository.save(user);
    }
}
