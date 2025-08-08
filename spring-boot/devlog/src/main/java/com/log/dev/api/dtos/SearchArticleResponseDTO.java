package com.log.dev.api.dtos;

import java.util.List;

import com.log.dev.api.modules.author.entities.PublishArticleEntity;

public record SearchArticleResponseDTO(
        List<PublishArticleEntity> articles,
        int page,
        int perPage,
        int totalPages,
        long totalElements) {

}
