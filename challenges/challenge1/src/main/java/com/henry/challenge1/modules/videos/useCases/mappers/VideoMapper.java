package com.henry.challenge1.modules.videos.useCases.mappers;

import com.henry.challenge1.modules._core.useCases.mappers.EntityMapper;
import com.henry.challenge1.modules.videos.controllers.dtos.RegisterVideoRequestDTO;
import com.henry.challenge1.modules.videos.controllers.dtos.VideoResponseDTO;
import com.henry.challenge1.modules.videos.models.VideoEntity;
import org.springframework.stereotype.Component;

@Component
public class VideoMapper implements EntityMapper<VideoEntity, VideoResponseDTO> {
    @Override
    public VideoEntity toDomain(VideoResponseDTO videoResponseDTO) {
        return VideoEntity.builder()
                .id(videoResponseDTO.id())
                .title(videoResponseDTO.title())
                .description(videoResponseDTO.description())
                .url(videoResponseDTO.url())
                .build();
    }

    public VideoEntity toDomain(RegisterVideoRequestDTO registerVideoRequestDTO) {
        return VideoEntity.builder()
                .title(registerVideoRequestDTO.title())
                .description(registerVideoRequestDTO.description())
                .url(registerVideoRequestDTO.url())
                .build();
    }

    @Override
    public VideoResponseDTO toInfra(VideoEntity videoEntity) {
        return new VideoResponseDTO(videoEntity.getId(), videoEntity.getTitle(), videoEntity.getDescription(), videoEntity.getUrl(), videoEntity.getCategory().getId());
    }
}
