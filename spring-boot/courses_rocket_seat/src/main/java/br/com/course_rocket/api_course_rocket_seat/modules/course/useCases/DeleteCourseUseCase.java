package br.com.course_rocket.api_course_rocket_seat.modules.course.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.course_rocket.api_course_rocket_seat.modules.course.exceptions.CourseDoesNotExistException;
import br.com.course_rocket.api_course_rocket_seat.modules.course.repositories.CourseRepository;

@Service
public class DeleteCourseUseCase {

    @Autowired
    private CourseRepository courseRepository;

    public void execute(UUID id) throws CourseDoesNotExistException {
        var course = this.courseRepository.findById(id);
        if (course.isEmpty()) {
            throw new CourseDoesNotExistException();
        }

        this.courseRepository.delete(course.get());
    }

}
