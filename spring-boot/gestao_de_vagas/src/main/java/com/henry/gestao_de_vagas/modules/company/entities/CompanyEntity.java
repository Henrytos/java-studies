package com.henry.gestao_de_vagas.modules.company.entities;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity(name = "company")
@Data
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Length(min = 3, max = 50, message = "nome deve ter entre 3 e 50 caracteres")
    @Schema(example = "Empresa XYZ", description = "Nome da empresa", requiredMode = RequiredMode.REQUIRED)
    private String name;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "nome de usuário não pode ter espaços em branco")
    @Schema(example = "empresaXYZ", description = "Nome de usuário da empresa", requiredMode = RequiredMode.REQUIRED)
    private String username;

    @Email(message = "Email inválido")
    @Column(unique = true) // garante que o email seja unico
    @NotBlank(message = "Email não pode estar vazio")
    @Schema(example = "empresa@xyz.com", description = "Email da empresa", requiredMode = RequiredMode.REQUIRED)
    private String email;

    @Pattern(regexp = "\\S+", message = "senhapassword não pode ter espaços em branco")
    @NotBlank(message = "Senha não pode estar vazia")
    @Schema(example = "empresaXYZ123", description = "Senha da empresa", requiredMode = RequiredMode.REQUIRED)
    private String password;

    @Length(max = 500, message = "descrição não pode ter mais de 500 caracteres")
    @Schema(example = "Descrição da empresa", description = "Descrição da empresa", requiredMode = RequiredMode.NOT_REQUIRED, maxLength = 500)
    private String description;

    @Schema(example = "https://www.empresaXYZ.com", description = "Website da empresa", requiredMode = RequiredMode.NOT_REQUIRED)
    @Length(max = 255, message = "website não pode ter mais de 255 caracteres")
    @Pattern(regexp = "^(https?://)?(www\\.)?[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}(?:/.*)?$", message = "website deve ser um URL válido")
    private String website;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDate createdAt;

}
