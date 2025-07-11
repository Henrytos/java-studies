package br.com.course_rocket.api_course_rocket_seat.modules.course.dto;

import java.util.List;

import br.com.course_rocket.api_course_rocket_seat.modules.course.CourseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCourseResponseDTO {
    private List<CourseEntity> courses;
}
