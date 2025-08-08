package com.log.dev.api.modules.user.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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

import com.log.dev.api.dtos.CreateArticleRequestDTO;
import com.log.dev.api.modules.author.entities.ArticleEntity;
import com.log.dev.api.modules.author.repositories.ArticleRepository;
import com.log.dev.api.modules.user.entities.UserEntity;
import com.log.dev.api.modules.user.repositories.UserRepository;
import com.log.dev.api.providers.JWTProviderService;
import com.log.dev.api.utils.UtilTest;
import com.log.dev.api.utils.factories.entities.MakeArticleEntityFactory;
import com.log.dev.api.utils.factories.entities.MakeUserEntityFactory;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateArticleControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private MakeUserEntityFactory makeUserEntityFactory;

    @Autowired
    private MakeArticleEntityFactory makeArticleEntityFactory;

    @Autowired
    private JWTProviderService jwtProviderService;

    private final String CREATE_ARTICLE_ROUTE_API = "/articles";

    @BeforeEach()
    public void setup() {
        this.mvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Nested
    @DisplayName("Success Tests")
    class SuccessTests {

        private UserEntity userEntity;

        private ArticleEntity articleEntity;

        private CreateArticleRequestDTO dto;

        @BeforeEach
        public void setup() {
            userEntity = makeUserEntityFactory.make();
            userEntity = userRepository.save(userEntity);

            articleEntity = makeArticleEntityFactory.make();

            dto = CreateArticleRequestDTO
                    .builder()
                    .content(articleEntity.getContent())
                    .title(articleEntity.getTitle())
                    .subTitle(articleEntity.getSubTitle())
                    .readingDurationInMinutes(articleEntity.getReadingDurationInMinutes())
                    .build();
        }

        @Test
        @DisplayName("should return 201 to request valid")
        public void should_return_201_to_request_valid() throws Exception {

            String token = jwtProviderService.generateToken(userEntity.getId().toString());

            mvc.perform(
                    MockMvcRequestBuilders
                            .post(CREATE_ARTICLE_ROUTE_API)
                            .header("Authorization", "Bearer ".concat(token))
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(UtilTest.objectToJson(dto)))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(articleEntity.getTitle()))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.subTitle").value(articleEntity.getSubTitle()))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.content").value(articleEntity.getContent()))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.readingDurationInMinutes")
                            .value(articleEntity.getReadingDurationInMinutes()));

            List<ArticleEntity> articles = articleRepository.findAll();
            ArticleEntity article = articles.get(0);
            assertEquals(articles.size(), 1);

            assertEquals(article.getAuthor().getId(), userEntity.getId());
            assertNotNull(article.getCreatedAt());
            assertNotNull(article.getUpdatedAt());
        }
    }

    @Nested
    @DisplayName("Failure Tests")
    class FailureTests {

        private ArticleEntity articleEntity;

        private CreateArticleRequestDTO dto;

        @BeforeEach
        public void setup() {

            articleEntity = makeArticleEntityFactory.make();

            dto = CreateArticleRequestDTO
                    .builder()
                    .content(articleEntity.getContent())
                    .title(articleEntity.getTitle())
                    .subTitle(articleEntity.getSubTitle())
                    .readingDurationInMinutes(articleEntity.getReadingDurationInMinutes())
                    .build();
        }

        @Test
        @DisplayName("should return 404 if user not found")
        public void should_return_404_if_user_not_found() throws Exception {

            String token = jwtProviderService.generateToken(UUID.randomUUID().toString());

            mvc.perform(
                    MockMvcRequestBuilders
                            .post(CREATE_ARTICLE_ROUTE_API)
                            .header("Authorization", "Bearer ".concat(token))
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(UtilTest.objectToJson(dto)))
                    .andExpect(MockMvcResultMatchers.status().isNotFound())
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

            List<ArticleEntity> articles = articleRepository.findAll();
            assertEquals(articles.size(), 0);
        }

        @Test
        @DisplayName("should return 400 bad request")
        public void should_return_400_bad_request() throws Exception {
            dto = null;

            mvc.perform(
                    MockMvcRequestBuilders
                            .post(CREATE_ARTICLE_ROUTE_API)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(UtilTest.objectToJson(dto)))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());
            List<ArticleEntity> articles = articleRepository.findAll();
            assertEquals(articles.size(), 0);
        }
    }

}
