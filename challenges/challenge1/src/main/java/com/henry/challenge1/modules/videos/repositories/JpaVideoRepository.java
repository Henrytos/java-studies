package com.henry.challenge1.modules.videos.repositories;

import com.henry.challenge1.modules.videos.models.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaVideoRepository extends JpaRepository<VideoEntity, Long> {

}
