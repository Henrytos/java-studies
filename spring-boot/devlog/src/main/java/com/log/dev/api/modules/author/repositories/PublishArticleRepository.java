package com.log.dev.api.modules.author.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.log.dev.api.modules.author.entities.PublishArticleEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PublishArticleRepository extends JpaRepository<PublishArticleEntity, UUID> {
        Optional<PublishArticleEntity> findByArticleId(UUID articleId);

        @Query("""
                        SELECT pa FROM publish_articles pa
                        LEFT JOIN pa.article a
                        LEFT JOIN pa.tags t
                        WHERE LOWER(a.title) LIKE LOWER(CONCAT('%', :title, '%'))
                           OR LOWER(a.content) LIKE LOWER(CONCAT('%', :content, '%'))
                           OR LOWER(t.name) IN :tags
                                """)
        Page<PublishArticleEntity> search(
                        @Param("title") String title,
                        @Param("content") String content,
                        @Param("tags") List<String> tags,
                        Pageable pageable);

}
