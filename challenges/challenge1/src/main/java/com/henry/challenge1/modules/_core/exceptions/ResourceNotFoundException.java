package com.henry.challenge1.modules._core.exceptions;

public class ResourceNotFoundException extends HttpException {

    public ResourceNotFoundException(){
        super(404, "Resource not found");
    }

    public ResourceNotFoundException(String message) {
        super(404, message);
    }

    public ResourceNotFoundException(int statusCode, String message) {
        super(statusCode, message);
    }
}
