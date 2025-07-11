package br.com.course_rocket.api_course_rocket_seat.modules.course.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CreateCourseRequestDTO {

    @Pattern(regexp = "^\\S.*\\S$")
    private String name;

    @Pattern(regexp = "^\\S.*\\S$")
    private String category;

}
