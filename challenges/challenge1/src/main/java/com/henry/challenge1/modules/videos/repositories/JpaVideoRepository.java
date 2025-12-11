package com.henry.challenge1.modules.videos.repositories;

import com.henry.challenge1.modules.videos.models.VideoEntity;
import com.henry.challenge1.modules.videos.models.enums.Status;
import io.micrometer.common.util.StringUtils;
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
    Page<VideoEntity> findAllByCategoryId(@Param("categoryId") long categoryId, Pageable pageable);

    @Query(value = """
                SELECT * FROM videos WHERE fk_category = :categoryId
            """, nativeQuery = true)
    List<VideoEntity> findAllByCategoryId(@Param("categoryId") long categoryId);

    Page<VideoEntity> findAllByStatus(Status status, Pageable pageable);

    Optional<VideoEntity> findByIdAndStatus(Long videoId, Status status);

    default Page<VideoEntity> findAllActive(String search, Pageable pageable) {
        if(StringUtils.isBlank(search)) {
            return findAllByStatus(Status.ACTIVE, pageable);
        }
        return  findAllByStatusAndTitleContainingIgnoreCase(Status.ACTIVE, search, pageable);
    }

    Page<VideoEntity> findAllByStatusAndTitleContainingIgnoreCase(Status status, String search, Pageable pageable);

    default Optional<VideoEntity> findByIdActive(Long videoId) {
        return findByIdAndStatus(videoId, Status.ACTIVE);
    }
}
