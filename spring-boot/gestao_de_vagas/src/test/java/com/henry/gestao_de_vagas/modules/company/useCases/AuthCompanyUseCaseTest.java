package com.henry.gestao_de_vagas.modules.company.useCases;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.time.Instant;

import org.assertj.core.api.Assertions;
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
import com.henry.gestao_de_vagas.modules.company.repositories.CompanyRepository;

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

    @Test
    @DisplayName("should be able a authentication company")
    public void should_be_able_a_authentication_company() throws Exception {
        var company = this.makeCompanyEntityFactory.makeFactorEntity();
        var password = company.getPassword();

        company.setPassword(this.passwordEncoder.encode(company.getPassword()));

        this.companyRepository.save(company);

        var auth = AuthCompanyRequestDTO.builder().username(company.getUsername())
                .password(password).build();

        var result = this.authCompanyUseCase.execute(auth);

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
    @DisplayName("should be not able a authentication company if company not found")
    public void should_be_not_able_a_authentication_company_if_company_not_found() {

        var auth = AuthCompanyRequestDTO.builder().username("USERNAME_TEST")
                .password("PASSWORD_TEST").build();

        try {
            this.authCompanyUseCase.execute(auth);
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(UsernameNotFoundException.class);
        }

    }

    @Test
    @DisplayName("should be not able a authentication company if password incorrect")
    public void should_be_not_able_a_authentication_company_if_password_incorrect() {
        var company = this.makeCompanyEntityFactory.makeFactorEntity();
        company.setPassword(this.passwordEncoder.encode(company.getPassword()));

        this.companyRepository.save(company);

        var auth = AuthCompanyRequestDTO.builder().username(company.getUsername())
                .password("PASSWORD_TEST").build();

        try {
            this.authCompanyUseCase.execute(auth);
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(UsernameNotFoundException.class);
        }

    }
}
