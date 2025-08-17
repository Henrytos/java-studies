package com.log.dev.api.modules.user.useCases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.log.dev.api.exceptions.ArticleNotFoundException;
import com.log.dev.api.exceptions.UserNotFoundException;
import com.log.dev.api.exceptions.WrongCredentialsException;
import com.log.dev.api.modules.author.repositories.PublishArticleRepository;
import com.log.dev.api.modules.user.repositories.LikeUserRepository;
import com.log.dev.api.modules.user.repositories.UserRepository;

@Service
public class ReactUserAPublishArticleUseCase {

    private final UserRepository userRepository;
    private final PublishArticleRepository publishArticleRepository;
    private final LikeUserRepository likeUserRepository;

    public ReactUserAPublishArticleUseCase(UserRepository userRepository,
            PublishArticleRepository publishArticleRepository,
            LikeUserRepository likeUserRepository) {
        this.userRepository = userRepository;
        this.publishArticleRepository = publishArticleRepository;
        this.likeUserRepository = likeUserRepository;
    }

    public void execute(
            UUID userId,
            UUID publishArticleId,
            Boolean isLike) throws UserNotFoundException, WrongCredentialsException {
        this.userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        this.publishArticleRepository.findById(publishArticleId)
                .orElseThrow(ArticleNotFoundException::new);

        this.likeUserRepository
                .findByUserIdAndPublishArticleId(userId, publishArticleId)
                .ifPresent((like) -> {
                    if (like.getIsLike().equals(isLike))
                        throw new WrongCredentialsException("You already reacted this article");

                    like.setIsLike(isLike);
                    this.likeUserRepository.save(like);
                });

    }

}
