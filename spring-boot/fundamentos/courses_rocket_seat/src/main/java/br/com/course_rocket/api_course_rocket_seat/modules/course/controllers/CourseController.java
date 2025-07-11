package br.com.course_rocket.api_course_rocket_seat.modules.course.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.course_rocket.api_course_rocket_seat.modules.course.dto.CreateCourseRequestDTO;
import br.com.course_rocket.api_course_rocket_seat.modules.course.useCases.CreateCourseUseCase;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CreateCourseUseCase createCourseUseCase;

    @GetMapping("/")
    public String getMethodName() {
        return "olá henry";
    }

    @PostMapping("/")
    public ResponseEntity<Object> cerate(@RequestBody CreateCourseRequestDTO courseRequestDTO) {
        try {
            var response = this.createCourseUseCase.execute(courseRequestDTO);

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
