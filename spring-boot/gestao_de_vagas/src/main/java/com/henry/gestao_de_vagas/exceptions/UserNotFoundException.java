package com.henry.gestao_de_vagas.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("user not found exception");
    }

}
