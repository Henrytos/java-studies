package com.log.dev.api.modules.author.useCases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.log.dev.api.dtos.CreateArticleRequestDTO;
import com.log.dev.api.exceptions.UserNotFoundException;
import com.log.dev.api.modules.author.entities.ArticleEntity;
import com.log.dev.api.modules.author.repositories.ArticleRepository;
import com.log.dev.api.modules.user.entities.UserEntity;
import com.log.dev.api.modules.user.repositories.UserRepository;

@Service
public class WriteArticleUseCase {

    final private UserRepository userRepository;

    final private ArticleRepository articleRepository;

    public WriteArticleUseCase(UserRepository userRepository, ArticleRepository articleRepository) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }

    public ArticleEntity execute(UUID authorId, CreateArticleRequestDTO dto) throws UserNotFoundException {
        UserEntity user = this.userRepository.findById(authorId)
                .orElseThrow(UserNotFoundException::new);

        ArticleEntity articleEntity = ArticleEntity.builder()
                .author(user)
                .title(dto.getTitle())
                .subTitle(dto.getSubTitle())
                .content(dto.getContent())
                .readingDurationInMinutes(dto.getReadingDurationInMinutes())
                .build();

        return this.articleRepository.save(articleEntity);
    }
}
