package com.log.dev.api.modules.user;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity(name = "avatars")
public class AvatarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "avatar_id")
    private UUID id;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "avatar_url", nullable = false)
    private String avatarUrl;

    @OneToOne(mappedBy = "avatar")
    private UserEntity user;
}
