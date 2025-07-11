package br.com.course_rocket.api_course_rocket_seat.modules.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetCourseRequestDTO {
    private String name;
    private String category;
}
