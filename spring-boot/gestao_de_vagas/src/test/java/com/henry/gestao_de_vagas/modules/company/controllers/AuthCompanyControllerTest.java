package com.henry.gestao_de_vagas.modules.company.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.henry.gestao_de_vagas.factories.entities.MakeCompanyEntityFactory;
import com.henry.gestao_de_vagas.modules.company.dto.AuthCompanyRequestDTO;
import com.henry.gestao_de_vagas.modules.company.entities.CompanyEntity;
import com.henry.gestao_de_vagas.modules.company.repositories.CompanyRepository;
import com.henry.gestao_de_vagas.utils.UtilTest;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AuthCompanyControllerTest {

        private MockMvc mvc;

        @Autowired
        private WebApplicationContext applicationContext;

        @Autowired
        private MakeCompanyEntityFactory makeCompanyEntity;

        @Autowired
        private CompanyRepository companyRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @BeforeEach
        public void setup() {
                mvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                                .apply(SecurityMockMvcConfigurers.springSecurity())
                                .build();
        }

        @Test
        @DisplayName("should authenticate company successfully")
        public void should_authenticate_company_successfully() throws Exception {

                CompanyEntity company = this.makeCompanyEntity.makeFactoryEntity();
                String password = company.getPassword();

                company.setPassword(this.passwordEncoder.encode(password));
                company = this.companyRepository.saveAndFlush(company);

                AuthCompanyRequestDTO request = AuthCompanyRequestDTO.builder()
                                .username(company.getUsername())
                                .password(password)
                                .build();

                this.mvc.perform(
                                MockMvcRequestBuilders.post("/auth/company")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(UtilTest.objectToJSON(request)))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.access_token").isNotEmpty())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.expire_at").isNotEmpty());

        }

}
