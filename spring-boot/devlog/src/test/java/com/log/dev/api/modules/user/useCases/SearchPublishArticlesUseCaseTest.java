package com.log.dev.api.modules.user.useCases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import com.log.dev.api.dtos.SearchPublishArticleResponseDTO;
import com.log.dev.api.dtos.SearchPublishArticleRequestDTO;
import com.log.dev.api.modules.author.entities.ArticleEntity;
import com.log.dev.api.modules.author.entities.PublishArticleEntity;
import com.log.dev.api.modules.author.repositories.ArticleRepository;
import com.log.dev.api.modules.author.repositories.PublishArticleRepository;
import com.log.dev.api.modules.user.entities.UserEntity;
import com.log.dev.api.modules.user.repositories.UserRepository;
import com.log.dev.api.utils.factories.entities.MakeArticleEntityFactory;
import com.log.dev.api.utils.factories.entities.MakePublishArticleEntityFactory;
import com.log.dev.api.utils.factories.entities.MakeUserEntityFactory;

import jakarta.transaction.Transactional;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Transactional
public class SearchPublishArticlesUseCaseTest {

    @Autowired
    private SearchPublishArticlesUseCase SearchPublishArticlesUseCase;

    @Autowired
    private PublishArticleRepository publishArticleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private MakeUserEntityFactory makeUserEntityFactory;

    @Autowired
    private MakeArticleEntityFactory makeArticleEntityFactory;

    @Autowired
    private MakePublishArticleEntityFactory makePublishArticleEntityFactory;

    @Test
    @DisplayName("should be able search in application")
    public void should_be_able_search_in_application() {
        List<String> titles = new ArrayList<>() {
            {
                add("docker");
                add("docker is a container");
                add("docker to junior developer");
                add("aws");
            }
        };

        UserEntity author = makeUserEntityFactory.make();
        author = userRepository.save(author);

        for (String title : titles) {
            ArticleEntity articleEntity = makeArticleEntityFactory.make(author);
            articleEntity.setTitle(title);
            articleEntity = articleRepository.save(articleEntity);

            PublishArticleEntity publishArticleEntity = makePublishArticleEntityFactory.make(articleEntity);
            publishArticleRepository.save(publishArticleEntity);
        }

        SearchPublishArticleRequestDTO search = new SearchPublishArticleRequestDTO("docker", "docker content", null, 1,
                10);

        SearchPublishArticleResponseDTO response = SearchPublishArticlesUseCase.execute(search);
        assertEquals(response.totalPages(), 1);
        assertEquals(response.page(), 1);
        assertEquals(response.perPage(), 10);
        assertEquals(response.totalPages(), 1);
        assertEquals(response.articles().get(0).getAuthorName(), author.getUsername());
        assertEquals(response.articles().get(0).getTitle(), "docker");
        assertEquals(response.articles().get(1).getTitle(), "docker is a container");
        assertEquals(response.articles().get(2).getTitle(), "docker to junior developer");
    }

    @Test
    @DisplayName("should be able search in application return 0")
    public void should_be_able_search_in_application_return_0() {
        SearchPublishArticleRequestDTO search = new SearchPublishArticleRequestDTO("docker", "docker content", null, 1,
                10);

        SearchPublishArticleResponseDTO response = SearchPublishArticlesUseCase.execute(search);
        assertEquals(response.articles().size(), 0);
        assertEquals(response.page(), 1);
        assertEquals(response.perPage(), 10);
        assertEquals(response.totalPages(), 0);
    }

}
