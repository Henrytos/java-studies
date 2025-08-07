package com.log.dev.api.exceptions;

public abstract class HttpException extends RuntimeException {
    private int statusCode;

    public HttpException(int statusCode, String message) {
        super(message);

        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
