package br.com.course_rocket.api_course_rocket_seat.modules.user.excetions;

public class UserDoesExistException extends Exception {

    public UserDoesExistException(String message) {
        super(message != null ? message : "User already exists");
    }

}
