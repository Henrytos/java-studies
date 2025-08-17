package com.log.dev.api.dtos;

import java.util.List;

public record SearchPublishArticleRequestDTO(
                String title,
                String content,
                List<String> tags,
                int page,
                int perPage) {
}
