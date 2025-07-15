package com.henry.gestao_de_vagas.modules.candidate.dto;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCandidateRequestDTO {

    @Schema(example = "jhondoe", requiredMode = RequiredMode.REQUIRED, minLength = 3, maxLength = 50, description = "Nome do Candidato")
    @Length(min = 3, max = 50, message = "nome deve ter entre 3 e 50 caracteres")
    private String name;

    @Schema(example = "jhondoe_dev", requiredMode = RequiredMode.REQUIRED, description = "Nome do Candidato na plataforma")
    @Pattern(regexp = "\\S+", message = "nome de usuário não pode ter espaços em branco")
    private String username;

    @Schema(example = "jhondoe_dev@gmail.com", requiredMode = RequiredMode.REQUIRED, description = "Email do Candidato")
    @Column(unique = true) // garante que o email seja unico
    @Email(message = "Email inválido")
    private String email;

    @Schema(example = "jhondoe_dev123456", requiredMode = RequiredMode.REQUIRED, description = "Senha do Candidato")
    @Pattern(regexp = "\\S+", message = "senha não pode ter espaços em branco")
    private String password;

    @Schema(example = "hello my name is jhon doe, from california", requiredMode = RequiredMode.REQUIRED, maxLength = 500)
    @Length(max = 500, message = "descrição não pode ter mais de 500 caracteres")
    private String description;
}
