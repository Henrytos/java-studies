package br.com.course_rocket.api_course_rocket_seat.modules.course.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.course_rocket.api_course_rocket_seat.modules.course.CourseEntity;

public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {
    List<CourseEntity> findByNameOrderByCreatedAtDesc(String name);

    List<CourseEntity> findByCategoryOrderByCreatedAtDesc(String category);

    List<CourseEntity> findByNameAndCategoryOrderByCreatedAtDesc(String name, String category);

    List<CourseEntity> findAllByOrderByCreatedAtDesc();
}
