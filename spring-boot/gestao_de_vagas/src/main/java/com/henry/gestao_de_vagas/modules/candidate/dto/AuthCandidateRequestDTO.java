package com.henry.gestao_de_vagas.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AuthCandidateRequestDTO {

    @Schema(example = "jhondoe_dev")
    @NotNull
    @NotBlank
    @Pattern(regexp = "^\\S+$", message = "não são permitidos espeços em branco no username")
    private String username;

    @Schema(example = "jhonDoe123")
    @NotNull
    @NotBlank
    @Pattern(regexp = "^\\S+$", message = "não são permitidos espeços em branco na senha")
    private String password;

}
