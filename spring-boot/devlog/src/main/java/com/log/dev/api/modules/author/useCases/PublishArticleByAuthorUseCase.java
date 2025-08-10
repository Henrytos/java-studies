package com.log.dev.api.modules.author.useCases;

import com.log.dev.api.dtos.PublishArticleRequestDTO;
import com.log.dev.api.exceptions.ArticleNotFoundException;
import com.log.dev.api.exceptions.InternalServerErrorException;
import com.log.dev.api.exceptions.UserNotFoundException;
import com.log.dev.api.exceptions.WrongCredentialsException;
import com.log.dev.api.modules.author.entities.ArticleEntity;
import com.log.dev.api.modules.author.entities.PublishArticleEntity;
import com.log.dev.api.modules.author.repositories.ArticleRepository;
import com.log.dev.api.modules.author.repositories.PublishArticleRepository;
import com.log.dev.api.modules.user.entities.UserEntity;
import com.log.dev.api.modules.user.repositories.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class PublishArticleByAuthorUseCase {

    final private UserRepository userRepository;

    final private ArticleRepository articleRepository;

    final private PublishArticleRepository publishArticleRepository;

    public PublishArticleByAuthorUseCase(
            UserRepository userRepository,
            ArticleRepository articleRepository,
            PublishArticleRepository publishArticleRepository) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
        this.publishArticleRepository = publishArticleRepository;
    }

    public void execute(PublishArticleRequestDTO dto)
            throws UserNotFoundException, ArticleNotFoundException,
            InternalServerErrorException, WrongCredentialsException {
        UserEntity user = this.userRepository.findById(dto.authorId()).orElseThrow(UserNotFoundException::new);
        ArticleEntity article = this.articleRepository.findById(dto.articleId())
                .orElseThrow(ArticleNotFoundException::new);

        if (article.getAuthor() == null) {
            throw new InternalServerErrorException();
        }

        if (!article.getAuthor().getId().equals(user.getId())) {
            throw new WrongCredentialsException();
        }

        PublishArticleEntity publishArticle = PublishArticleEntity.builder().article(article).build();
        this.publishArticleRepository.save(publishArticle);
    }

}
