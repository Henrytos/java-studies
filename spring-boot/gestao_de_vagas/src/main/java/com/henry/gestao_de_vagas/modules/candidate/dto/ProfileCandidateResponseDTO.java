package com.henry.gestao_de_vagas.modules.candidate.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileCandidateResponseDTO {

    private UUID id;

    @Schema(example = "jhondoe", description = "Nome do candidato", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @Schema(example = "jhondoe_dev", description = "Nome do candidato", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(example = "jhondoe_dev@gmail.com", description = "Email do candidato", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Schema(example = "Desenvolvedor spring boot", description = "Descrição do candidato", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;

}
