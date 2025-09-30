package com.estudo.hexagonal_ddd.application.exceptions;

public class UnauthorizedException extends ServiceRunTimeException{

    public UnauthorizedException(){
        super("Unauthorized Exception");
    }

    public UnauthorizedException(String message){
        super(message);
    }

}
