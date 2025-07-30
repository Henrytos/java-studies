package com.henry.gestao_de_vagas.exceptions;

public class CompanyNotFoundException extends HttpException {
    private static final int statusCode = 404; // Default status code for not found exceptions

    public CompanyNotFoundException() {
        super("company not found exception", statusCode);
    }

    public CompanyNotFoundException(String message) {
        super(message, statusCode);
    }

}
