package com.estudo.hexagonal_ddd.application.exceptions;

public class WrongCredentialException extends ServiceRunTimeException {
    public WrongCredentialException(String message) {
        super(message);
    }
}
