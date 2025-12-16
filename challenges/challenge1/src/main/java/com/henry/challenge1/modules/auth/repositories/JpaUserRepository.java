package com.henry.challenge1.modules.auth.repositories;

import com.henry.challenge1.modules.auth.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmailOrUsername(String email, String username);

    Optional<UserEntity> findByEmail(String email);


}
