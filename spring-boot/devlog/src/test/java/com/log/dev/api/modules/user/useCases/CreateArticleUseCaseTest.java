package com.log.dev.api.modules.user.useCases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.log.dev.api.dtos.CreateArticleRequestDTO;
import com.log.dev.api.exceptions.UserNotFoundException;
import com.log.dev.api.modules.user.ArticleEntity;
import com.log.dev.api.modules.user.UserEntity;
import com.log.dev.api.modules.user.repositories.ArticleRepository;
import com.log.dev.api.modules.user.repositories.UserRepository;
import com.log.dev.api.utils.factories.entities.MakeArticleEntityFactory;
import com.log.dev.api.utils.factories.entities.MakeUserEntityFactory;

@ExtendWith(MockitoExtension.class)
public class CreateArticleUseCaseTest {

    @InjectMocks
    private CreateArticleUseCase createArticleUseCase; // sut = system under test = sistema em teste, one two

    @Mock
    private UserRepository userRepository;

    @Mock
    private ArticleRepository articleRepository;

    private MakeUserEntityFactory makeUserEntityFactory;

    private MakeArticleEntityFactory makeArticleEntityFactory;
    private UserEntity user;

    private ArticleEntity articleEntity;

    private CreateArticleRequestDTO dto;

    public CreateArticleUseCaseTest() {
        this.makeUserEntityFactory = new MakeUserEntityFactory();
        this.makeArticleEntityFactory = new MakeArticleEntityFactory();
    }

    @BeforeEach
    public void setup() {
        user = makeUserEntityFactory.make(UUID.randomUUID());
        articleEntity = makeArticleEntityFactory.make();

        dto = CreateArticleRequestDTO
                .builder()
                .title(articleEntity.getTitle())
                .subTitle(articleEntity.getSubTitle())
                .content(articleEntity.getContent())
                .authorId(user.getId().toString())
                .readingDurationInMinutes(articleEntity.getReadingDurationInMinutes())
                .build();
    }

    @Nested
    @DisplayName("success test")
    class SuccessTest {

        @Test
        @DisplayName("should be able create a new article")
        public void should_be_able_create_a_new_article() {
            when(userRepository.findById(eq(UUID.fromString(dto.getAuthorId())))).thenReturn(Optional.of(user));

            ArticleEntity articleEntityWithUser = makeArticleEntityFactory.make(user, articleEntity);
            ArticleEntity articleEntityWithUserSaved = makeArticleEntityFactory.make(UUID.randomUUID(), user,
                    articleEntity);

            when(articleRepository.save(articleEntityWithUser)).thenReturn(articleEntityWithUserSaved);

            ArticleEntity response = createArticleUseCase.execute(dto);

            assertNotNull(response);
            assertNotNull(response.getId());
            assertNotNull(response.getAuthor());
            assertEquals(response.getAuthor(), user);
            assertEquals(response.getTitle(), articleEntity.getTitle());
            assertEquals(response.getSubTitle(), articleEntity.getSubTitle());
            assertEquals(response.getContent(), articleEntity.getContent());
            assertEquals(response.getReadingDurationInMinutes(), articleEntity.getReadingDurationInMinutes());
        }

    }

    @Nested
    @DisplayName("failure test")
    class FailureTest {

        @Test
        @DisplayName("should not be able create a new article if author not found")
        public void should_not_be_able_create_a_new_article_if_author_not_found() {
            when(userRepository.findById(eq(UUID.fromString(dto.getAuthorId())))).thenReturn(Optional.empty());

            UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
                createArticleUseCase.execute(dto);
            });

            assertEquals(exception.getMessage(), "User not found");
            assertEquals(exception.getStatusCode(), 404);
        }

    }
}
