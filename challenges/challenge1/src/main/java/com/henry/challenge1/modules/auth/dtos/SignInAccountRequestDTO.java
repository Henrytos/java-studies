package com.henry.challenge1.modules.auth.dtos;

public record SignInAccountRequestDTO(
        String email,
        String password
) {
}
