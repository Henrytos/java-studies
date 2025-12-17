package com.henry.challenge1.configs.dtos;

public record ErrorMessageResponseDTO(
        String message,
        int statusCode
) {
}
