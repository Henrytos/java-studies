package com.estudo.hexagonal_ddd.application.ports.inbound;

import com.estudo.hexagonal_ddd.application.dtos.RegisterUserDTO;

public interface UserServicePort {
     void register(RegisterUserDTO dto);
     String authenticate(String email, String password);
}
