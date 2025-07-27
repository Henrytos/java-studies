package com.henry.gestao_de_vagas.modules.candidate.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.henry.gestao_de_vagas.factories.entities.MakeCandidateEntityFactory;
import com.henry.gestao_de_vagas.factories.entities.MakeCompanyEntityFactory;
import com.henry.gestao_de_vagas.factories.entities.MakeJobEntityFactory;
import com.henry.gestao_de_vagas.modules.candidate.CandidateRepository;
import com.henry.gestao_de_vagas.modules.company.repositories.CompanyRepository;
import com.henry.gestao_de_vagas.modules.company.repositories.JobRepository;

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

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    @DisplayName("should apply for a job successfully")
    public void testApplyForJob() throws Exception {
    }

}
