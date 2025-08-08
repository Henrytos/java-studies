package com.log.dev.api.modules.author.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.log.dev.api.modules.author.entities.PublishArticleEntity;

import java.util.Optional;
import java.util.UUID;

public interface PublishArticleRepository extends JpaRepository<PublishArticleEntity, UUID> {
    Optional<PublishArticleEntity> findByArticleId(UUID articleId);
}
