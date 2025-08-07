package com.log.dev.api.modules.user.repositories;

import com.log.dev.api.modules.user.entities.PublishArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PublishArticleRepository extends JpaRepository<PublishArticleEntity, UUID> {
    Optional<PublishArticleEntity> findByArticleId(UUID articleId);
}
