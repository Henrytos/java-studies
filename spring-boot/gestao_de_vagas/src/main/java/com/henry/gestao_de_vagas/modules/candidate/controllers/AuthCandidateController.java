package com.henry.gestao_de_vagas.modules.candidate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.henry.gestao_de_vagas.exceptions.ErrorMessageDto;
import com.henry.gestao_de_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import com.henry.gestao_de_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import com.henry.gestao_de_vagas.modules.candidate.useCases.AuthCandidateUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Autenticação de candidato na aplicação")
public class AuthCandidateController {

    @Autowired
    private AuthCandidateUseCase authCandidateUseCase;

    @PostMapping("/candidate")
    @Operation(summary = "Autenticação de candidatos", description = "Autenticação de candidatos por meio dos campos username e password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = AuthCandidateResponseDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(defaultValue = "username/password incorrect"), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorMessageDto.class)), mediaType = "application/json"))
    })
    public ResponseEntity<Object> auth(@Valid @RequestBody AuthCandidateRequestDTO authCandidateRequestDTO) {
        var token = this.authCandidateUseCase.execute(authCandidateRequestDTO);

        return ResponseEntity.ok().body(token);
    }

}
