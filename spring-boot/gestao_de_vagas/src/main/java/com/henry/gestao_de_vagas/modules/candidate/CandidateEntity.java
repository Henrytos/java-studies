package com.henry.gestao_de_vagas.modules.candidate;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "candidate")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // estrategia de geração de UUID unico
    private UUID id;

    @Length(min = 3, max = 50, message = "nome deve ter entre 3 e 50 caracteres")
    @Schema(example = "jhondoe")
    private String name;

    @Pattern(regexp = "\\S+", message = "nome de usuário não pode ter espaços em branco")
    @Schema(example = "jhondoe_dev")
    private String username;

    @Column(unique = true) // garante que o email seja unico
    @Email(message = "Email inválido")
    @Schema(example = "jhondoe_dev@gmail.com")
    private String email;

    @Pattern(regexp = "\\S+", message = "senha não pode ter espaços em branco")
    @Schema(example = "jhondoe_dev123456")
    private String password;

    @Length(max = 500, message = "descrição não pode ter mais de 500 caracteres")
    @Schema(example = "Desenvolvedor back end java spring boot junior")
    private String description;

    @Schema(example = "Desenvolvedor back end java spring boot junior")
    private String curriculum;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDate createdAt;

}
