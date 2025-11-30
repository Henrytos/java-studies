package com.henry.challenge1.modules.videos.controllers.dtos;


public record UpdateVideoRequestDTO(
        String title,
        String description,
        String url) {
}