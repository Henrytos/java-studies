package com.log.dev.api.modules.author.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.log.dev.api.modules.author.entities.TagEntity;

public interface TagRepository extends JpaRepository<TagEntity, UUID> {

}
