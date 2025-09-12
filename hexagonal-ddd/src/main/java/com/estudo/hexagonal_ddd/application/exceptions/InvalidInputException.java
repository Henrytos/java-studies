package com.estudo.hexagonal_ddd.application.exceptions;

public class InvalidInputException extends  ServiceRunTimeException{

    public InvalidInputException(String message){
        super(message);
    }

}
