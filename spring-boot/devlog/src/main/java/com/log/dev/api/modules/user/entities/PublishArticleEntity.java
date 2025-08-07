package com.log.dev.api.modules.user.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "publish_articles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublishArticleEntity {

    @Id
    @Column(name = "publish_article_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany(mappedBy = "publishArticle")
    private List<LikeUserEntity> likes;

    @OneToMany(mappedBy = "publishArticle")
    private List<CommentEntity> comments;

    @OneToOne
    @JoinColumn(name = "article_id")
    private ArticleEntity article; //dono

    @CreationTimestamp
    @Column(name = "publish_at")
    private LocalDateTime publishAt;
}
