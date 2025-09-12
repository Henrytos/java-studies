package com.estudo.hexagonal_ddd.application.ports.inbound;

public interface TokenService {
    String generate(String identifier);
}
