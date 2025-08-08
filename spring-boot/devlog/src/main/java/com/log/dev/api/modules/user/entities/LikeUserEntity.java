package com.log.dev.api.modules.user.entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.log.dev.api.modules.author.entities.PublishArticleEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity(name = "likes")
@JsonIgnoreProperties(value = { "user", "article" })
@Data
public class LikeUserEntity {

    @Id
    @Column(name = "like_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "publish_article_id")
    private PublishArticleEntity publishArticle;

    @Column(name = "is_like", nullable = false)
    private Boolean isLike = false;
}
