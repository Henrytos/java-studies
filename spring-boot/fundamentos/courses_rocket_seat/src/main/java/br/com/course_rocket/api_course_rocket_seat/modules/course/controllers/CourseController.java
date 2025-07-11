package br.com.course_rocket.api_course_rocket_seat.modules.course.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.course_rocket.api_course_rocket_seat.modules.course.dto.CreateCourseRequestDTO;
import br.com.course_rocket.api_course_rocket_seat.modules.course.dto.GetCourseRequestDTO;
import br.com.course_rocket.api_course_rocket_seat.modules.course.dto.UpdateCourseRequestDTO;
import br.com.course_rocket.api_course_rocket_seat.modules.course.useCases.ActiveCourseUseCase;
import br.com.course_rocket.api_course_rocket_seat.modules.course.useCases.CreateCourseUseCase;
import br.com.course_rocket.api_course_rocket_seat.modules.course.useCases.DeleteCourseUseCase;
import br.com.course_rocket.api_course_rocket_seat.modules.course.useCases.GetCourseUseCase;
import br.com.course_rocket.api_course_rocket_seat.modules.course.useCases.UpdateCourseUseCase;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CreateCourseUseCase createCourseUseCase;

    @Autowired
    private GetCourseUseCase getCourseUseCase;

    @Autowired
    private UpdateCourseUseCase updateCourseUseCase;

    @Autowired
    private DeleteCourseUseCase deleteCourseUseCase;

    @Autowired
    private ActiveCourseUseCase activeCourseUseCase;

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

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@Valid @PathVariable String id,
            @RequestBody UpdateCourseRequestDTO courseRequestDTO) {
        try {
            var courseId = UUID.fromString(id);
            var result = this.updateCourseUseCase.execute(courseId, courseRequestDTO);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@Valid @PathVariable String id) {
        try {
            var courseId = UUID.fromString(id);
            this.deleteCourseUseCase.execute(courseId);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<Object> active(
            @PathVariable String id) {
        try {
            var courseId = UUID.fromString(id);
            this.activeCourseUseCase.execute(courseId);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
