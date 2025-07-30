package com.henry.gestao_de_vagas.exceptions;

public class HttpException extends RuntimeException {

    private final int statusCode;

    public HttpException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public static int getStatusCodeStatic() {
        return 500; // Default status code, can be overridden by subclasses
    }

}
