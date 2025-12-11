package com.henry.challenge1.modules.categories.dtos;

import com.henry.challenge1.modules.videos.controllers.dtos.VideoResponseDTO;

import java.util.List;

public record CategoryWithVideoResponseDTO(
        Long id,
        String title,
        String color,
        List<VideoResponseDTO>  videos
) {
}
