package com.estudo.hexagonal_ddd.domain.services;

import com.estudo.hexagonal_ddd.domain.entities.User;

public class UserAuthorizationService implements AuthorizationService{
    @Override
    public boolean canManegeUsers(User user) {
        return user.isAdmin();
    }

    @Override
    public boolean canSelfDelete(User user) {
        return user.isAdmin();
    }
}
