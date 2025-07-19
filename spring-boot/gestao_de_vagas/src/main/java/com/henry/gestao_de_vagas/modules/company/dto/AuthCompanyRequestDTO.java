package com.henry.gestao_de_vagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthCompanyRequestDTO {

    @Schema(example = "company", requiredMode = RequiredMode.REQUIRED, description = "Nome da Empresa na plataforma")
    @NotNull(message = "Nome da empresa não pode ser nulo")
    @NotBlank(message = "Nome da empresa não pode ser em branco")
    private String username;

    @Schema(example = "company123456", requiredMode = RequiredMode.REQUIRED, description = "Senha da Empresa")
    @NotNull(message = "Senha não pode ser nula")
    @NotBlank(message = "Senha não pode ser em branco")
    private String password;

}
