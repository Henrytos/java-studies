package com.estudo.hexagonal_ddd.application.exceptions;

public abstract class ServiceRunTimeException extends RuntimeException  {

    public ServiceRunTimeException() {
        super();
    }

    public ServiceRunTimeException(String message) {
        super(message);
    }

    public ServiceRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceRunTimeException(Throwable cause) {
        super(cause);
    }}
