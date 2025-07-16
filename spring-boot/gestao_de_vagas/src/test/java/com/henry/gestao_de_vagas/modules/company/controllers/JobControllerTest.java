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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.henry.gestao_de_vagas.modules.company.dto.CreateJobRequestDTO;
import com.henry.gestao_de_vagas.utils.UtilTest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class JobControllerTest {

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
        var createJobDTO = CreateJobRequestDTO.builder().description("desenvolvedor java spring boot").level("junior")
                .benefits("vr,vt,va...").build();
        var subjectId = UUID.fromString("46f42db8-f91b-4ea2-8949-7be691177a7c");
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
