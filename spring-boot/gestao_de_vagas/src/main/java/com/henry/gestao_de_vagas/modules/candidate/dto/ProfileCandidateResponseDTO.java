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

    @Schema(example = "47c84d39-4b3d-4d93-b357-0206cecd199b", type = "uuid")
    private UUID id;

    @Schema(example = "jhondoe")
    private String username;

    @Schema(example = "jhondoe_dev")
    private String name;

    @Schema(example = "jhondoe_dev@gmail.com")
    private String email;

    @Schema(example = "Desenvolvedor spring boot")
    private String description;

}
