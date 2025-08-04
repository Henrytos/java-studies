package com.log.dev.api.utils.factories.entities;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;
import com.log.dev.api.modules.user.ArticleEntity;
import com.log.dev.api.modules.user.UserEntity;

@Service
public class MakeArticleEntityFactory implements FactoryInterface<ArticleEntity> {
    private final Faker faker = new Faker();

    public ArticleEntity make() {
        ArticleEntity articleEntity = ArticleEntity.builder()
                .title(faker.lorem().characters(10, 50))
                .subTitle(faker.lorem().characters(10, 100))
                .content(faker.lorem().characters(10, 100))
                .readingDurationInMinutes((int) Math.round(Math.random() * 10))
                .build();

        return articleEntity;
    }

    public ArticleEntity make(UserEntity author) {
        ArticleEntity articleEntity = ArticleEntity.builder()
                .author(author)
                .title(faker.lorem().characters(10, 50))
                .subTitle(faker.lorem().characters(10, 100))
                .content(faker.lorem().characters(10, 100))
                .readingDurationInMinutes((int) Math.round(Math.random() * 10))
                .build();

        return articleEntity;
    }

    public ArticleEntity make(UserEntity author, ArticleEntity article) {
        ArticleEntity articleEntity = ArticleEntity.builder()
                .author(author)
                .title(article.getTitle())
                .subTitle(article.getSubTitle())
                .content(article.getContent())
                .readingDurationInMinutes(article.getReadingDurationInMinutes())
                .build();

        return articleEntity;
    }

    public ArticleEntity make(UUID articleId, UserEntity author, ArticleEntity article) {
        ArticleEntity articleEntity = ArticleEntity.builder()
                .id(articleId)
                .author(author)
                .title(article.getTitle())
                .subTitle(article.getSubTitle())
                .content(article.getContent())
                .readingDurationInMinutes(article.getReadingDurationInMinutes())
                .build();

        return articleEntity;
    }
}
