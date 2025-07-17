package com.henry.gestao_de_vagas.exceptions;

public class CompanyNotFoundException extends RuntimeException {

    public CompanyNotFoundException() {
        super("company not found exception");
    }

}
