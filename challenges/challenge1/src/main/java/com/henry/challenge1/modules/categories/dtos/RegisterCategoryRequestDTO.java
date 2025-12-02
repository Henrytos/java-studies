package com.henry.challenge1.modules.categories.dtos;

import jakarta.validation.constraints.NotBlank;

public record RegisterCategoryRequestDTO(
        @NotBlank
        String title,
        @NotBlank
        String color
) {
}
