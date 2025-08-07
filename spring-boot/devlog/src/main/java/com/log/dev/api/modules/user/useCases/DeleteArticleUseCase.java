package com.log.dev.api.modules.user.useCases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.log.dev.api.exceptions.ArticleNotFoundException;
import com.log.dev.api.exceptions.UserNotFoundException;
import com.log.dev.api.exceptions.WrongCredentialsException;
import com.log.dev.api.modules.user.repositories.ArticleRepository;
import com.log.dev.api.modules.user.repositories.UserRepository;

@Service
public class DeleteArticleUseCase {

    private UserRepository userRepository;

    private ArticleRepository articleRepository;

    public DeleteArticleUseCase(UserRepository userRepository, ArticleRepository articleRepository) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }

    public void execute(UUID userId, UUID articleId) {
        var user = this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());

        var article = this.articleRepository.findById(articleId).orElseThrow(() -> new ArticleNotFoundException());

        if (article.getAuthor().getId() != user.getId()) {
            throw new WrongCredentialsException();
        }

        this.articleRepository.delete(article);
    }

}
