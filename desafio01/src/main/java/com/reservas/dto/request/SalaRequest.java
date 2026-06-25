package com.reservas.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record SalaRequest(
        @NotBlank(message = "nome é obrigatório") String nome,
        @Positive(message = "capacidade deve ser maior que zero") int capacidade,
        Boolean ativa) {
}