package com.henry.gestao_de_vagas.modules.company.dto;

import lombok.Data;

@Data
public class CreateJobRequestDTO {

    private String description;
    private String level;
    private String benefits;

}
