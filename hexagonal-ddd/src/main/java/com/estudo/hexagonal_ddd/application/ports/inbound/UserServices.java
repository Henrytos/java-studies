package com.estudo.hexagonal_ddd.application.ports.inbound;

import com.estudo.hexagonal_ddd.application.dtos.RegisterUserDTO;
import com.estudo.hexagonal_ddd.domain.entities.User;

public interface UserServices {
     void register(RegisterUserDTO dto);
     String authenticate(String email, String password);
}
