package com.log.dev.api.modules.author.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.log.dev.api.modules.user.entities.CommentEntity;
import com.log.dev.api.modules.user.entities.LikeUserEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "publish_articles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = { "comments", "likes" })
public class PublishArticleEntity {

    @Id
    @Column(name = "publish_article_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany(mappedBy = "publishArticle")
    private List<LikeUserEntity> likes;

    @OneToMany(mappedBy = "publishArticle") // aponta o dono da relação
    private List<CommentEntity> comments;

    @ManyToMany(mappedBy = "publishArticles") // aponta o dono da relação
    private List<TagEntity> tags;

    @OneToOne
    @JoinColumn(name = "article_id") // aponta o dono da relação
    private ArticleEntity article;

    @CreationTimestamp
    @Column(name = "publish_at")
    private LocalDateTime publishAt;
}
