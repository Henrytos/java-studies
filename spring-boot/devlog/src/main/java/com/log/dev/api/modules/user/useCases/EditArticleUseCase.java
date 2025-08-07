package com.log.dev.api.modules.user.useCases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.log.dev.api.dtos.UpdateArticleRequestDTO;
import com.log.dev.api.exceptions.ArticleNotFoundException;
import com.log.dev.api.exceptions.UserNotFoundException;
import com.log.dev.api.exceptions.UserUnauthorizedException;
import com.log.dev.api.modules.user.entities.ArticleEntity;
import com.log.dev.api.modules.user.repositories.ArticleRepository;
import com.log.dev.api.modules.user.repositories.UserRepository;

@Service
public class EditArticleUseCase {

    final private UserRepository userRepository;

    final private ArticleRepository articleRepository;

    public EditArticleUseCase(
            UserRepository userRepository,
            ArticleRepository articleRepository) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }

    public void execute(UUID authorId, UUID articleId, UpdateArticleRequestDTO dto)
            throws UserNotFoundException, UserUnauthorizedException {
        var user = this.userRepository.findById(authorId).orElseThrow(UserNotFoundException::new);

        var article = this.articleRepository.findById(articleId).orElseThrow(ArticleNotFoundException::new);

        if (article.getAuthor().getId() != user.getId()) {
            throw new UserUnauthorizedException();
        }

        ArticleEntity articleEntity = ArticleEntity
                .builder()
                .title(dto.getTitle())
                .subTitle(dto.getSubTitle())
                .content(dto.getContent())
                .readingDurationInMinutes(dto.getReadingDurationInMinutes())
                .build();

        this.articleRepository.save(articleEntity);
    }

}
