package com.log.dev.api.modules.user.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.log.dev.api.modules.user.entities.LikeUserEntity;

public interface LikeUserRepository extends JpaRepository<LikeUserEntity, UUID> {
    Optional<LikeUserEntity> findByUserIdAndPublishArticleId(UUID userId, UUID publishArticleId);
}
