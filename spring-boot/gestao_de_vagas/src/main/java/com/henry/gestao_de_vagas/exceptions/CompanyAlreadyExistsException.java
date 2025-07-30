package com.henry.gestao_de_vagas.exceptions;

public class CompanyAlreadyExistsException extends HttpException {

    private static final int statusCode = 400; // Default status code for bad request exceptions

    public CompanyAlreadyExistsException() {
        super("company already exists", statusCode);
    }

    public CompanyAlreadyExistsException(String message) {
        super(message, statusCode);
    }

}
