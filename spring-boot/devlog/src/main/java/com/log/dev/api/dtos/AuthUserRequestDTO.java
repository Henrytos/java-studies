package com.log.dev.api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthUserRequestDTO {

    @NotBlank
    @NotNull
    @Schema(example = "henry", requiredMode = RequiredMode.REQUIRED)
    private String username;

    @NotBlank
    @NotNull
    @Schema(example = "12345678", requiredMode = RequiredMode.REQUIRED, minLength = 6)
    private String password;
}
