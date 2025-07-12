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

    public CourseEntity execute(UUID id, UpdateCourseRequestDTO dto) throws CourseDoesNotExistException,
            WrongCredentialsException {
        Optional<String> name = Optional.ofNullable(dto.getName()).filter(s -> !s.isEmpty());
        Optional<String> category = Optional.ofNullable(dto.getCategory()).filter(s -> !s.isEmpty());

        var course = this.courseRepository.findById(id).orElseThrow(() -> new CourseDoesNotExistException());

        if (name.isEmpty() && category.isEmpty()) {
            throw new WrongCredentialsException();
        }

        if (name.isPresent()) {
            course.setName(name.get());
        }
        if (category.isPresent()) {
            course.setCategory(category.get());
        }

        return this.courseRepository.save(course);
    }

}
