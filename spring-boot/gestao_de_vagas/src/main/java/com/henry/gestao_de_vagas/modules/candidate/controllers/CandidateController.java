package com.henry.gestao_de_vagas.modules.candidate.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.henry.gestao_de_vagas.exceptions.ErrorMessageDto;
import com.henry.gestao_de_vagas.modules.candidate.CandidateEntity;
import com.henry.gestao_de_vagas.modules.candidate.dto.CreateCandidateRequestDTO;
import com.henry.gestao_de_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import com.henry.gestao_de_vagas.modules.candidate.useCases.ListAllJobsByFilterUseCase;
import com.henry.gestao_de_vagas.modules.candidate.useCases.ProfileCandidateUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @Autowired
    private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

    @Tag(name = "Candidato")
    @Operation(summary = "criação de perfil", description = "criação de perfil de candidato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "criação de um perfil de candidato", content = @Content(schema = @Schema(implementation = CandidateEntity.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "usuario já existente", content = @Content(schema = @Schema(defaultValue = "Usuário já existe"), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "erro ao criar usuario(não está atendendo as regras)", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorMessageDto.class)), mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "erro interno do servidor", content = @Content(array = @ArraySchema(schema = @Schema(defaultValue = "error example")), mediaType = "application/json"))
    })
    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CreateCandidateRequestDTO candidateRequestDTO) {
        try {
            var result = this.createCandidateUseCase.execute(candidateRequestDTO);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> get(HttpServletRequest request) {

        try {
            var candidateId = request.getAttribute("candidate_id");
            var profile = this.profileCandidateUseCase.execute(UUID.fromString(candidateId.toString()));

            return ResponseEntity.ok().body(profile);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

    }

    @GetMapping("/jobs")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Tag(name = "Candidato", description = "Rota para manipular candidatos")
    @Operation(summary = "Listagem de vagas", description = "Lista todas as vagas filtradas por um critério de filtro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de vagas filtradas com sucesso", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CandidateEntity.class))))
    })
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Object> listAllJobsByFilter(
            @RequestParam String filter) {
        try {
            var jobs = this.listAllJobsByFilterUseCase.execute(filter);
            return ResponseEntity.ok().body(jobs);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }

}
