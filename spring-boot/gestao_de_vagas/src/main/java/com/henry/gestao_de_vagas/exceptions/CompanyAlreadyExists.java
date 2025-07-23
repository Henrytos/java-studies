package com.henry.gestao_de_vagas.exceptions;

public class CompanyAlreadyExists extends RuntimeException {

    public CompanyAlreadyExists() {
        super("company already exists");
    }
}
