package com.estudo.hexagonal_ddd.domain.exceptions;

public class WrongCredentials extends RuntimeException {

    public WrongCredentials(String ...typeCredentials){

        super("Wrong credentials: %s".formatted(getFieldsCredentials(typeCredentials)));



    }

    private static String getFieldsCredentials(String ...typeCredentials){
        StringBuilder messageErrorTypeCredentials = new StringBuilder();

        for (String typeCredential : typeCredentials) {
            messageErrorTypeCredentials.append(typeCredential.concat(" "));
        }

        return messageErrorTypeCredentials.toString();
    }

}
