package com.log.dev.api.exceptions;

public class UserUnauthorizedException extends HttpException {

    public UserUnauthorizedException() {
        super(401, "User Unauthorized");
    }

}
