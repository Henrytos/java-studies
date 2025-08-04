package com.log.dev.api.modules.user.useCases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.log.dev.api.dtos.CreateArticleRequestDTO;
import com.log.dev.api.exceptions.UserNotFoundException;
import com.log.dev.api.modules.user.ArticleEntity;
import com.log.dev.api.modules.user.UserEntity;
import com.log.dev.api.modules.user.repositories.ArticleRepository;
import com.log.dev.api.modules.user.repositories.UserRepository;

@Service
public class CreateArticleUseCase {

    private UserRepository userRepository;

    private ArticleRepository articleRepository;

    public CreateArticleUseCase(UserRepository userRepository, ArticleRepository articleRepository) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }

    public ArticleEntity execute(CreateArticleRequestDTO dto) throws UserNotFoundException {
        UserEntity user = this.userRepository.findById(UUID.fromString(dto.getAuthorId()))
                .orElseThrow(() -> new UserNotFoundException());

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
