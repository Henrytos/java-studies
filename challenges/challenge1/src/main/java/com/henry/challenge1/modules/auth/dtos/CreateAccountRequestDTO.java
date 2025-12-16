package com.henry.challenge1.modules.auth.dtos;

public record CreateAccountRequestDTO(
        String username,
        String email,
        String password
) {
}
