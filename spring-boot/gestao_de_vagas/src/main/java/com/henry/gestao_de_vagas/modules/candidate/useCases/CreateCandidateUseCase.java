package com.henry.gestao_de_vagas.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.henry.gestao_de_vagas.exceptions.UserAlreadyExists;
import com.henry.gestao_de_vagas.modules.candidate.CandidateEntity;
import com.henry.gestao_de_vagas.modules.candidate.CandidateRepository;
import com.henry.gestao_de_vagas.modules.candidate.dto.CreateCandidateRequestDTO;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateEntity execute(CreateCandidateRequestDTO dto) throws UserAlreadyExists {

        this.candidateRepository.findByUsernameOrEmail(dto.getUsername(), dto.getEmail())
                .ifPresent((user) -> {
                    throw new UserAlreadyExists();
                });

        var password = this.passwordEncoder.encode(dto.getPassword());

        var candidateEntity = CandidateEntity
                .builder()
                .name(dto.getName())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(password)
                .description(dto.getDescription())
                .build();

        return this.candidateRepository.save(candidateEntity);
    }
}
