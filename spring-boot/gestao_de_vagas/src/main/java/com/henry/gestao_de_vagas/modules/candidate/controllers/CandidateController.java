package com.henry.gestao_de_vagas.modules.candidate.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.henry.gestao_de_vagas.modules.candidate.CandidateEntity;
import com.henry.gestao_de_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import com.henry.gestao_de_vagas.modules.candidate.useCases.ProfileCandidateUseCase;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity entity) {
        try {
            var result = this.createCandidateUseCase.execute(entity);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<Object> get(HttpServletRequest request) {

        try {
            var candidateId = request.getAttribute("candidate_id");
            var profile = this.profileCandidateUseCase.execute(UUID.fromString(candidateId.toString()));

            return ResponseEntity.ok().body(profile);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

    }

}
