package com.henry.gestao_de_vagas.modules.company.entities;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

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
 
    @Id@GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @NotBlank
    @Length(min = 3, max = 50, message = "nome deve ter entre 3 e 50 caracteres")
    private String name;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "nome de usuário não pode ter espaços em branco")
    private String username;
    
    @Email(message = "Email inválido")
    @Column(unique = true) // garante que o email seja unico
    private String email;

    @Pattern(regexp = "\\S+", message = "senhapassword não pode ter espaços em branco")
    private String password;

    @Length(max = 500, message = "descrição não pode ter mais de 500 caracteres")
    private String description;


    private String website;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDate createdAt;

}
