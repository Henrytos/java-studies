package com.henry.gestao_de_vagas.modules.company.useCases;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import com.henry.gestao_de_vagas.exceptions.CompanyAlreadyExistsException;
import com.henry.gestao_de_vagas.factories.entities.MakeCompanyEntityFactory;
import com.henry.gestao_de_vagas.modules.company.entities.CompanyEntity;
import com.henry.gestao_de_vagas.modules.company.repositories.CompanyRepository;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@ActiveProfiles("test")
public class CreateCompanyUseCaseTest {

    @Autowired
    private CreateCompanyUseCase createCompanyUseCase;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private MakeCompanyEntityFactory makeCompanyEntityFactory;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("should be able a create new company")
    public void should_be_able_a_create_new_company() {
        CompanyEntity company = this.makeCompanyEntityFactory.makeFactoryEntity();
        String passwordPlan = company.getPassword();

        CompanyEntity companyCreated = this.createCompanyUseCase.execute(company);

        Assertions.assertThat(companyCreated.getId()).isNotNull();
        Assertions.assertThat(companyCreated.getEmail()).isEqualTo(company.getEmail());
        Assertions.assertThat(companyCreated.getCreatedAt()).isNotNull();

        Optional<CompanyEntity> companyFind = this.companyRepository.findById(companyCreated.getId());
        Assertions.assertThat(companyFind.isPresent()).isTrue();

        Boolean isPasswordEncoded = this.passwordEncoder.matches(passwordPlan, companyCreated.getPassword());

        Assertions.assertThat(companyCreated.getPassword()).isNotEqualTo(passwordPlan);
        Assertions.assertThat(isPasswordEncoded).isTrue();
    }

    @Test
    @DisplayName("should not be able a create new company if email exists")
    public void should_not_be_able_a_create_new_company_if_email_exists() {
        CompanyEntity company = this.makeCompanyEntityFactory.makeFactoryEntity();
        this.companyRepository.save(company);

        Exception exception = assertThrows(CompanyAlreadyExistsException.class, () -> {
            this.createCompanyUseCase.execute(company);
        });

        Assertions.assertThat(exception.getMessage()).isEqualTo("company already exists");
    }

}
