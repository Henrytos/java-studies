package com.henry.gestao_de_vagas.modules.candidate.controllers;

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

import com.henry.gestao_de_vagas.modules.candidate.dto.CreateCandidateRequestDTO;
import com.henry.gestao_de_vagas.utils.UtilTest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateCandidateControllerTest {

        private MockMvc mvc;

        @Autowired
        private WebApplicationContext applicationContext;

        @Before
        public void setup() {
                mvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                                .apply(SecurityMockMvcConfigurers.springSecurity())
                                .build();
        }

        @Test
        @DisplayName("should be able a create new job")
        public void should_be_able_a_create_new_job() throws Exception {
                var candidateRequestDTO = CreateCandidateRequestDTO.builder()
                                .name("henry")
                                .username("henry_dev_java")
                                .email("henry_dev_java@gmail.com")
                                .password("henry_dev_java123")
                                .description("sou desenvolvedor java spring boot")
                                .build();

                var result = mvc.perform(
                                MockMvcRequestBuilders.post("/candidate/")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(UtilTest.objectToJSON(candidateRequestDTO))// request
                )
                                .andExpect(MockMvcResultMatchers.status().isOk());
                System.out.println(result);
        }

}
