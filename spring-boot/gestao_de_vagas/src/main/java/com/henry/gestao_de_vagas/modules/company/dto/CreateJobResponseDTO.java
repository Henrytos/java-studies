package com.henry.gestao_de_vagas.modules.company.dto;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Builder;

@Builder
public record CreateJobResponseDTO(
        UUID id,
        String description,
        String level,
        String benefits,
        UUID companyId,
        LocalDate createdAt) {
}
