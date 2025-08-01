package com.log.dev.api.modules.user.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.log.dev.api.dtos.AuthUserRequestDTO;
import com.log.dev.api.dtos.AuthUserResponseDTO;
import com.log.dev.api.exceptions.UserNotFoundException;
import com.log.dev.api.exceptions.WrongCredentialsException;
import com.log.dev.api.modules.user.UserEntity;
import com.log.dev.api.modules.user.repositories.UserRepository;

@Service
public class AuthUserUseCase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${spring.secrets.jwt.secret_key}")
    private String secretKey;

    public AuthUserResponseDTO execute(AuthUserRequestDTO dto) throws WrongCredentialsException, UserNotFoundException {
        UserEntity user = this.userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new UserNotFoundException());

        Boolean passwordMatches = this.passwordEncoder.matches(dto.getPassword(), user.getPassword());

        if (!passwordMatches) {
            throw new WrongCredentialsException();
        }

        Algorithm algorithm = Algorithm.HMAC256(this.secretKey);

        Long expireAt = Instant.now().plus(Duration.ofMinutes(10)).toEpochMilli();

        String token = JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("roles", Arrays.asList("USER"))
                .withExpiresAt(Instant.ofEpochMilli(expireAt))
                .sign(algorithm);

        AuthUserResponseDTO response = new AuthUserResponseDTO(token, expireAt);
        return response;
    }
}
