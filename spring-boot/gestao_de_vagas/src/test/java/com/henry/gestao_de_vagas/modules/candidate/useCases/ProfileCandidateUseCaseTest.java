package com.henry.gestao_de_vagas.modules.candidate.useCases;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.henry.gestao_de_vagas.exceptions.UserNotFoundException;
import com.henry.gestao_de_vagas.factories.entities.MakeCandidateEntityFactory;
import com.henry.gestao_de_vagas.modules.candidate.CandidateRepository;

@ExtendWith(MockitoExtension.class)
public class ProfileCandidateUseCaseTest {

    @InjectMocks
    private ProfileCandidateUseCase profileCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Test
    @DisplayName("should be able get profile candidate")
    public void should_be_able_get_profile_candidate() {

        var candidateId = UUID.randomUUID();
        var candidate = MakeCandidateEntityFactory.staticMakeFactorEntity();
        candidate.setId(candidateId);

        when(this.candidateRepository.findById(candidateId)).thenReturn(Optional.of(candidate));

        var result = this.profileCandidateUseCase.execute(candidateId);
        Assertions.assertThat(result).hasFieldOrProperty("id");

        assertEquals(result.getId(), candidate.getId());
        assertEquals(result.getName(), candidate.getName());
        assertEquals(result.getUsername(), candidate.getUsername());
        assertEquals(result.getEmail(), candidate.getEmail());
        assertEquals(result.getDescription(), candidate.getDescription());

    }

    @Test
    @DisplayName("should not be able get profile candidate if candidate not found")
    public void should_not_be_able_get_profile_candidate_if_candidate_not_found() {

        try {
            this.profileCandidateUseCase.execute(null);
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
    }

}
