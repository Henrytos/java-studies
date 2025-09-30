package com.estudo.hexagonal_ddd.application.ports.outbound;

public interface HashEncoder {
    String encode(String plan);
    String encode(String plan, Integer salt);

    Boolean valid(String plan, String hash);
}
