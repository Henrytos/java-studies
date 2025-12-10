package com.henry.challenge1.modules.categories.useCases.exceptions;

import org.springframework.http.HttpStatus;

public class CategoryNotFoundException extends RuntimeException {
    int status =  HttpStatus.NOT_FOUND.value();


    public CategoryNotFoundException() {
        super("Category not found");
    }


    public CategoryNotFoundException(int status, String message) {
        super(message);
        this.status = status;
    }
}
