package com.henry.gestao_de_vagas.modules.candidate;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CandidateEntity {
    
    private UUID id;

    @Length(min = 3, max = 50, message = "nome deve ter entre 3 e 50 caracteres")
    private String name;

    @Pattern(regexp = "\\S+", message = "nome de usuário não pode ter espaços em branco")
    private String username;

    @Email(message = "Email inválido")
    private String email;

    @Length(min = 6, max = 20, message = "senha deve ter entre 6 e 20 caracteres")
    @Pattern(regexp = "\\S+", message = "password não pode ter espaços em branco")
    private String password;

    @Length(max = 500, message = "descrição não pode ter mais de 500 caracteres")
    private String description;

    private String curriculum;

}
