package com.reservas.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.reservas.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("SELECT r FROM Reserva r WHERE r.sala.id = :salaId AND r.status = 'ATIVA' AND r.inicio < :fim AND r.fim > :inicio")
    List<Reserva> findConflitantes(@Param("salaId") Long salaId, @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim);

    Page<Reserva> findByUsuarioId(Long usuarioId, Pageable pageable);

    Page<Reserva> findBySalaIdAndInicioGreaterThanEqualAndFimLessThanEqual(Long salaId, LocalDateTime inicio,
            LocalDateTime fim, Pageable pageable);
}