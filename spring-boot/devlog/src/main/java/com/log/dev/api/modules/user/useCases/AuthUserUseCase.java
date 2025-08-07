package com.log.dev.api.modules.user.useCases;

import java.time.Duration;
import java.time.Instant;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.log.dev.api.dtos.AuthUserRequestDTO;
import com.log.dev.api.dtos.AuthUserResponseDTO;
import com.log.dev.api.exceptions.UserNotFoundException;
import com.log.dev.api.exceptions.WrongCredentialsException;
import com.log.dev.api.modules.user.entities.UserEntity;
import com.log.dev.api.modules.user.repositories.UserRepository;
import com.log.dev.api.providers.JWTProviderService;

@Service
public class AuthUserUseCase {

    final private UserRepository userRepository;

    final private PasswordEncoder passwordEncoder;

    final private JWTProviderService jwtProviderService;

    public AuthUserUseCase(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JWTProviderService jwtProviderService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProviderService = jwtProviderService;
    }

    public AuthUserResponseDTO execute(AuthUserRequestDTO dto) throws WrongCredentialsException, UserNotFoundException {
        UserEntity user = this.userRepository.findByUsername(dto.getUsername())
                .orElseThrow(UserNotFoundException::new);

        boolean passwordMatches = this.passwordEncoder.matches(dto.getPassword(), user.getPassword());

        if (!passwordMatches) {
            throw new WrongCredentialsException();
        }

        Long expireAt = Instant.now().plus(Duration.ofMinutes(10)).toEpochMilli();
        String token = jwtProviderService.generateToken(user.getId().toString(), expireAt, "USER");

        return  new AuthUserResponseDTO(token, expireAt);
    }
}
