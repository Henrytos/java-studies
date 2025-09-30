package com.estudo.hexagonal_ddd.application.exceptions;

public class WithoutAuthorizationException extends ServiceRunTimeException{

    public WithoutAuthorizationException(){
        super("Without Authorization Exception");
    }

    public WithoutAuthorizationException(String message){
        super(message);
    }

}
