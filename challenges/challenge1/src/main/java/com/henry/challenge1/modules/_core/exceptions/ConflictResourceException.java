package com.henry.challenge1.modules._core.exceptions;

public class ConflictResourceException extends HttpException {

    public ConflictResourceException() {
        super(409, "Conflict Resource Exception");
    }

    public ConflictResourceException(String message) {
        super(409, message);
    }

    public ConflictResourceException(int statusCode, String message) {
        super(statusCode, message);
    }
}
