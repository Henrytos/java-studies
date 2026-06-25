package com.reservas.dto.response;

import java.time.LocalDateTime;

import com.reservas.model.enums.ReservaStatus;

public record ReservaResponse(Long id, SalaResponse sala, UsuarioResponse usuario, LocalDateTime inicio,
        LocalDateTime fim, ReservaStatus status) {
}