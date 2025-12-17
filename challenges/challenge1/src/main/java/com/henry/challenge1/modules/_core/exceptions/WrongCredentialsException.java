package com.henry.challenge1.modules._core.exceptions;

public class WrongCredentialsException extends HttpException {

    public WrongCredentialsException() {
        super(400, "Wrong Credentials");
    }

    public WrongCredentialsException(String message) {
        super(400, message);
    }

    public WrongCredentialsException(int statusCode, String message) {
        super(statusCode, message);
    }
}
