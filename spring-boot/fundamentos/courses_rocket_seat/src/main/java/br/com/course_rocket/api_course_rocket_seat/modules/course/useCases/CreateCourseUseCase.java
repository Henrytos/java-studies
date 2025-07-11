package br.com.course_rocket.api_course_rocket_seat.modules.course.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.course_rocket.api_course_rocket_seat.modules.course.CourseEntity;
import br.com.course_rocket.api_course_rocket_seat.modules.course.dto.CreateCourseRequestDTO;
import br.com.course_rocket.api_course_rocket_seat.modules.course.repositories.CourseRepository;

@Service
public class CreateCourseUseCase {

    @Autowired
    private CourseRepository courseRepository;

    public CourseEntity execute(CreateCourseRequestDTO courseRequestDTO) throws Exception {
        var course = CourseEntity
                .builder()
                .name(courseRequestDTO.getName())
                .category(courseRequestDTO.getCategory())
                .active(true)
                .build();

        return this.courseRepository.save(course);
    }

}
