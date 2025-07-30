package com.henry.gestao_de_vagas.modules.company.controllers;

import java.util.UUID;

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

import com.henry.gestao_de_vagas.factories.entities.MakeCompanyEntityFactory;
import com.henry.gestao_de_vagas.modules.company.dto.CreateJobRequestDTO;
import com.henry.gestao_de_vagas.modules.company.repositories.CompanyRepository;
import com.henry.gestao_de_vagas.utils.UtilTest;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateJobControllerTest {

        private MockMvc mvc;

        @Autowired
        private WebApplicationContext applicationContext;

        @Autowired
        private CompanyRepository companyRepository;

        @Autowired
        private MakeCompanyEntityFactory makeCompanyEntity;

        @BeforeEach
        public void setup() {
                mvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                                .apply(SecurityMockMvcConfigurers.springSecurity())
                                .build();
        }

        @Value("${security.token.secret}")
        private String secretKey;

        @Test
        @DisplayName("should be able a create new job")
        public void should_be_able_a_create_new_job() throws Exception {
                var createJobDTO = CreateJobRequestDTO.builder().description("desenvolvedor java spring boot")
                                .level("junior")
                                .benefits("vr,vt,va...").build();
                var company = makeCompanyEntity.makeFactoryEntity();
                company = this.companyRepository.saveAndFlush(company);

                var subjectId = company.getId();

                var token = UtilTest.generateToken(subjectId, "COMPANY", this.secretKey);

                mvc.perform(
                                MockMvcRequestBuilders.post("/company/job/")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .header("Authorization", "Bearer ".concat(token))
                                                .content(UtilTest.objectToJSON(createJobDTO)

                                                )// request
                )
                                .andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        @DisplayName("should not be able a create new job if company not found")
        public void should_not_be_able_a_create_new_job_if_company_not_found() throws Exception {
                var createJobDTO = CreateJobRequestDTO.builder().description("desenvolvedor java spring boot")
                                .level("junior")
                                .benefits("vr,vt,va...").build();

                var token = UtilTest.generateToken(UUID.randomUUID(), "COMPANY", this.secretKey);
                mvc.perform(
                                MockMvcRequestBuilders.post("/company/job/")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .header("Authorization", "Bearer ".concat(token))
                                                .content(UtilTest.objectToJSON(createJobDTO))

                ).andExpect(MockMvcResultMatchers.status().isNotFound());

        }

}
