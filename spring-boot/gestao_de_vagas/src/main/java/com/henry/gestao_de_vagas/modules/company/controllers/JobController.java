package com.henry.gestao_de_vagas.modules.company.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.henry.gestao_de_vagas.modules.company.dto.CreateJobRequestDTO;
import com.henry.gestao_de_vagas.modules.company.dto.CreateJobResponseDTO;
import com.henry.gestao_de_vagas.modules.company.entities.JobEntity;
import com.henry.gestao_de_vagas.modules.company.useCases.CreateJobUseCase;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/company/job")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<Object> create(@Valid @RequestBody CreateJobRequestDTO createJobDTO,
            HttpServletRequest request) {

        var companyId = UUID.fromString(request.getAttribute("company_id").toString());

        var entity = JobEntity
                .builder()
                .description(createJobDTO.getDescription())
                .level(createJobDTO.getLevel())
                .benefits(createJobDTO.getBenefits())
                .companyId(companyId)
                .build();

        var result = this.createJobUseCase.execute(entity);

        var createJobResponseDTO = CreateJobResponseDTO
                .builder()
                .id(result.getId())
                .companyId(companyId)
                .description(result.getDescription())
                .level(result.getLevel())
                .benefits(result.getBenefits())
                .createdAt(result.getCreatedAt())
                .build();

        return ResponseEntity.ok().body(createJobResponseDTO);
    }

}
