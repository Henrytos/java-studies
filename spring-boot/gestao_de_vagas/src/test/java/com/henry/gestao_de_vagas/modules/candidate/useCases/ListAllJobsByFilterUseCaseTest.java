package com.henry.gestao_de_vagas.modules.candidate.useCases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import com.henry.gestao_de_vagas.factories.entities.MakeCompanyEntityFactory;
import com.henry.gestao_de_vagas.factories.entities.MakeJobEntityFactory;
import com.henry.gestao_de_vagas.modules.company.entities.JobEntity;
import com.henry.gestao_de_vagas.modules.company.repositories.CompanyRepository;
import com.henry.gestao_de_vagas.modules.company.repositories.JobRepository;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@ActiveProfiles("test")
public class ListAllJobsByFilterUseCaseTest {

    @Autowired
    private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private MakeJobEntityFactory makeJobEntityFactory;

    @Autowired
    private MakeCompanyEntityFactory makeCompanyEntity;

    @Test
    @DisplayName("should be able a list all jobs by filter")
    public void should_be_able_a_list_all_jobs_by_filter() {
        var company = this.makeCompanyEntity.makeFactoryEntity();
        company = this.companyRepository.save(company);

        List<JobEntity> jobs = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            var job = this.makeJobEntityFactory.makeFactoryEntity(company.getId());
            job.setDescription("desenvolvedor front end react");
            jobs.add(job);
        }

        this.jobRepository.saveAll(jobs);

        this.jobRepository.save(this.makeJobEntityFactory.makeFactoryEntity(company.getId()));

        var result = this.listAllJobsByFilterUseCase.execute("react");
        assertEquals(result.size(), 20);
    }

}
