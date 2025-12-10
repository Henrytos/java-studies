package com.henry.challenge1.modules.categories.repositories;

import com.henry.challenge1.modules.categories.models.CategoryEntity;
import com.henry.challenge1.modules.videos.models.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaCategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByTitle(String title);

    Page<CategoryEntity> findAllByStatus(Status status, Pageable pageable);

    default Page<CategoryEntity> findAllActive(Pageable pageable) {
        return findAllByStatus(Status.ACTIVE, pageable);
    };

    Optional<CategoryEntity> findByIdAndStatus(Long id, Status status);

    default Optional<CategoryEntity> findByIdActive(Long categoryId) {
        return findByIdAndStatus(categoryId, Status.ACTIVE);
    }

    Optional<CategoryEntity> findByTitleAndStatus(String title, Status status);

    default Optional<CategoryEntity> findByTitleActive(String title) {
        return findByTitleAndStatus(title, Status.ACTIVE);
    }
}
