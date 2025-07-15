package com.henry.gestao_de_vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henry.gestao_de_vagas.exceptions.JobNotFoundException;
import com.henry.gestao_de_vagas.exceptions.UseNotFoundException;
import com.henry.gestao_de_vagas.modules.candidate.CandidateRepository;
import com.henry.gestao_de_vagas.modules.company.repositories.JobRepository;

@Service
public class ApplyJobUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    public void execute(UUID candidateId, UUID jobId) throws UseNotFoundException, JobNotFoundException {
        // verificar se existe candidato
        this.candidateRepository.findById(candidateId).orElseThrow(() -> {
            throw new UseNotFoundException();
        });

        // verificar se existe vaga
        this.jobRepository.findById(jobId).orElseThrow(() -> {
            throw new JobNotFoundException();
        });

        // criar aplicação da vaga
    }
}
