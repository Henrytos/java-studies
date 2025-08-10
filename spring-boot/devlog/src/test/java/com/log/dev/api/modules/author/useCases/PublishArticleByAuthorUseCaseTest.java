package com.log.dev.api.modules.author.useCases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import com.log.dev.api.dtos.PublishArticleRequestDTO;
import com.log.dev.api.exceptions.ArticleNotFoundException;
import com.log.dev.api.exceptions.InternalServerErrorException;
import com.log.dev.api.exceptions.UserNotFoundException;
import com.log.dev.api.exceptions.WrongCredentialsException;
import com.log.dev.api.modules.author.entities.ArticleEntity;
import com.log.dev.api.modules.author.repositories.ArticleRepository;
import com.log.dev.api.modules.author.repositories.PublishArticleRepository;
import com.log.dev.api.modules.user.entities.UserEntity;
import com.log.dev.api.modules.user.repositories.UserRepository;
import com.log.dev.api.utils.factories.entities.MakeArticleEntityFactory;
import com.log.dev.api.utils.factories.entities.MakeUserEntityFactory;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PublishArticleByAuthorUseCaseTest {

    @Autowired
    private PublishArticleByAuthorUseCase publishArticleByAuthorUseCase;

    @Autowired
    private PublishArticleRepository publishArticleRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MakeUserEntityFactory makeUserEntityFactory;

    @Autowired
    private MakeArticleEntityFactory makeArticleEntityFactory;

    @AfterEach
    public void afterEach() {
        this.publishArticleRepository.deleteAll();
        this.articleRepository.deleteAll();
        this.userRepository.deleteAll();
    }

    @Test
    @DisplayName("should be able return list articles")
    public void should_be_able_return_list_articles() {
        UserEntity author = makeUserEntityFactory.make();
        author = userRepository.save(author);

        ArticleEntity article = makeArticleEntityFactory.make(author);
        article = articleRepository.save(article);

        PublishArticleRequestDTO dto = new PublishArticleRequestDTO(author.getId(), article.getId());
        this.publishArticleByAuthorUseCase.execute(dto);

        assertEquals(this.publishArticleRepository.findAll().size(), 1);
    }

    @Test
    @DisplayName("should not be able return list articles if user not found")
    public void should_not_be_able_return_list_articles_if_user_not_found() {

        PublishArticleRequestDTO dto = new PublishArticleRequestDTO(UUID.randomUUID(), UUID.randomUUID());

        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            this.publishArticleByAuthorUseCase.execute(dto);
        });

        assertEquals(exception.getMessage(), "User not found");
    }

    @Test
    @DisplayName("should not be able return list articles if article not found")
    public void should_not_be_able_return_list_articles_if_article_not_found() {
        UserEntity author = makeUserEntityFactory.make();
        author = userRepository.save(author);

        PublishArticleRequestDTO dto = new PublishArticleRequestDTO(author.getId(), UUID.randomUUID());

        Exception exception = assertThrows(ArticleNotFoundException.class, () -> {
            this.publishArticleByAuthorUseCase.execute(dto);
        });

        assertEquals(exception.getMessage(), "Article not found");
    }

    @Test
    @DisplayName("should not be able return list articles if wrong credentials")
    public void should_not_be_able_return_list_articles_if_wrong_credentials() {
        UserEntity author = makeUserEntityFactory.make();
        author = userRepository.save(author);

        UserEntity falseAuthor = makeUserEntityFactory.make();
        falseAuthor = userRepository.save(falseAuthor);

        ArticleEntity article = makeArticleEntityFactory.make(author);
        article = articleRepository.save(article);

        PublishArticleRequestDTO dto = new PublishArticleRequestDTO(falseAuthor.getId(), article.getId());

        Exception exception = assertThrows(WrongCredentialsException.class, () -> {
            this.publishArticleByAuthorUseCase.execute(dto);
        });

        assertEquals(exception.getMessage(), "Wrong credentials");
    }

    @Test
    @DisplayName("should not be able return list articles if author not exists in article")
    public void should_not_be_able_return_list_articles_if_author_not_exists_in_article() {
        UserEntity author = makeUserEntityFactory.make();
        author = userRepository.save(author);

        ArticleEntity article = makeArticleEntityFactory.make();
        article = articleRepository.save(article);

        PublishArticleRequestDTO dto = new PublishArticleRequestDTO(author.getId(), article.getId());

        Exception exception = assertThrows(InternalServerErrorException.class, () -> {
            this.publishArticleByAuthorUseCase.execute(dto);
        });

        assertEquals(exception.getMessage(), "Internal server error");
    }

}
