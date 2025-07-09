package com.henry.gestao_de_vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.henry.gestao_de_vagas.modules.candidate.CandidateRepository;
import com.henry.gestao_de_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID candidateId) throws UsernameNotFoundException {
        var candidate = this.candidateRepository
                .findById(candidateId)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("user not found");
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
