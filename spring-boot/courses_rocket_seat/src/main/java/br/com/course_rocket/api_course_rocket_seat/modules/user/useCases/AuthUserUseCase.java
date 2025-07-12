package br.com.course_rocket.api_course_rocket_seat.modules.user.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.course_rocket.api_course_rocket_seat.modules.user.dto.AuthUserRequestDTO;
import br.com.course_rocket.api_course_rocket_seat.modules.user.dto.AuthUserResponseDTO;
import br.com.course_rocket.api_course_rocket_seat.modules.user.dto.WrongCredentialException;
import br.com.course_rocket.api_course_rocket_seat.modules.user.repositories.UserRepository;

@Service
public class AuthUserUseCase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${spring.boot.secret.key}")
    private String secretKey;

    public AuthUserResponseDTO execute(AuthUserRequestDTO dto) throws WrongCredentialException {

        var user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new WrongCredentialException("password or email is invalid"));

        var passwordMatches = this.passwordEncoder.matches(dto.getPassword(), user.getPasswordHash());

        if (!passwordMatches) {
            throw new WrongCredentialException("password or email is invalid");
        }
        Algorithm algorithm = Algorithm.HMAC256(this.secretKey);

        var subject = user.getId().toString();
        var roles = Arrays.asList(user.getRole().name().toUpperCase());
        var expiresAt = Instant.now().plus(Duration.ofMinutes(10));
        String token = JWT.create()
                .withIssuer("course-rockets")
                .withSubject(subject)
                .withClaim("roles", roles)
                .withExpiresAt(expiresAt)
                .sign(algorithm);

        return new AuthUserResponseDTO(token, expiresAt.toEpochMilli());
    }

}
