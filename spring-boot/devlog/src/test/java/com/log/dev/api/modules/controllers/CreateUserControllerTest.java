package com.log.dev.api.modules.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.log.dev.api.dtos.CreateUserRequestDTO;
import com.log.dev.api.utils.UtilTest;

import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateUserControllerTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private final static String API_ROUTE_CREATE_USER = "/user";

    @BeforeEach()
    public void setup() {
        this.mvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    @DisplayName("should be able create a new user")
    public void should_be_able_create_a_new_user() throws Exception {
        CreateUserRequestDTO dto = CreateUserRequestDTO
                .builder()
                .email("jhon_doe@example.com")
                .username("jhondoe")
                .password("jhondoe123").build();

        mvc.perform(
                MockMvcRequestBuilders.post(API_ROUTE_CREATE_USER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(UtilTest.objectToJson(dto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
