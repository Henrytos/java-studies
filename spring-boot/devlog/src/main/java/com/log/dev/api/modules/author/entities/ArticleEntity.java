package com.log.dev.api.modules.author.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.log.dev.api.modules.user.entities.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "articles")
@JsonIgnoreProperties(value = { "author", "publishArticle" })
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    @Column(name = "sub_title")
    private String subTitle;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity author;

    @OneToOne(mappedBy = "article")
    private PublishArticleEntity publishArticle; // mapeamento

    @Column(name = "reading_duration_in_minutes")
    private int readingDurationInMinutes;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
