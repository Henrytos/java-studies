package com.henry.gestao_de_vagas.modules.company.controllers;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.henry.gestao_de_vagas.modules.company.dto.CreateJobRequestDTO;
import com.henry.gestao_de_vagas.modules.company.entities.CompanyEntity;
import com.henry.gestao_de_vagas.modules.company.repositories.CompanyRepository;
import com.henry.gestao_de_vagas.utils.UtilTest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class JobControllerTest {

        private MockMvc mvc;

        @Autowired
        private WebApplicationContext applicationContext;

        @Autowired
        private CompanyRepository companyRepository;

        @Before
        public void setup() {
                mvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                                .apply(SecurityMockMvcConfigurers.springSecurity())
                                .build();
        }

        @Test
        @DisplayName("should be able a create new job")
        public void should_be_able_a_create_new_job() throws Exception {
                var createJobDTO = CreateJobRequestDTO.builder().description("desenvolvedor java spring boot")
                                .level("junior")
                                .benefits("vr,vt,va...").build();

                var company = CompanyEntity.builder()
                                .name("NAME_TEST")
                                .username("USERNAME_TEST")
                                .email("company@gmail.com")
                                .password("PASSWORD_TEST")
                                .description("DESCRIPTION_TEST")
                                .website("https://www.baeldung.com/spring-boot-h2-database")
                                .build();
                company = this.companyRepository.saveAndFlush(company);

                var subjectId = company.getId();

                var token = UtilTest.generateToken(subjectId, "COMPANY", "9b3bd2a8-ddbe-4084-afa8-2a75dcbc994c");

                var result = mvc.perform(
                                MockMvcRequestBuilders.post("/company/job/")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .header("Authorization", "Bearer ".concat(token))
                                                .content(UtilTest.objectToJSON(createJobDTO)

                                                )// request
                )
                                .andExpect(MockMvcResultMatchers.status().isOk());
                System.out.println(result);
        }

}
