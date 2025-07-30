package com.henry.gestao_de_vagas.modules.candidate.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import org.springframework.http.MediaType;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.context.WebApplicationContext;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.henry.gestao_de_vagas.factories.entities.MakeCandidateEntityFactory;
import com.henry.gestao_de_vagas.modules.candidate.CandidateEntity;
import com.henry.gestao_de_vagas.modules.candidate.CandidateRepository;
import com.henry.gestao_de_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import com.henry.gestao_de_vagas.utils.UtilTest;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AuthCandidateControllerTest {

        private MockMvc mvc;

        @Autowired
        private WebApplicationContext webApplicationContext;

        @Autowired
        private MakeCandidateEntityFactory makeCandidateEntityFactory;

        @Autowired
        private CandidateRepository candidateRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @BeforeEach
        public void setup() {
                mvc = MockMvcBuilders.webAppContextSetup(
                                webApplicationContext)
                                .apply(SecurityMockMvcConfigurers.springSecurity())
                                .build();
        }

        @Test
        @DisplayName("should authenticate candidate with valid credentials")
        public void should_authenticate_candidate_with_valid_credentials() throws Exception {

                CandidateEntity candidate = this.makeCandidateEntityFactory.makeFactoryEntity();
                String password = candidate.getPassword();
                saveCandidateInDatabase(candidate);

                AuthCandidateRequestDTO candidateRequest = AuthCandidateRequestDTO.builder()
                                .username(candidate.getUsername())
                                .password(password).build();

                mvc.perform(
                                MockMvcRequestBuilders.post("/auth/candidate")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(UtilTest.objectToJSON(candidateRequest)))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.jsonPath("access_token").exists())
                                .andExpect(MockMvcResultMatchers.jsonPath("expire_at").exists())
                                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

        }

        @Test
        @DisplayName("should not authenticate with invalid password")
        public void should_not_authenticate_with_invalid_password() throws Exception {

                CandidateEntity candidate = this.makeCandidateEntityFactory.makeFactoryEntity();
                saveCandidateInDatabase(candidate);

                AuthCandidateRequestDTO candidateRequest = AuthCandidateRequestDTO.builder()
                                .username(candidate.getUsername())
                                .password("INVALID_PASSWORD").build();

                mvc.perform(
                                MockMvcRequestBuilders.post("/auth/candidate")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(UtilTest.objectToJSON(candidateRequest)))
                                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.jsonPath("message").exists());

        }

        @Test
        @DisplayName("should not authenticate with invalid username")
        public void should_not_authenticate_with_invalid_username() throws Exception {

                CandidateEntity candidate = this.makeCandidateEntityFactory.makeFactoryEntity();
                saveCandidateInDatabase(candidate);

                AuthCandidateRequestDTO candidateRequest = AuthCandidateRequestDTO.builder()
                                .username("INVALID_USERNAME")
                                .password("INVALID_PASSWORD").build();

                mvc.perform(
                                MockMvcRequestBuilders.post("/auth/candidate")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(UtilTest.objectToJSON(candidateRequest)))
                                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.jsonPath("message").exists());

        }

        private void saveCandidateInDatabase(CandidateEntity candidateEntity) {
                String passwordEncoder = this.passwordEncoder.encode(candidateEntity.getPassword());
                candidateEntity.setPassword(passwordEncoder);
                this.candidateRepository.save(candidateEntity);
        }
}
