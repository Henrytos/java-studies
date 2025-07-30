package com.henry.gestao_de_vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henry.gestao_de_vagas.exceptions.JobNotFoundException;
import com.henry.gestao_de_vagas.exceptions.UserNotFoundException;
import com.henry.gestao_de_vagas.modules.candidate.CandidateRepository;
import com.henry.gestao_de_vagas.modules.candidate.entities.ApplyJobEntity;
import com.henry.gestao_de_vagas.modules.candidate.repositories.ApplyJobRepository;
import com.henry.gestao_de_vagas.modules.company.repositories.JobRepository;

@Service
public class ApplyJobUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public ApplyJobEntity execute(UUID candidateId, UUID jobId) throws UserNotFoundException, JobNotFoundException {
        // verificar se existe candidato
        this.candidateRepository.findById(candidateId).orElseThrow(() -> {
            throw new UserNotFoundException();
        });

        // verificar se existe vaga
        this.jobRepository.findById(jobId).orElseThrow(() -> {
            throw new JobNotFoundException();
        });

        // criar aplicação da vaga
        var applyJob = ApplyJobEntity.builder().candidateId(candidateId).jobId(jobId).build();
        applyJob = this.applyJobRepository.save(applyJob);

        return applyJob;
    }
}
