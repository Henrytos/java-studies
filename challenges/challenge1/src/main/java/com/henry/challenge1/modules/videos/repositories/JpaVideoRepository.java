package com.henry.challenge1.modules.videos.repositories;

import com.henry.challenge1.modules.videos.models.VideoEntity;
import com.henry.challenge1.modules.videos.models.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaVideoRepository extends JpaRepository<VideoEntity, Long> {

    @Query(value = """
                SELECT * FROM videos WHERE fk_category = :categoryId
            """, nativeQuery = true)
    List<VideoEntity> findAllByCategoryId(@Param("categoryId") long categoryId);

    Page<VideoEntity> findAllByStatus(Status status, Pageable pageable);

    Optional<VideoEntity> findByIdAndStatus(Long videoId, Status status);

    default Page<VideoEntity> findAllActive(Pageable pageable) {
        return findAllByStatus(Status.ACTIVE, pageable);
    }

    default Optional<VideoEntity> findByIdActive(Long videoId) {
        return findByIdAndStatus(videoId, Status.ACTIVE);
    }
}
