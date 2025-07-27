package com.henry.gestao_de_vagas.modules.candidate.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
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
import com.henry.gestao_de_vagas.modules.candidate.CandidateRepository;
import com.henry.gestao_de_vagas.modules.company.repositories.CompanyRepository;
import com.henry.gestao_de_vagas.modules.company.repositories.JobRepository;
import com.henry.gestao_de_vagas.utils.UtilTest;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GetJobsByFilterControllerTest {

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

    @Value("${security.token.secret.candidate}")
    private String secretKey;

    @BeforeEach
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    @DisplayName("should retrieve jobs by filter successfully")
    public void should_retrieve_jobs_by_filter_successfully() throws Exception {
        var company = makeCompanyEntityFactory.makeFactoryEntity();
        company = companyRepository.save(company);

        var candidate = makeCandidateEntityFactory.makeFactoryEntity();
        candidate = candidateRepository.save(candidate);

        var job = makeJobEntityFactory.makeFactoryEntity();
        job.setCompanyId(company.getId());
        job = jobRepository.save(job);

        String token = UtilTest.generateToken(candidate.getId(), "CANDIDATE", secretKey);

        mvc.perform(MockMvcRequestBuilders.get("/candidate/jobs")
                .header("Authorization", "Bearer ".concat(token))
                .param("filter", job.getDescription()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(job.getId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value(job.getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].companyId").value(job.getCompanyId().toString()));
    }

    @Test
    @DisplayName("should return 401 when token is invalid")
    public void should_return_401_when_token_is_invalid() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/candidate/jobs")
                .header("Authorization", "Bearer invalid.token"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());

    }

    @Test
    @DisplayName("should return 403 when role is invalid")
    public void should_return_403_when_role_is_invalid() throws Exception {
        var candidate = makeCandidateEntityFactory.makeFactoryEntity();
        candidate = candidateRepository.save(candidate);

        String token = UtilTest.generateToken(candidate.getId(), "INVALID_ROLE", secretKey);

        mvc.perform(MockMvcRequestBuilders.get("/candidate/jobs")
                .header("Authorization", "Bearer ".concat(token))
                .param("filter", "some filter"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @DisplayName("should return 409 when filter param is invalid")
    public void should_return_409_when_filter_param_is_invalid() throws Exception {
        var candidate = makeCandidateEntityFactory.makeFactoryEntity();
        candidate = candidateRepository.save(candidate);

        String token = UtilTest.generateToken(candidate.getId(), "CANDIDATE", secretKey);

        mvc.perform(MockMvcRequestBuilders.get("/candidate/jobs")
                .header("Authorization", "Bearer ".concat(token)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}