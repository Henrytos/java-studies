package com.henry.gestao_de_vagas.modules.company.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.henry.gestao_de_vagas.modules.company.dto.AuthCompanyRequestDTO;
import com.henry.gestao_de_vagas.modules.company.useCases.AuthCompanyUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Autenticação de compania na aplicação")
public class AuthCompanyController {

    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;

    @PostMapping("/company")
    @Operation(summary = "Autenticação de companias", description = "Autenticação de companias por meio dos campos username e password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)), description = "Autenticação realizada com sucesso"),
            @ApiResponse(responseCode = "401", content = @Content(mediaType = "application/json", schema = @Schema(defaultValue = "Usuário não encontrado")), description = "Não autorizado"),
            @ApiResponse(responseCode = "401", content = @Content(mediaType = "application/json", schema = @Schema(defaultValue = "Usuário não encontrado")), description = "Não autorizado"),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(defaultValue = "Senha incorreta")), description = "Senha incorreta"),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(defaultValue = "Erro interno do servidor")), description = "Erro interno do servidor")
    })
    public ResponseEntity<Object> auth(@RequestBody AuthCompanyRequestDTO authCompanyDTO) {
        try {
            var token = this.authCompanyUseCase.execute(authCompanyDTO);

            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
