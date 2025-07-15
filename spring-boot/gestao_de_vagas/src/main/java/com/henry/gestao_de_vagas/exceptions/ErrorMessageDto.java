package com.henry.gestao_de_vagas.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageDto {

    @Schema(example = "formato do email invalído", description = "Mensagem de erro que descreve o problema encontrado", requiredMode = Schema.RequiredMode.REQUIRED)
    private String message;
    @Schema(example = "email", description = "Campo que gerou o erro", requiredMode = Schema.RequiredMode.REQUIRED)
    private String field;

}
