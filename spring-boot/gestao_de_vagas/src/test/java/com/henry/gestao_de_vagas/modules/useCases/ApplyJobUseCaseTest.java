package com.henry.gestao_de_vagas.modules.useCases;

import static org.mockito.Mockito.when;

import java.util.Optional;

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
import com.henry.gestao_de_vagas.modules.candidate.useCases.ApplyJobUseCase;
import com.henry.gestao_de_vagas.modules.company.repositories.JobRepository;

@ExtendWith(MockitoExtension.class) // sempre extender quando for mocar com mokito
public class ApplyJobUseCaseTest {

    @InjectMocks // alvo do teste unitariosendo mokado(mokado = simulado | estatico )
    private ApplyJobUseCase applyJobUseCase;

    @Mock // dependencias de sistema em execução
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

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

}
