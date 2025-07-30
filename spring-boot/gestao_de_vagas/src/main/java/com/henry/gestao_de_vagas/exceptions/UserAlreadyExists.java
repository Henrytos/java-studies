package com.henry.gestao_de_vagas.exceptions;

public class UserAlreadyExists extends HttpException {

    private static final int statusCode = 400; // Default status code for bad request exceptions

    public UserAlreadyExists() {
        super("user already exists", statusCode);
    }

    public UserAlreadyExists(String message) {
        super(message, statusCode);
    }

}
