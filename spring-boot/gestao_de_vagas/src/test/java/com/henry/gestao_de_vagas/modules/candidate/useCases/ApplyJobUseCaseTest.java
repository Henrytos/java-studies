package com.henry.gestao_de_vagas.modules.candidate.useCases;

import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.henry.gestao_de_vagas.exceptions.JobNotFoundException;
import com.henry.gestao_de_vagas.exceptions.UseNotFoundException;
import com.henry.gestao_de_vagas.modules.candidate.CandidateEntity;
import com.henry.gestao_de_vagas.modules.candidate.CandidateRepository;
import com.henry.gestao_de_vagas.modules.candidate.entities.ApplyJobEntity;
import com.henry.gestao_de_vagas.modules.candidate.repositories.ApplyJobRepository;
import com.henry.gestao_de_vagas.modules.company.entities.JobEntity;
import com.henry.gestao_de_vagas.modules.company.repositories.JobRepository;

@ExtendWith(MockitoExtension.class) // sempre extender quando for mocar com mokito
public class ApplyJobUseCaseTest {

    @InjectMocks // alvo do teste unitariosendo mokado(mokado = simulado | estatico )
    private ApplyJobUseCase applyJobUseCase;

    @Mock // dependencias de sistema em execução
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName("Should not be possible to create candidacy if candidate is not found")
    public void should_not_be_possible_to_apply_job_with_candidate_not_found() {
        try {
            applyJobUseCase.execute(null, null);
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(UseNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should not be possible to create candidacy if job is not found")
    public void should_not_be_possible_to_apply_job_with_job_not_found() {
        var candidate = new CandidateEntity();
        when(this.candidateRepository.findById(candidate.getId())).thenReturn(Optional.of(candidate));

        try {
            applyJobUseCase.execute(candidate.getId(), null);
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(JobNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should be possible to create apply job")
    public void should_be_possible_to_create_apply_job() {

        var candidateId = UUID.randomUUID();
        var jobId = UUID.randomUUID();

        when(this.candidateRepository.findById(candidateId)).thenReturn(Optional.of(new CandidateEntity()));
        when(this.jobRepository.findById(jobId)).thenReturn(Optional.of(new JobEntity()));

        var applyJobEntity = ApplyJobEntity.builder().candidateId(candidateId).jobId(jobId)
                .build();
        var applyJobEntityCReated = ApplyJobEntity.builder().id(UUID.randomUUID()).candidateId(candidateId).jobId(jobId)
                .build();

        when(this.applyJobRepository.save(applyJobEntity)).thenReturn(applyJobEntityCReated);

        var result = applyJobUseCase.execute(candidateId, jobId);
        Assertions.assertThat(result).hasFieldOrProperty("id");
    }

}
