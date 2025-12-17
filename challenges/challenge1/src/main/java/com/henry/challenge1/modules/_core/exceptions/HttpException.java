package com.henry.challenge1.modules._core.exceptions;

import lombok.Getter;

public abstract class HttpException extends RuntimeException {
    @Getter
    private final int statusCode;
    @Getter
    private final String message;


    public HttpException(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
