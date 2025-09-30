package com.estudo.hexagonal_ddd.application.ports.outbound;

import com.estudo.hexagonal_ddd.domain.entities.value_objects.Role;

public interface TokenServicePort {
    String generate(String identifier);
    String generate(String identifier, Role... roles);

}
