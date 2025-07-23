package com.henry.gestao_de_vagas.modules.company.useCases;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import com.henry.gestao_de_vagas.exceptions.CompanyNotFoundException;
import com.henry.gestao_de_vagas.factories.entities.MakeCompanyEntityFactory;
import com.henry.gestao_de_vagas.factories.entities.MakeJobEntityFactory;
import com.henry.gestao_de_vagas.modules.company.entities.CompanyEntity;
import com.henry.gestao_de_vagas.modules.company.entities.JobEntity;
import com.henry.gestao_de_vagas.modules.company.repositories.CompanyRepository;
import com.henry.gestao_de_vagas.modules.company.repositories.JobRepository;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@ActiveProfiles("test")
public class CreateJobUseCaseTest {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @Autowired
    private JobRepository jobEntityRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private MakeCompanyEntityFactory makeCompanyEntityFactory;

    @Autowired
    private MakeJobEntityFactory makeJobEntityFactory;

    @Test
    @DisplayName("should be able to create a job for a company")
    public void should_be_able_to_create_a_job_for_a_company() {
        CompanyEntity company = this.makeCompanyEntityFactory.makeFactoryEntity();
        this.companyRepository.save(company);

        JobEntity job = this.makeJobEntityFactory.makeFactoryEntity();
        job.setCompanyId(company.getId());

        JobEntity createdJob = this.createJobUseCase.execute(job);

        Assertions.assertThat(createdJob).isNotNull();
        Assertions.assertThat(createdJob.getCompanyId()).isEqualTo(company.getId());

        Optional<JobEntity> jobExists = this.jobEntityRepository.findById(createdJob.getId());
        Assertions.assertThat(jobExists.isPresent()).isTrue();
    }

    @Test
    @DisplayName("should not be able to create a job for a company if the company does not exist")
    public void should_not_be_able_to_create_a_job_for_a_company_if_the_company_does_not_exist() {
        JobEntity job = this.makeJobEntityFactory.makeFactoryEntity(UUID.randomUUID());

        Exception exception = assertThrows(CompanyNotFoundException.class, () -> {
            this.createJobUseCase.execute(job);
        });

        Assertions.assertThat(exception.getMessage()).isEqualTo("company not found exception");
    }
}
