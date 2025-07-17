package com.henry.gestao_de_vagas.utils;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

public class UtilTest {
    public static String objectToJSON(Object obj) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        var json = objectMapper.writeValueAsString(obj);
        return json;
    }

    public static String generateToken(UUID subjectId, String role, String secretKey) {

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expireAt = Instant.now().plus(Duration.ofMinutes(10));

        var token = JWT.create()
                .withIssuer("gestao_de_vagas")
                .withSubject(subjectId.toString())
                .withClaim("roles", Arrays.asList(role))
                .withExpiresAt(expireAt)
                .sign(algorithm);

        return token;
    }

    public static Faker faker() {
        Faker faker = new Faker();

        return faker;
    }

}
