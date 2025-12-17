package com.henry.challenge1.modules.videos.useCases;

import com.henry.challenge1.modules.videos.controllers.dtos.VideoResponseDTO;
import com.henry.challenge1.modules.videos.models.VideoEntity;
import com.henry.challenge1.modules.videos.repositories.JpaVideoRepository;
import com.henry.challenge1.modules._core.exceptions.ResourceNotFoundException;
import com.henry.challenge1.modules.videos.useCases.mappers.VideoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindByVideoIdUseCase {

    private final JpaVideoRepository jpaVideoRepository;

    private final VideoMapper videoMapper;

    public VideoResponseDTO execute(Long videoId) {
        VideoEntity video = this.jpaVideoRepository.findByIdActive(videoId)
                .orElseThrow(() -> new ResourceNotFoundException("Video not found"));

        return this.videoMapper.toInfra(video);
    }

}
