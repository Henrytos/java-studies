package br.com.course_rocket.api_course_rocket_seat.modules.user.dto;

public record AuthUserResponseDTO(String token, Long expiresAt) {
}