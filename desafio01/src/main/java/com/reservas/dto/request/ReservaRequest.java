package com.reservas.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public record ReservaRequest(
        @NotNull(message = "salaId é obrigatório") Long salaId,
        @NotNull(message = "usuarioId é obrigatório") Long usuarioId,
        @NotNull(message = "inicio é obrigatório") LocalDateTime inicio,
        @NotNull(message = "fim é obrigatório") LocalDateTime fim) {
}