package com.henry.gestao_de_vagas.modules.candidate.controllers;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
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
import com.henry.gestao_de_vagas.utils.UtilTest;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GetCandidateProfileControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private MakeCandidateEntityFactory makeCandidateEntityFactory;

    @Value("${security.token.secret.candidate}")
    private String secretKey;

    @BeforeEach
    public void setUp() {
        this.mvc = MockMvcBuilders
                .webAppContextSetup(this.webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    @DisplayName("should retrieve candidate profile successfully")
    public void should_retrieve_candidate_profile_successfully() throws Exception {
        CandidateEntity candidate = makeCandidateEntityFactory.makeFactoryEntity();
        candidate = candidateRepository.save(candidate);

        String token = UtilTest.generateToken(candidate.getId(), "CANDIDATE", secretKey);

        mvc.perform(MockMvcRequestBuilders.get("/candidate/profile")
                .header("Authorization", "Bearer ".concat(token)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(candidate.getId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(candidate.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(candidate.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(candidate.getUsername()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(candidate.getDescription()));
    }

    @Test
    @DisplayName("should return 401 when token is invalid")
    public void should_return_401_when_token_is_invalid() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/candidate/profile")
                .header("Authorization", "Bearer invalid.token"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @DisplayName("should return 403 when role is invalid")
    public void should_return_403_when_role_is_invalid() throws Exception {
        CandidateEntity candidate = makeCandidateEntityFactory.makeFactoryEntity();
        candidate = candidateRepository.save(candidate);

        String token = UtilTest.generateToken(candidate.getId(), "INVALID_ROLE", secretKey);

        mvc.perform(MockMvcRequestBuilders.get("/candidate/profile")
                .header("Authorization", "Bearer ".concat(token)))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @DisplayName("should return 404 when candidate not found")
    public void should_return_404_when_candidate_not_found() throws Exception {
        String token = UtilTest.generateToken(UUID.randomUUID(), "CANDIDATE", secretKey);

        mvc.perform(MockMvcRequestBuilders.get("/candidate/profile")
                .header("Authorization", "Bearer ".concat(token)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
