package com.estudo.hexagonal_ddd.domain.entities.value_objects;

public enum Role {
    ADMIN ,
    MEMBER;

    public static boolean isValid(String vale){
        try {
            Role.valueOf(vale.toUpperCase());
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
