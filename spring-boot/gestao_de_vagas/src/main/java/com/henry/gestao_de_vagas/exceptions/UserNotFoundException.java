package com.henry.gestao_de_vagas.exceptions;

public class UserNotFoundException extends HttpException {

    private static final int statusCode = 404; // Default status code for not found exceptions

    public UserNotFoundException() {
        super("user not found", statusCode);
    }

    public UserNotFoundException(String message) {
        super(message, statusCode);
    }

}
