package com.log.dev.api.modules.author.useCases;

import java.util.UUID;

import com.log.dev.api.modules.author.entities.PublishArticleEntity;
import com.log.dev.api.modules.author.repositories.PublishArticleRepository;

import org.springframework.stereotype.Service;

import com.log.dev.api.dtos.ArticleWithDetailsDTO;
import com.log.dev.api.exceptions.ArticleNotFoundException;
import com.log.dev.api.exceptions.UserNotFoundException;
import com.log.dev.api.modules.user.repositories.UserRepository;

@Service
public class GetArticleWithDetailsUseCase {
    final private UserRepository userRepository;

    final private PublishArticleRepository publishArticleRepository;

    public GetArticleWithDetailsUseCase(UserRepository userRepository,
            PublishArticleRepository publishArticleRepository) {
        this.userRepository = userRepository;
        this.publishArticleRepository = publishArticleRepository;
    }

    public ArticleWithDetailsDTO execute(UUID userId, UUID articleId)
            throws UserNotFoundException, ArticleNotFoundException {
        this.userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        PublishArticleEntity publishArticle = this.publishArticleRepository.findByArticleId(articleId)
                .orElseThrow(ArticleNotFoundException::new);

        return ArticleWithDetailsDTO.fromEntity(publishArticle);
    }

}
