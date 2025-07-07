package com.henry.gestao_de_vagas.exceptions;

public class UserAlreadyExists extends RuntimeException {
    
    public UserAlreadyExists(){
        super("Usuário já existe");
    }
}
