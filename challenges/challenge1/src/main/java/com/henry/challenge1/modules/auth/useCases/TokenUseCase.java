package com.henry.challenge1.modules.auth.useCases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.henry.challenge1.modules.auth.dtos.SignInAccountResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class TokenUseCase {

    @Value("'${spring.secret_key}'")
    private String secretKey;

    public SignInAccountResponseDTO generate(UserDetails userDetails) {

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        Instant expiresAt = generateExpirationDate();

        String token = JWT
                .create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(expiresAt)
                .withArrayClaim("ROLES", Arrays.stream(userDetails.getAuthorities().toArray()).map(a -> a.toString()).toArray(String[]::new))
                .sign(algorithm);

        return new SignInAccountResponseDTO(token, expiresAt.toEpochMilli());
    }


    public Instant generateExpirationDate() {
        return Instant.now().plus(Duration.ofMinutes(20));
    }

}
