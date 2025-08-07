package com.log.dev.api.modules.user.useCases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.log.dev.api.dtos.ArticleWithDetailsDTO;
import com.log.dev.api.exceptions.ArticleNotFoundException;
import com.log.dev.api.exceptions.UserNotFoundException;
import com.log.dev.api.modules.user.entities.ArticleEntity;
import com.log.dev.api.modules.user.repositories.ArticleRepository;
import com.log.dev.api.modules.user.repositories.UserRepository;

@Service
public class GetArticleWithDetailsUseCase {
    private UserRepository userRepository;

    private ArticleRepository articleRepository;

    public GetArticleWithDetailsUseCase(UserRepository userRepository, ArticleRepository articleRepository) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }

    public ArticleWithDetailsDTO execute(UUID userId, UUID articleId) throws UserNotFoundException,
            ArticleNotFoundException {
        this.userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException());

        ArticleEntity articleEntity = this.articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleNotFoundException());

        return ArticleWithDetailsDTO.fromEntity(articleEntity);
    }

}
