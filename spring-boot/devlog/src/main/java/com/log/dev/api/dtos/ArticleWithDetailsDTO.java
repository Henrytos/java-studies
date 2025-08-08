package com.log.dev.api.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.log.dev.api.modules.author.entities.PublishArticleEntity;
import com.log.dev.api.modules.user.entities.CommentEntity;
import com.log.dev.api.modules.user.entities.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleWithDetailsDTO {
    private String articleId;

    private String title;

    private String subTitle;

    private String content;

    private UserEntity author;

    private int quantityLikes;

    private int readingDurationInMinutes;

    private List<CommentEntity> comments;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static ArticleWithDetailsDTO fromEntity(PublishArticleEntity publishArticle) {
        return ArticleWithDetailsDTO.builder()
                .articleId(publishArticle.getArticle().getId().toString())
                .title(publishArticle.getArticle().getTitle())
                .subTitle(publishArticle.getArticle().getSubTitle())
                .content(publishArticle.getArticle().getContent())
                .author(publishArticle.getArticle().getAuthor())
                .quantityLikes(publishArticle.getLikes().size())
                .readingDurationInMinutes(publishArticle.getArticle().getReadingDurationInMinutes())
                .comments(publishArticle.getComments())
                .createdAt(publishArticle.getArticle().getCreatedAt())
                .updatedAt(publishArticle.getArticle().getUpdatedAt())
                .build();
    }
}
