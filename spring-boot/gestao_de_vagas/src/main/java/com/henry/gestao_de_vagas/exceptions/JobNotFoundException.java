package com.henry.gestao_de_vagas.exceptions;

public class JobNotFoundException extends RuntimeException {

    public JobNotFoundException() {
        super("user not found");
    }

    public JobNotFoundException(String message) {
        super(message);
    }

}