package com.henry.challenge1.modules.videos.controllers.dtos;

import jakarta.validation.constraints.NotBlank;

public record RegisterVideoRequestDTO(
        @NotBlank
        String title,
        @NotBlank
        String description,
        @NotBlank
        String url) {
}
