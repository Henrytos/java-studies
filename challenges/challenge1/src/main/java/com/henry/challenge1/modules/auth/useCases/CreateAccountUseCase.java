package com.henry.challenge1.modules.auth.useCases;

import com.henry.challenge1.modules.auth.UserEntity;
import com.henry.challenge1.modules.auth.dtos.CreateAccountRequestDTO;
import com.henry.challenge1.modules.auth.dtos.CreateAccountResponseDTO;
import com.henry.challenge1.modules.auth.mappers.UserMapper;
import com.henry.challenge1.modules.auth.repositories.JpaUserRepository;
import com.henry.challenge1.modules._core.exceptions.ConflictResourceException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateAccountUseCase {

    private final JpaUserRepository jpaUserRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    public CreateAccountResponseDTO execute(
            CreateAccountRequestDTO body
    ) {
        UserEntity user = this.userMapper.toDomain(body);

        boolean userExists = this.jpaUserRepository
                .findByEmailOrUsername(user.getEmail(), user.getUsername()).isPresent();

        if (userExists)
            throw new ConflictResourceException("User already exists");

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user = jpaUserRepository.save(user);

        return this.userMapper.toInfraResponse(user);
    }

}
