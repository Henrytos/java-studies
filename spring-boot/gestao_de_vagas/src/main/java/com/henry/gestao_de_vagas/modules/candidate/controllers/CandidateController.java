package com.henry.gestao_de_vagas.modules.candidate.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.henry.gestao_de_vagas.exceptions.ErrorMessageDto;
import com.henry.gestao_de_vagas.modules.candidate.CandidateEntity;
import com.henry.gestao_de_vagas.modules.candidate.dto.CreateCandidateRequestDTO;
import com.henry.gestao_de_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import com.henry.gestao_de_vagas.modules.candidate.dto.ResponseMessageDTO;
import com.henry.gestao_de_vagas.modules.candidate.entities.ApplyJobEntity;
import com.henry.gestao_de_vagas.modules.candidate.useCases.ApplyJobUseCase;
import com.henry.gestao_de_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import com.henry.gestao_de_vagas.modules.candidate.useCases.ListAllJobsByFilterUseCase;
import com.henry.gestao_de_vagas.modules.candidate.useCases.ProfileCandidateUseCase;
import com.henry.gestao_de_vagas.modules.company.entities.JobEntity;

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
@Tag(name = "Candidato", description = "Rotas de manipualção de candidato")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @Autowired
    private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

    @Autowired
    private ApplyJobUseCase applyJobUseCase;

    @PostMapping("/")
    @Operation(summary = "Criação de perfil", description = "Funcionalide De Criação De Candidato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "criação de um perfil de candidato", content = @Content(schema = @Schema(implementation = CandidateEntity.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "usuario já existente", content = @Content(schema = @Schema(defaultValue = "Usuário já existe"), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "erro ao criar usuario(não está atendendo as regras)", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorMessageDto.class)), mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "erro interno do servidor")
    })
    public ResponseEntity<Object> create(@Valid @RequestBody CreateCandidateRequestDTO candidateRequestDTO) {
        try {
            CandidateEntity result = this.createCandidateUseCase.execute(candidateRequestDTO);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            ResponseMessageDTO message = new ResponseMessageDTO(e.getMessage());
            return ResponseEntity.internalServerError().body(message);
        }
    }

    @GetMapping("/profile")
    @Operation(summary = "Obter perfil", description = "obter perfil do candidato autenticado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ProfileCandidateResponseDTO.class), mediaType = "application/json"), description = "perfil do usuario autenticado"),
            @ApiResponse(responseCode = "403", description = "rota não permitida"),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(defaultValue = "user not found")), description = "usuario não autorizado")
    })
    @SecurityRequirement(name = "jwt_auth")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> get(HttpServletRequest request) {

        try {
            var candidateId = request.getAttribute("candidate_id");
            ProfileCandidateResponseDTO profile = this.profileCandidateUseCase
                    .execute(UUID.fromString(candidateId.toString()));

            return ResponseEntity.ok().body(profile);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

    }

    @GetMapping("/jobs")
    @Operation(summary = "Listagem de vagas", description = "Lista todas as vagas filtradas por um critério de filtro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de vagas filtradas com sucesso", content = @Content(array = @ArraySchema(schema = @Schema(implementation = JobEntity.class)), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "rota não autorizado"),
            @ApiResponse(responseCode = "403", description = "rota não permitida"),
            @ApiResponse(responseCode = "500", description = "erro interno do servidor"),
    })
    @SecurityRequirement(name = "jwt_auth")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> listAllJobsByFilter(
            @RequestParam String filter) {
        try {
            var jobs = this.listAllJobsByFilterUseCase.execute(filter);
            return ResponseEntity.ok().body(jobs);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }

    @PostMapping("/apply/job")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Criação de candidatura", description = "Rota de criação de candidatura de um candidato com uma vaga")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ApplyJobEntity.class))),
            @ApiResponse(responseCode = "403"),
            @ApiResponse(responseCode = "401")
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> applyJob(@RequestBody String jobId, HttpServletRequest request) {
        var candidateId = request.getAttribute("candidate_id");

        try {
            var result = this.applyJobUseCase.execute(UUID.fromString(candidateId.toString()), UUID.fromString(jobId));

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
