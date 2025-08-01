package com.henry.gestao_de_vagas.modules.candidate;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
// O @Repository é opcional aqui, pois JpaRepository já é um componente do Spring Data, JpaRepository <Entidate, Identificador unico>
public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {

    Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);

    Optional<CandidateEntity> findByUsername(String username);

}
