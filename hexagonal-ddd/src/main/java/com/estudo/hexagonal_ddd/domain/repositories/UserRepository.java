package com.estudo.hexagonal_ddd.domain.repositories;

import com.estudo.hexagonal_ddd.domain.entities.User;

import java.util.Optional;
import java.util.UUID;


public interface UserRepository {
     void save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findById(UUID adminId);
    void delete(UUID userId);
}
