package com.henry.gestao_de_vagas.modules.company.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.henry.gestao_de_vagas.modules.company.dto.CreateJobRequestDTO;

public class JobControllerTest {

    private MockMvc mvc;

    private WebApplicationContext applicationContext;

    @org.junit.Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    }

    @Test
    @DisplayName("should be able a create new job")
    public void should_be_able_a_create_new_job() throws Exception {
        var createJobDTO = CreateJobRequestDTO.builder().description("desenvolvedor java spring boot").level("junior")
                .benefits("vr,vt,va...").build();

        var result = mvc.perform(
                MockMvcRequestBuilders.post("/company/job")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectToJSON(createJobDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());
        System.out.println(result);
    }

    private static String objectToJSON(Object obj) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(objectMapper);
    }

}
