package com.henry.gestao_de_vagas.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JWTCandidateProvider {

    @Value("${security.token.secret.candidate}")
    private String secretKey;

    public DecodedJWT validateToken(String token) {
        token = token.replace("Bearer ", "");

        try {

            Algorithm algorithm = Algorithm.HMAC256(this.secretKey);
            var tokenDecode = JWT.require(algorithm).build().verify(token);

            return tokenDecode;
        } catch (Exception e) {
            return null;
        }
    }
}
