package com.henry.gestao_de_vagas.modules.candidate.dto;

public record ResponseMessageDTO(String message) {
    public ResponseMessageDTO(String message) {
        this.message = message;
    }
}
