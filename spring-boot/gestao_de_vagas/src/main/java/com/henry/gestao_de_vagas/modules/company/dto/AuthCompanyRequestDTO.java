package com.henry.gestao_de_vagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthCompanyRequestDTO {

    @Schema(example = "company", requiredMode = RequiredMode.REQUIRED, description = "Nome da Empresa na plataforma")
    private String username;

    @Schema(example = "company123456", requiredMode = RequiredMode.REQUIRED, description = "Senha da Empresa")
    private String password;

}
