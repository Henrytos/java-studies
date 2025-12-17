package com.henry.challenge1.modules.videos.useCases;

import ch.qos.logback.core.util.StringUtil;
import com.henry.challenge1.modules.videos.controllers.dtos.UpdateVideoRequestDTO;
import com.henry.challenge1.modules.videos.controllers.dtos.VideoResponseDTO;
import com.henry.challenge1.modules.videos.models.VideoEntity;
import com.henry.challenge1.modules.videos.repositories.JpaVideoRepository;
import com.henry.challenge1.modules._core.exceptions.ResourceNotFoundException;
import com.henry.challenge1.modules.videos.useCases.mappers.VideoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EditVideoUseCase {

    private final JpaVideoRepository jpaVideoRepository;

    private final VideoMapper videoMapper;

    public VideoResponseDTO execute(Long videoId, UpdateVideoRequestDTO dto) {

        VideoEntity video = this.jpaVideoRepository.findByIdActive(videoId)
                .orElseThrow(()-> new ResourceNotFoundException("Video not found"));

        if(!StringUtil.isNullOrEmpty(dto.title()))
            video.setTitle(dto.title());

        if(!StringUtil.isNullOrEmpty(dto.description()))
            video.setDescription(dto.description());

        if(!StringUtil.isNullOrEmpty(dto.url()))
            video.setUrl(dto.url());

        return this.videoMapper.toInfra(video);
    }

}
