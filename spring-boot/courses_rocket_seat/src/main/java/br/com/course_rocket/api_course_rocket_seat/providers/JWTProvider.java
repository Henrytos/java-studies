package br.com.course_rocket.api_course_rocket_seat.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class JWTProvider {

    @Value("${spring.boot.secret.key}")
    private String jwtSecret;

    public void validateToken(String token) {
        token = token.replace("Bearer ", "");

        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);

        var tokenDecoded = JWT.require(algorithm)
                .withIssuer("course-rockets")
                .build()
                .verify(token);
    }

}
