package com.log.dev.api.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.log.dev.api.modules.user.entities.ArticleEntity;
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

    public static ArticleWithDetailsDTO fromEntity(ArticleEntity articleEntity) {
        return ArticleWithDetailsDTO.builder()
                .articleId(articleEntity.getId().toString())
                .title(articleEntity.getTitle())
                .subTitle(articleEntity.getSubTitle())
                .content(articleEntity.getContent())
                .author(articleEntity.getAuthor())
                .quantityLikes(articleEntity.getLikes().size())
                .readingDurationInMinutes(articleEntity.getReadingDurationInMinutes())
                .comments(articleEntity.getComments())
                .createdAt(articleEntity.getCreatedAt())
                .updatedAt(articleEntity.getUpdatedAt())
                .build();
    }
}
