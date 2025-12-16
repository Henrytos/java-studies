package com.henry.challenge1.modules.auth.dtos;

public record SignInAccountResponseDTO(
        String token,
        Long expiresAt
) {
}
