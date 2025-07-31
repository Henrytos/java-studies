package com.log.dev.api.exceptions;

public class UserNotFoundException extends HttpException {
    public UserNotFoundException() {
        super(404, "User not found");
    }

    public UserNotFoundException(String message) {
        super(404, message);
    }

}
