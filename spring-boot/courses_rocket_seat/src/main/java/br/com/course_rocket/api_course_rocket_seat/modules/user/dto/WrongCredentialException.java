package br.com.course_rocket.api_course_rocket_seat.modules.user.dto;

public class WrongCredentialException extends Exception {

    public WrongCredentialException(String message) {
        super(message != null ? message : "Wrong credentials");
    }

}
