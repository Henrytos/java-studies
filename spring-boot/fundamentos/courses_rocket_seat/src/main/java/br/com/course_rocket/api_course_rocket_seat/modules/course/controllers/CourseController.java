package br.com.course_rocket.api_course_rocket_seat.modules.course.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.course_rocket.api_course_rocket_seat.modules.course.dto.CreateCourseRequestDTO;
import br.com.course_rocket.api_course_rocket_seat.modules.course.dto.GetCourseRequestDTO;
import br.com.course_rocket.api_course_rocket_seat.modules.course.useCases.CreateCourseUseCase;
import br.com.course_rocket.api_course_rocket_seat.modules.course.useCases.GetCourseUseCase;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CreateCourseUseCase createCourseUseCase;

    @Autowired
    private GetCourseUseCase getCourseUseCase;

    @GetMapping("/")
    public ResponseEntity<Object> get(
            @RequestParam() String name,
            @RequestParam() String category) {
        try {
            var courseRequest = GetCourseRequestDTO.builder().name(name).category(category).build();
            var result = this.getCourseUseCase.execute(courseRequest);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CreateCourseRequestDTO courseRequestDTO) {
        try {
            var response = this.createCourseUseCase.execute(courseRequestDTO);

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
