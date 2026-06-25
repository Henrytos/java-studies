package com.reservas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservas.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id);
}