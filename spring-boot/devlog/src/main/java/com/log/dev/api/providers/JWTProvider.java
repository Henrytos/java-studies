package com.log.dev.api.providers;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JWTProvider {

    @Value("${spring.secrets.jwt.secret_key}")
    private String secretKey;

    private Algorithm algorithm = Algorithm.HMAC256(this.secretKey);

    public String generateToken(String subject) {

        Instant expireAt = Instant.now().plus(Duration.ofMinutes(10));

        String token = JWT
                .create()
                .withSubject(subject)
                .withClaim("roles", Arrays.asList("USER"))
                .withExpiresAt(expireAt)
                .sign(algorithm);

        return token;
    }

    public String generateToken(String subject, String... roles) {
        Instant expireAt = Instant.now().plus(Duration.ofMinutes(10));

        String token = JWT
                .create()
                .withSubject(subject)
                .withClaim("roles", Arrays.asList(roles))
                .withExpiresAt(expireAt)
                .sign(algorithm);

        return token;
    }

    public String generateToken(String subject, Instant expireAt, String... roles) {
        String token = JWT
                .create()
                .withSubject(subject)
                .withClaim("roles", Arrays.asList(roles))
                .withExpiresAt(expireAt)
                .sign(algorithm);

        return token;
    }

    public boolean validateToken(String token) {
        try {
            JWT.require(algorithm).build().verify(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean validateTokenStatic(String token, String secretKey) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        try {
            JWT.require(algorithm).build().verify(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public DecodedJWT getDecodedToken(String token) {
        try {
            DecodedJWT decodedJWT = JWT.require(algorithm).build().verify(token);

            return decodedJWT;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public String getSubject(String token) {
        try {
            DecodedJWT decodedJWT = JWT.require(algorithm).build().verify(token);

            return decodedJWT.getSubject();
        } catch (Exception e) {
            e.printStackTrace();

            return "";
        }
    }
}
