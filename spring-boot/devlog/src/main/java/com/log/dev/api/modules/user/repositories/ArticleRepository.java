package com.log.dev.api.modules.user.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.log.dev.api.modules.user.ArticleEntity;

public interface ArticleRepository extends JpaRepository<ArticleEntity, UUID> {

}
