package com.log.dev.api.modules.author.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.log.dev.api.modules.author.entities.PublishArticleEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PublishArticleRepository extends JpaRepository<PublishArticleEntity, UUID> {
    Optional<PublishArticleEntity> findByArticleId(UUID articleId);

    Page<PublishArticleEntity> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseOrTagsNameContainingIgnoreCase(
            String title, String content, List<String> tagsName, Pageable pageable);
}
