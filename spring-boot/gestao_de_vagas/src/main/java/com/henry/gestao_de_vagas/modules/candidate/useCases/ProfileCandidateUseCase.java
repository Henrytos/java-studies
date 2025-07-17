package com.henry.gestao_de_vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henry.gestao_de_vagas.exceptions.UserNotFoundException;
import com.henry.gestao_de_vagas.modules.candidate.CandidateRepository;
import com.henry.gestao_de_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID candidateId) throws UserNotFoundException {
        var candidate = this.candidateRepository
                .findById(candidateId)
                .orElseThrow(() -> {
                    throw new UserNotFoundException();
                });

        ProfileCandidateResponseDTO candidateDto = ProfileCandidateResponseDTO
                .builder()
                .id(candidate.getId())
                .name(candidate.getName())
                .username(candidate.getUsername())
                .email(candidate.getEmail())
                .description(candidate.getDescription())
                .build();

        return candidateDto;
    }

}
