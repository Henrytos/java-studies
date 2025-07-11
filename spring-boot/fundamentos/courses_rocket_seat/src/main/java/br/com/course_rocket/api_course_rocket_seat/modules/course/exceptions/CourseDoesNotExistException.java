package br.com.course_rocket.api_course_rocket_seat.modules.course.exceptions;

public class CourseDoesNotExistException extends Exception {
    public CourseDoesNotExistException() {
        super("not course already exists");
    }
}
