package com.henry.gestao_de_vagas.modules.candidates;

import java.util.UUID;

import lombok.Data;

@Data
public class CandidateEntity {
    private UUID id;
    private String username;
    private String name;
    private String email;
    private String password;
    private String curriculum;
}
