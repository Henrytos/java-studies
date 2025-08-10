package com.log.dev.api.exceptions;

public class InternalServerErrorException extends HttpException {

    public InternalServerErrorException() {
        super(500, "Internal server error");
    }

}
