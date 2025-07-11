package br.com.course_rocket.api_course_rocket_seat.modules.course.useCases;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.course_rocket.api_course_rocket_seat.modules.course.CourseEntity;
import br.com.course_rocket.api_course_rocket_seat.modules.course.dto.GetCourseRequestDTO;
import br.com.course_rocket.api_course_rocket_seat.modules.course.dto.GetCourseResponseDTO;
import br.com.course_rocket.api_course_rocket_seat.modules.course.repositories.CourseRepository;

@Service
public class GetCourseUseCase {

    @Autowired
    private CourseRepository courseRepository;

    public GetCourseResponseDTO execute(GetCourseRequestDTO courseRequestDTO) {
        List<CourseEntity> courses = null;
        Optional<String> name = Optional.ofNullable(courseRequestDTO.getName()).filter(s -> !s.isEmpty());
        Optional<String> category = Optional.ofNullable(courseRequestDTO.getCategory()).filter(s -> !s.isEmpty());

        if (name.isPresent() && category.isPresent()) {
            courses = this.courseRepository.findByNameAndCategoryOrderByCreatedAtDesc(name.get(), category.get());
        } else if (category.isPresent()) {
            courses = this.courseRepository.findByCategoryOrderByCreatedAtDesc(category.get());
        } else if (name.isPresent()) {
            courses = this.courseRepository.findByNameOrderByCreatedAtDesc(name.get());
        } else {
            courses = this.courseRepository.findAllByOrderByCreatedAtDesc();
        }

        var courseResponse = GetCourseResponseDTO
                .builder()
                .courses(courses)
                .build();

        return courseResponse;
    }

}
