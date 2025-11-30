package com.henry.challenge1.modules.videos.controllers.dtos;

public record RegisterVideoResponseDTO(
        Long id,
        String title,
        String description,
        String url
) {
}
