package com.log.dev.api.utils.factories.entities;

import org.springframework.stereotype.Service;

import com.log.dev.api.modules.author.entities.ArticleEntity;
import com.log.dev.api.modules.author.entities.PublishArticleEntity;

@Service
public class MakePublishArticleEntityFactory implements FactoryInterface<PublishArticleEntity> {

    @Override
    public PublishArticleEntity make() {
        PublishArticleEntity publishArticleEntity = PublishArticleEntity.builder()
                .build();

        return publishArticleEntity;
    }

    public PublishArticleEntity make(ArticleEntity articleEntity) {
        PublishArticleEntity publishArticleEntity = PublishArticleEntity.builder()
                .article(articleEntity)
                .build();

        return publishArticleEntity;
    }

}
