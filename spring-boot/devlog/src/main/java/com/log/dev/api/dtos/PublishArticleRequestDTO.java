package com.log.dev.api.dtos;

import java.util.UUID;

public record PublishArticleRequestDTO(UUID authorId, UUID articleId) {
}
