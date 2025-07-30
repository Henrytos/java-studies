package com.henry.gestao_de_vagas.modules.candidate.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.henry.gestao_de_vagas.exceptions.WrongCredentialsException;
import com.henry.gestao_de_vagas.modules.candidate.CandidateRepository;
import com.henry.gestao_de_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import com.henry.gestao_de_vagas.modules.candidate.dto.AuthCandidateResponseDTO;

@Service
public class AuthCandidateUseCase {

    @Value("${security.token.secret.candidate}")
    private String secretKey;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO auth) throws WrongCredentialsException {
        var candidate = this.candidateRepository
                .findByUsername(auth.getUsername())
                .orElseThrow(() -> {
                    throw new WrongCredentialsException("username/password incorrect");
                });

        var passwordMatches = this.passwordEncoder.matches(auth.getPassword(), candidate.getPassword());
        if (!passwordMatches) {
            throw new WrongCredentialsException("username/password incorrect");
        }

        Algorithm algorithm = Algorithm.HMAC256(this.secretKey);

        var expireIn = Instant.now().plus(Duration.ofMinutes(10)).toEpochMilli();

        var token = JWT.create()
                .withIssuer("gestao_de_vagas")
                .withSubject(candidate.getId().toString())
                .withClaim("roles", Arrays.asList("CANDIDATE"))
                .withExpiresAt(Instant.ofEpochMilli(expireIn))
                .sign(algorithm);

        AuthCandidateResponseDTO authCandidateResponse = AuthCandidateResponseDTO
                .builder()
                .access_token(token)
                .expire_at(expireIn)
                .build();

        return authCandidateResponse;
    }

}
