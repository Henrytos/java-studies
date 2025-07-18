package com.henry.gestao_de_vagas.modules.useCases;

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
import org.springframework.security.crypto.password.PasswordEncoder;

import com.henry.gestao_de_vagas.exceptions.UserAlreadyExists;
import com.henry.gestao_de_vagas.factories.entities.MakeCandidateEntityFactory;
import com.henry.gestao_de_vagas.modules.candidate.CandidateEntity;
import com.henry.gestao_de_vagas.modules.candidate.CandidateRepository;
import com.henry.gestao_de_vagas.modules.candidate.dto.CreateCandidateRequestDTO;
import com.henry.gestao_de_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import com.henry.gestao_de_vagas.utils.UtilTest;

@ExtendWith(MockitoExtension.class)
public class CreateCandidateUseCaseTest {

    @InjectMocks
    private CreateCandidateUseCase createCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("should be a ble create a new candidate")
    public void should_be_a_ble_create_a_new_candidate() {
        var dto = makeCreateCandidateRequest();

        when(this.candidateRepository.findByUsernameOrEmail(dto.getUsername(), dto.getEmail()))
                .thenReturn(Optional.ofNullable(null));

        when(this.passwordEncoder.encode(dto.getPassword()))
                .thenReturn(dto.getPassword());

        var candidateEntity = requestToEntity(dto);
        var candidateEntitySave = requestToEntity(dto);
        candidateEntitySave.setId(UUID.randomUUID());

        when(this.candidateRepository.save(candidateEntity))
                .thenReturn(candidateEntitySave);

        var result = this.createCandidateUseCase.execute(dto);

        Assertions.assertThat(result).hasFieldOrProperty("id");
    }

    @Test
    @DisplayName("should not be a ble create a new candidate if candidate exists by username")
    public void should_be_a_ble_create_a_new_candidate_if_candidate_exists_by_username() {
        var dto = makeCreateCandidateRequest();
        var candidateExists = MakeCandidateEntityFactory.staticMakeFactorEntity();
        candidateExists.setUsername(dto.getUsername());

        when(this.candidateRepository.findByUsernameOrEmail(dto.getUsername(), dto.getEmail()))
                .thenReturn(Optional.of(candidateExists));

        try {
            this.createCandidateUseCase.execute(dto);
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(UserAlreadyExists.class);
        }
    }

    @Test
    @DisplayName("should not be a ble create a new candidate if candidate exists by email")
    public void should_be_a_ble_create_a_new_candidate_if_candidate_exists_by_email() {
        var dto = makeCreateCandidateRequest();
        var candidateExists = MakeCandidateEntityFactory.staticMakeFactorEntity();
        candidateExists.setEmail(dto.getEmail());

        when(this.candidateRepository.findByUsernameOrEmail(dto.getUsername(), dto.getEmail()))
                .thenReturn(Optional.of(candidateExists));

        try {
            this.createCandidateUseCase.execute(dto);
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(UserAlreadyExists.class);
        }
    }

    static CreateCandidateRequestDTO makeCreateCandidateRequest() {
        var faker = UtilTest.faker();

        var dto = CreateCandidateRequestDTO.builder().name(faker.name().name())
                .username(faker.name().username()).email(faker.internet().emailAddress())
                .description(faker.lorem().characters()).password(faker.lorem().characters(6, 12)).build();
        return dto;
    }

    static CandidateEntity requestToEntity(CreateCandidateRequestDTO dto) {
        var candidate = CandidateEntity.builder().name(dto.getName())
                .username(dto.getUsername()).email(dto.getEmail())
                .description(dto.getDescription()).password(dto.getPassword()).build();

        return candidate;
    }
}
