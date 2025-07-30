package com.henry.gestao_de_vagas.exceptions;

public class JobNotFoundException extends HttpException {

    private static final int statusCode = 404; // Default status code for not found exceptions

    public JobNotFoundException() {
        super("job not found", statusCode);
    }

    public JobNotFoundException(String message) {
        super(message, statusCode);
    }

}