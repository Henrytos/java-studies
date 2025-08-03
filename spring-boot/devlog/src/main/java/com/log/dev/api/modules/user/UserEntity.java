package com.log.dev.api.modules.user;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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

}
