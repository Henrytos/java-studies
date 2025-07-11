package br.com.course_rocket.api_course_rocket_seat.modules.course.controllers;

public class WrongCredentialsException extends Exception {
    public WrongCredentialsException() {
        super("wrong credentials exception");
    }
}
