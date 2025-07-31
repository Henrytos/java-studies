package com.log.dev.api.exceptions;

public class WrongCredentialsException extends HttpException {
    public WrongCredentialsException() {
        super(401, "Wrong credentials");
    }

    public WrongCredentialsException(String message) {
        super(401, message);
    }
}
