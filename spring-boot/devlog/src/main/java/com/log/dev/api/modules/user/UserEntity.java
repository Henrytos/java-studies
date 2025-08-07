package com.log.dev.api.modules.user;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
@JsonIgnoreProperties(value = { "password", "likes", "articles", "avatar" })
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID id;

    @Column(nullable = false, unique = true)
    @Schema(example = "jhondoe")
    private String username;

    @Column(nullable = false, unique = true)
    @Schema(example = "jhondoe@example.com")
    private String email;

    @Column(name = "password_hash", nullable = false)
    @Schema(example = "jhondoe123hash")
    private String password;

    @OneToMany(mappedBy = "author")
    private List<ArticleEntity> articles;

    @OneToMany(mappedBy = "user")
    private List<LikeUserEntity> likes;

    @OneToOne
    @JoinColumn(name = "avatar_id")
    private AvatarEntity avatar;
}
