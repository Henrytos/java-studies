package com.henry.gestao_de_vagas.modules.company.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.henry.gestao_de_vagas.modules.company.dto.AuthCompanyRequestDTO;
import com.henry.gestao_de_vagas.modules.company.dto.AuthCompanyResponseDTO;
import com.henry.gestao_de_vagas.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCompanyResponseDTO execute(AuthCompanyRequestDTO authCompanyRequestDTO) throws AuthenticationException {

        // 1 verificar se o usuário existe
        var company = this.companyRepository.findByUsername(authCompanyRequestDTO.getUsername()).orElseThrow(() -> {
            throw new UsernameNotFoundException("Usuário não encontrado");
        });

        // 2 verificar se a senha está correta
        var passwordMatches = this.passwordEncoder.matches(authCompanyRequestDTO.getPassword(), company.getPassword());

        if (!passwordMatches) {
            throw new UsernameNotFoundException("Senha incorreta");
        }

        // 3 retornar token JWT ou algo do tipo
        Algorithm algorithm = Algorithm.HMAC256(this.secretKey);
        var expireAt = Instant.now().plus(Duration.ofMinutes(10));

        var token = JWT.create()
                .withIssuer("gestao_de_vagas")
                .withSubject(company.getId().toString())
                .withClaim("roles", Arrays.asList("COMPANY"))
                .withExpiresAt(expireAt)
                .sign(algorithm);

        var authCompanyResponse = AuthCompanyResponseDTO.builder()
                .access_token(token)
                .expire_at(expireAt.toEpochMilli())
                .build();

        return authCompanyResponse;
    }
}
