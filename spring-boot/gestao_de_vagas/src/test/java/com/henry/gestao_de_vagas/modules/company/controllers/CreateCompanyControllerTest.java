package com.henry.gestao_de_vagas.modules.company.controllers;

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

import com.henry.gestao_de_vagas.modules.company.dto.CreateCompanyRequestDTO;
import com.henry.gestao_de_vagas.utils.UtilTest;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateCompanyControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private final String API_ROUTE_CREATE_COMPANY = "/company/";

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    @DisplayName("should be able to create a new company")
    public void should_be_able_to_create_a_new_company() throws Exception {
        CreateCompanyRequestDTO dto = CreateCompanyRequestDTO.builder()
                .username("henry")
                .name("henry")
                .email("henry@example.com")
                .password("password")
                .description("A new company")
                .website("https://example.com")
                .build();

        mvc.perform(
                MockMvcRequestBuilders.post(this.API_ROUTE_CREATE_COMPANY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(UtilTest.objectToJSON(dto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(dto.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(dto.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(dto.getUsername()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").exists());
    }

}
