package com.estudo.hexagonal_ddd.infrastructure.adapters.outbound.persistence.database.repository;

import com.estudo.hexagonal_ddd.domain.entities.User;
import com.estudo.hexagonal_ddd.infrastructure.adapters.outbound.persistence.database.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaUserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmail(String email);
}
