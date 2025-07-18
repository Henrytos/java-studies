package com.henry.gestao_de_vagas.modules.useCases;

import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.time.Instant;

import javax.naming.AuthenticationException;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.henry.gestao_de_vagas.factories.entities.MakeCandidateEntityFactory;
import com.henry.gestao_de_vagas.modules.candidate.CandidateRepository;
import com.henry.gestao_de_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import com.henry.gestao_de_vagas.modules.candidate.useCases.AuthCandidateUseCase;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AuthCandidateUseCaseTest {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private AuthCandidateUseCase authCandidateUseCase;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("should be able a authentication candidate")
    public void should_be_able_a_authentication_candidate() throws Exception {
        var candidate = MakeCandidateEntityFactory.staticMakeFactorEntity();
        var password = candidate.getPassword();

        candidate.setPassword(this.passwordEncoder.encode(candidate.getPassword()));

        this.candidateRepository.save(candidate);

        var auth = AuthCandidateRequestDTO.builder().username(candidate.getUsername())
                .password(password).build();

        var result = this.authCandidateUseCase.execute(auth);

        Assertions.assertThat(result).hasFieldOrProperty("access_token");
        Assertions.assertThat(result).hasFieldOrProperty("expire_at");

        var expireAtInLong = result.getExpire_at();
        var expireTokenInMinutes = Duration.ofMillis(expireAtInLong - Instant.now().toEpochMilli()).toMinutes();
        var EXPIRE_TOKEN_MIN_TIME = Long.valueOf(9); // 9 minutes
        var EXPIRE_TOKEN_MAX_TIME = Long.valueOf(10);// 10 minutes

        Assertions.assertThat(expireTokenInMinutes)
                .isGreaterThanOrEqualTo(EXPIRE_TOKEN_MIN_TIME) // limite minimo
                .isLessThanOrEqualTo(EXPIRE_TOKEN_MAX_TIME); // limite maximo
        assertTrue(expireTokenInMinutes >= EXPIRE_TOKEN_MIN_TIME);
        assertTrue(EXPIRE_TOKEN_MAX_TIME >= expireTokenInMinutes);

    }

    @Test
    @DisplayName("should be not able a authentication candidate if candidate not found")
    public void should_be_not_able_a_authentication_candidate_if_candidate_not_found() {

        var auth = AuthCandidateRequestDTO.builder().username("USERNAME_TEST")
                .password("PASSWORD_TEST").build();

        try {
            this.authCandidateUseCase.execute(auth);
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(UsernameNotFoundException.class);
        }

    }

    @Test
    @DisplayName("should be not able a authentication candidate if password incorrect")
    public void should_be_not_able_a_authentication_candidate_if_password_incorrect() {
        var candidate = MakeCandidateEntityFactory.staticMakeFactorEntity();

        candidate.setPassword(this.passwordEncoder.encode(candidate.getPassword()));

        this.candidateRepository.save(candidate);

        var auth = AuthCandidateRequestDTO.builder().username(candidate.getUsername())
                .password("PASSWORD_TEST").build();

        try {
            this.authCandidateUseCase.execute(auth);
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(AuthenticationException.class);
        }

    }
}
