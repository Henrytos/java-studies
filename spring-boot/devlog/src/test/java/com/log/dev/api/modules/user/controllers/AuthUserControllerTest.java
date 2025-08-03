package com.log.dev.api.modules.user.controllers;

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

import com.log.dev.api.dtos.AuthUserRequestDTO;
import com.log.dev.api.modules.user.UserEntity;
import com.log.dev.api.modules.user.repositories.UserRepository;
import com.log.dev.api.utils.UtilTest;
import com.log.dev.api.utils.factories.entities.MakeUserEntityFactory;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AuthUserControllerTest {

        private MockMvc mvc;

        @Autowired
        private WebApplicationContext webApplicationContext;

        private final String AUTHENTICATE_ROUTE_API = "/auth/user";

        @Autowired
        private MakeUserEntityFactory makeUserEntityFactory;

        @Autowired
        private UserRepository userRepository;

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
        @DisplayName("should_be_able_return_status_code_200_to_username_and_password_valid")
        public void should_be_able_return_status_code_200_to_username_and_password_valid() throws Exception {

                UserEntity user = makeUserEntityFactory.make();
                String password = user.getPassword();

                user.setPassword(passwordEncoder.encode(password));
                userRepository.save(user);

                AuthUserRequestDTO dto = AuthUserRequestDTO.builder().username(user.getUsername()).password(
                                password)
                                .build();

                mvc.perform(
                                MockMvcRequestBuilders
                                                .post(AUTHENTICATE_ROUTE_API)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(UtilTest.objectToJson(dto)))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.token").exists())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.expireAt").exists());
        }

        @Test
        @DisplayName("should_be_able_return_status_code_400_if_username_or_password_is_empty")
        public void should_be_able_return_status_code_400_if_username_or_password_is_empty() throws Exception {
                AuthUserRequestDTO dto = AuthUserRequestDTO.builder().username("").password("")
                                .build();

                mvc.perform(
                                MockMvcRequestBuilders
                                                .post(AUTHENTICATE_ROUTE_API)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(UtilTest.objectToJson(dto)))
                                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.jsonPath("$[0].message").exists())
                                .andExpect(MockMvcResultMatchers.jsonPath("$[0].field").exists())
                                .andExpect(MockMvcResultMatchers.jsonPath("$[1].message").exists())
                                .andExpect(MockMvcResultMatchers.jsonPath("$[1].field").exists());
        }

        @Test
        @DisplayName("should_be_able_return_status_code_401_if_user_not_found")
        public void should_be_able_return_status_code_401_if_user_not_found() throws Exception {
                UserEntity user = makeUserEntityFactory.make();

                AuthUserRequestDTO dto = AuthUserRequestDTO.builder().username(user.getUsername()).password(
                                user.getPassword())
                                .build();

                mvc.perform(
                                MockMvcRequestBuilders
                                                .post(AUTHENTICATE_ROUTE_API)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(UtilTest.objectToJson(dto)))
                                .andExpect(MockMvcResultMatchers.status().isNotFound())
                                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.message").exists());
        }

        @Test
        @DisplayName("should_be_able_return_status_code_401_if_wrong_credentials")
        public void should_be_able_return_status_code_401_if_wrong_credentials() throws Exception {
                UserEntity user = makeUserEntityFactory.make();
                String password = user.getPassword();

                user.setPassword(passwordEncoder.encode(password));
                userRepository.save(user);

                AuthUserRequestDTO dto = AuthUserRequestDTO.builder().username(user.getUsername())
                                .password(password.concat("invalid"))
                                .build();

                mvc.perform(
                                MockMvcRequestBuilders
                                                .post(AUTHENTICATE_ROUTE_API)
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(UtilTest.objectToJson(dto)))
                                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.message").exists());
        }

}
