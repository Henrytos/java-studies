package com.estudo.hexagonal_ddd.application.ports.inbound;

import com.estudo.hexagonal_ddd.application.dtos.RegisterUserDTO;
import com.estudo.hexagonal_ddd.domain.entities.User;
import com.estudo.hexagonal_ddd.domain.entities.value_objects.Role;

import java.util.UUID;

public interface UserServicePort {
     void register(RegisterUserDTO dto);
     String authenticate(String email, String password);
     void delete(UUID adminId, UUID userId);
     User getAsAdmin(UUID adminId, UUID userId);
    User getProfile(UUID userId);
    void update(UUID adminId, User user);
}
