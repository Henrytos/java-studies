package com.estudo.hexagonal_ddd.domain.repositories;

import com.estudo.hexagonal_ddd.domain.entities.User;

import java.util.Optional;


public interface UserRepository {
     void save(User user);
    Optional<User> findByEmail(String email);

}
