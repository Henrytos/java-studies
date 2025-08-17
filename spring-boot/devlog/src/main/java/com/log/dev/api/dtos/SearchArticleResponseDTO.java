package com.log.dev.api.dtos;

import java.util.List;

public record SearchArticleResponseDTO(
                List<SearchArticleDTO> articles,
                int page,
                int perPage,
                int totalPages,
                long totalElements) {

}
