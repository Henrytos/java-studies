package com.henry.gestao_de_vagas.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AuthCandidateRequestDTO {

    @Schema(example = "jhondoe_dev", requiredMode = RequiredMode.REQUIRED, description = "Nome do Candidato na plataforma")
    @NotNull
    @NotBlank
    @Pattern(regexp = "^\\S+$", message = "não são permitidos espeços em branco no username")
    private String username;

    @Schema(example = "jhondoe_dev123456", requiredMode = RequiredMode.REQUIRED, description = "Senha do Candidato")
    @NotNull
    @NotBlank
    @Pattern(regexp = "^\\S+$", message = "não são permitidos espeços em branco na senha")
    private String password;

}
