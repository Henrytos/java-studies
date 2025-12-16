package com.henry.challenge1.modules.auth.dtos;

public record CreateAccountResponseDTO(
        Long id,
        String username,
        String email
) {
}
