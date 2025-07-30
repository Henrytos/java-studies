package com.henry.gestao_de_vagas.modules.candidate.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.henry.gestao_de_vagas.factories.entities.MakeCandidateEntityFactory;
import com.henry.gestao_de_vagas.factories.entities.MakeCompanyEntityFactory;
import com.henry.gestao_de_vagas.factories.entities.MakeJobEntityFactory;
import com.henry.gestao_de_vagas.modules.candidate.CandidateEntity;
import com.henry.gestao_de_vagas.modules.candidate.CandidateRepository;
import com.henry.gestao_de_vagas.modules.company.entities.CompanyEntity;
import com.henry.gestao_de_vagas.modules.company.entities.JobEntity;
import com.henry.gestao_de_vagas.modules.company.repositories.CompanyRepository;
import com.henry.gestao_de_vagas.modules.company.repositories.JobRepository;
import com.henry.gestao_de_vagas.utils.UtilTest;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplyJobControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private MakeCompanyEntityFactory makeCompanyEntityFactory;

    @Autowired
    private MakeCandidateEntityFactory makeCandidateEntityFactory;

    @Autowired
    private MakeJobEntityFactory makeJobEntityFactory;

    private static final String API_ROUTE_APPLY_JOB = "/candidate/apply/job";

    @Value("${security.token.secret.candidate}")
    private String secretKey;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    @DisplayName("should apply for a job successfully")
    public void should_apply_for_a_job_successfully() throws Exception {
        CompanyEntity companyEntity = this.makeCompanyEntityFactory.makeFactoryEntity();
        companyEntity = this.companyRepository.save(companyEntity);

        JobEntity jobEntity = this.makeJobEntityFactory.makeFactoryEntity(companyEntity.getId());
        jobEntity = this.jobRepository.save(jobEntity);

        CandidateEntity candidateEntity = this.makeCandidateEntityFactory.makeFactoryEntity();
        candidateEntity = this.candidateRepository.save(candidateEntity);

        String token = UtilTest.generateToken(candidateEntity.getId(), "CANDIDATE", secretKey);

        mvc.perform(
                MockMvcRequestBuilders.post(API_ROUTE_APPLY_JOB)
                        .header("Authorization", "Bearer ".concat(token))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jobEntity.getId().toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.candidateId").value(candidateEntity.getId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.jobId").value(jobEntity.getId().toString()));

    }

}
