package com.henry.gestao_de_vagas.modules.company.useCases;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Duration;
import java.time.Instant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import com.henry.gestao_de_vagas.factories.entities.MakeCompanyEntityFactory;
import com.henry.gestao_de_vagas.modules.company.dto.AuthCompanyRequestDTO;
import com.henry.gestao_de_vagas.modules.company.entities.CompanyEntity;
import com.henry.gestao_de_vagas.modules.company.repositories.CompanyRepository;
import com.henry.gestao_de_vagas.providers.JWTCompanyProvider;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@ActiveProfiles("test")
public class AuthCompanyUseCaseTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MakeCompanyEntityFactory makeCompanyEntityFactory;

    @Autowired
    private JWTCompanyProvider jwtCompanyProvider;

    private CompanyEntity company;
    private String passwordPlan;

    @BeforeEach
    public void initSetup() {
        var company = this.makeCompanyEntityFactory.makeFactorEntity();
        var password = company.getPassword();

        company.setPassword(this.passwordEncoder.encode(company.getPassword()));

        this.companyRepository.save(company);

        this.company = company;
        this.passwordPlan = password;
    }

    @DisplayName("should be able to authenticate company")
    public void should_be_able_a_authentication_company() throws Exception {
        var auth = AuthCompanyRequestDTO.builder().username(this.company.getUsername())
                .password(this.passwordPlan).build();

        var result = this.authCompanyUseCase.execute(auth);

        Assertions.assertThat(result).hasFieldOrProperty("access_token");
        Assertions.assertThat(result).hasFieldOrProperty("expire_at");

        final var expireAtInLong = result.getExpire_at();
        final var expireTokenInMinutes = Duration.ofMillis(expireAtInLong - Instant.now().toEpochMilli()).toMinutes();
        final var EXPIRE_TOKEN_MIN_TIME = Long.valueOf(9); // 9 minutes
        final var EXPIRE_TOKEN_MAX_TIME = Long.valueOf(10);// 10 minutes

        Assertions.assertThat(expireTokenInMinutes)
                .isGreaterThanOrEqualTo(EXPIRE_TOKEN_MIN_TIME) // limite minimo
                .isLessThanOrEqualTo(EXPIRE_TOKEN_MAX_TIME); // limite maximo

        var token = this.jwtCompanyProvider.validateToken(result.getAccess_token());
        Assertions.assertThat(token.getSubject()).isEqualTo(company.getId().toString());
        Assertions.assertThat(token.getClaims().isEmpty()).isFalse();

        var role = token.getClaim("roles").asList(String.class);
        Assertions.assertThat(role.get(0)).isEqualTo("COMPANY");
    }

    @Test
    @DisplayName("should be not able a authentication company if company not found")
    public void should_be_not_able_a_authentication_company_if_company_not_found() {
        var auth = AuthCompanyRequestDTO.builder().username("USERNAME_TEST")
                .password(this.passwordPlan).build();

        var exception = assertThrows(UsernameNotFoundException.class, () -> {
            this.authCompanyUseCase.execute(auth);
        });

        Assertions.assertThat(exception.getMessage()).isEqualTo("username/password incorrect");
    }

    @Test
    @DisplayName("should be not able a authentication company if password incorrect")
    public void should_be_not_able_a_authentication_company_if_password_incorrect() {
        var auth = AuthCompanyRequestDTO.builder().username(this.company.getUsername())
                .password("PASSWORD_TEST").build();

        var exception = assertThrows(UsernameNotFoundException.class, () -> {
            this.authCompanyUseCase.execute(auth);
        });

        Assertions.assertThat(exception.getMessage()).isEqualTo("username/password incorrect");
    }
}
