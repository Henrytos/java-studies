package com.henry.gestao_de_vagas.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageDto {

    @Schema(example = "formato do email invalído")
    private String message;
    @Schema(example = "email")
    private String field;

}
