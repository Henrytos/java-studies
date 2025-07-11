package br.com.course_rocket.api_course_rocket_seat.modules.course.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetCourseRequestDTO {
    @Pattern(regexp = "^\\S.*\\S$")
    private String name;

    @Pattern(regexp = "^\\S.*\\S$")
    private String category;
}
