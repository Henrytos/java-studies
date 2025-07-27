package com.henry.gestao_de_vagas.modules.candidate.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.henry.gestao_de_vagas.modules.candidate.CandidateEntity;
import com.henry.gestao_de_vagas.modules.candidate.CandidateRepository;
import com.henry.gestao_de_vagas.modules.candidate.dto.CreateCandidateRequestDTO;
import com.henry.gestao_de_vagas.utils.UtilTest;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class CreateCandidateControllerTest {

        private MockMvc mvc;

        @Autowired
        private WebApplicationContext applicationContext;

        @Autowired
        private CandidateRepository candidateRepository;

        @Autowired
        private MakeCandidateEntityFactory makeCandidateEntityFactory;

        @BeforeEach
        void setup() {
                mvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                                .apply(SecurityMockMvcConfigurers.springSecurity())
                                .build();
        }

        @Test
        @DisplayName("should be able a create new job")
        void should_be_able_a_create_new_job() throws Exception {
                var candidateRequestDTO = CreateCandidateRequestDTO.builder()
                                .name(UtilTest.faker().name().firstName())
                                .username(UtilTest.faker().name().username())
                                .email(UtilTest.faker().internet().emailAddress())
                                .password(UtilTest.faker().internet().password())
                                .description(UtilTest.faker().lorem().characters(25))
                                .build();

                mvc.perform(
                                MockMvcRequestBuilders.post("/candidate/")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(UtilTest.objectToJSON(candidateRequestDTO))// request
                )
                                .andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        @DisplayName("should not be able a create new job if email already exists")
        void should_not_be_able_a_create_new_job_if_email_already_exists() throws Exception {
                CandidateEntity candidateEntity = this.makeCandidateEntityFactory.makeFactoryEntity();
                this.candidateRepository.save(candidateEntity);

                var candidateRequestDTO = CreateCandidateRequestDTO.builder()
                                .name(UtilTest.faker().name().firstName())
                                .username(UtilTest.faker().name().username())
                                .email(candidateEntity.getEmail())
                                .password(UtilTest.faker().internet().password())
                                .description(UtilTest.faker().lorem().characters(25))
                                .build();

                mvc.perform(
                                MockMvcRequestBuilders.post("/candidate/")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(UtilTest.objectToJSON(candidateRequestDTO))// request
                )
                                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
        }

        @Test
        @DisplayName("should not be able a create new job if username already exists")
        void should_not_be_able_a_create_new_job_if_username_already_exists() throws Exception {
                CandidateEntity candidateEntity = this.makeCandidateEntityFactory.makeFactoryEntity();
                this.candidateRepository.save(candidateEntity);

                var candidateRequestDTO = CreateCandidateRequestDTO.builder()
                                .name(UtilTest.faker().name().firstName())
                                .username(candidateEntity.getUsername())
                                .email(UtilTest.faker().internet().emailAddress())
                                .password(UtilTest.faker().internet().password())
                                .description(UtilTest.faker().lorem().characters(25))
                                .build();

                mvc.perform(
                                MockMvcRequestBuilders.post("/candidate/")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(UtilTest.objectToJSON(candidateRequestDTO))// request
                )
                                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
        }
}
