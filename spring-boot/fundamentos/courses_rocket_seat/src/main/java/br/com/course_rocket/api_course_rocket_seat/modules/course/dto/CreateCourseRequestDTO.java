package br.com.course_rocket.api_course_rocket_seat.modules.course.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CreateCourseRequestDTO {

    @NotBlank
    @Pattern(regexp = "^\\S.*\\S$")
    @Length(min = 3, max = 50)
    private String name;

    @NotBlank
    @Pattern(regexp = "^\\S.*\\S$")
    @Length(min = 3, max = 50)
    private String category;

}
