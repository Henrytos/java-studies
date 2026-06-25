package com.reservas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservas.model.Sala;

public interface SalaRepository extends JpaRepository<Sala, Long> {

    Optional<Sala> findByNomeIgnoreCase(String nome);

    boolean existsByNomeIgnoreCaseAndIdNot(String nome, Long id);
}