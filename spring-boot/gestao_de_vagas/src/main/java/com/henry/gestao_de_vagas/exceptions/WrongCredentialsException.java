package com.henry.gestao_de_vagas.exceptions;

public class WrongCredentialsException extends HttpException {

    private static final int statusCode = 401; // Default status code for unauthorized exceptions

    public WrongCredentialsException() {
        super("wrong credentials", statusCode);
    }

    public WrongCredentialsException(String message) {
        super(message, statusCode);
    }

}
