package com.estudo.hexagonal_ddd.domain.services;

import com.estudo.hexagonal_ddd.domain.entities.User;

public interface AuthorizationService {
    boolean canManegeUsers(User user);
    boolean canSelfDelete(User user);
}
