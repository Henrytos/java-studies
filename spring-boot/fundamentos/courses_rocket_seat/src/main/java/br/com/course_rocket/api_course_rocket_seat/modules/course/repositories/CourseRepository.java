package br.com.course_rocket.api_course_rocket_seat.modules.course.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.course_rocket.api_course_rocket_seat.modules.course.CourseEntity;

public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {

}
