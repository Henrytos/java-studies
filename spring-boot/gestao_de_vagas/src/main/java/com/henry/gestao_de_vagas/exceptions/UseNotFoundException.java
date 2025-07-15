package com.henry.gestao_de_vagas.exceptions;

public class UseNotFoundException extends RuntimeException {

    public UseNotFoundException() {
        super("user not found");
    }

    public UseNotFoundException(String message) {
        super(message);
    }

}
