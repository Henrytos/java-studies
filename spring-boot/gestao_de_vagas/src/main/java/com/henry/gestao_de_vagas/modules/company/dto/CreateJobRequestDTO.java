package com.henry.gestao_de_vagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobRequestDTO {

    @Schema(example = "Desenvolvedor Java", requiredMode = Schema.RequiredMode.REQUIRED, description = "Título da vaga")
    private String description;

    @Schema(example = "Desenvolvedor Java Pleno", requiredMode = Schema.RequiredMode.REQUIRED, description = "Descrição da vaga")
    private String level;

    @Schema(example = "Vale transporte, Vale refeição", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "Benefícios da vaga")
    private String benefits;

}
