package com.henry.challenge1.modules.videos.controllers.dtos;

public record VideoResponseDTO(
        Long id,
        String title,
        String description,
        String url
) {
}
