package com.henry.gestao_de_vagas.modules.company.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.henry.gestao_de_vagas.exceptions.ErrorMessageDto;
import com.henry.gestao_de_vagas.modules.company.dto.CreateCompanyRequestDTO;
import com.henry.gestao_de_vagas.modules.company.entities.CompanyEntity;
import com.henry.gestao_de_vagas.modules.company.useCases.CreateCompanyUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/company")
@Tag(name = "Compania", description = "Gerenciamento de companias")
public class CompanyController {

    @Autowired
    private CreateCompanyUseCase createCompanyUseCase;

    @PostMapping("/")
    @Operation(summary = "Criação de companias", description = "Criação de companias na plataforma")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = CompanyEntity.class), mediaType = "application/json"), description = "Compania criada com sucesso"),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(defaultValue = "Usuário já existe"), mediaType = "application/json"), description = "já existentes"),
            @ApiResponse(responseCode = "400", description = "erro ao criar usuario(não está atendendo as regras)", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorMessageDto.class)), mediaType = "application/json")),
    })
    public ResponseEntity<Object> createCompany(@Valid @RequestBody CreateCompanyRequestDTO dto) {
        CompanyEntity companyEntity = CompanyEntity.builder()
                .name(dto.getName())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .description(dto.getDescription())
                .website(dto.getWebsite())
                .build();

        CompanyEntity result = this.createCompanyUseCase.execute(companyEntity);

        return ResponseEntity.ok(result);
    }

}
