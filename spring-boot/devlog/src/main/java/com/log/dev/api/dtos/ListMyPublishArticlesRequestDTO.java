package com.log.dev.api.dtos;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ListMyPublishArticlesRequestDTO {
    @NotNull
    private UUID userId;

    @NotNull
    @Length(min = 0)
    private int page;

    @NotNull
    @Length(min = 1, max = 10)
    private int perPage;
}
