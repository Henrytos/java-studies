package br.com.course_rocket.api_course_rocket_seat.modules.course.useCases;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.course_rocket.api_course_rocket_seat.modules.course.CourseEntity;
import br.com.course_rocket.api_course_rocket_seat.modules.course.controllers.WrongCredentialsException;
import br.com.course_rocket.api_course_rocket_seat.modules.course.dto.UpdateCourseRequestDTO;
import br.com.course_rocket.api_course_rocket_seat.modules.course.exceptions.CourseDoesNotExistException;
import br.com.course_rocket.api_course_rocket_seat.modules.course.repositories.CourseRepository;

@Service
public class UpdateCourseUseCase {

    @Autowired
    private CourseRepository courseRepository;

    public CourseEntity execute(UUID id, UpdateCourseRequestDTO courseRequestDTO) throws CourseDoesNotExistException,
            WrongCredentialsException {
        Optional<String> name = Optional.ofNullable(courseRequestDTO.getName()).filter(s -> !s.isEmpty());
        Optional<String> category = Optional.ofNullable(courseRequestDTO.getCategory()).filter(s -> !s.isEmpty());

        var course = this.courseRepository.findById(id);
        if (course.isEmpty()) {
            throw new CourseDoesNotExistException();
        }

        if (name.isPresent() && category.isPresent()) {
            course.get().setName(name.get());
            course.get().setCategory(category.get());
        } else if (name.isPresent()) {
            course.get().setName(name.get());
        } else if (category.isPresent()) {
            course.get().setCategory(category.get());
        } else {
            throw new WrongCredentialsException();
        }

        return this.courseRepository.save(course.get());
    }

}
