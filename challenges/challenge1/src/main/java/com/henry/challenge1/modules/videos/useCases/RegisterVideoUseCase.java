package com.henry.challenge1.modules.videos.useCases;

import com.henry.challenge1.modules.videos.controllers.dtos.RegisterVideoRequestDTO;
import com.henry.challenge1.modules.videos.controllers.dtos.VideoResponseDTO;
import com.henry.challenge1.modules.videos.models.VideoEntity;
import com.henry.challenge1.modules.videos.models.enums.Status;
import com.henry.challenge1.modules.videos.repositories.JpaVideoRepository;
import com.henry.challenge1.modules.videos.useCases.mappers.VideoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterVideoUseCase {

    public final JpaVideoRepository jpaVideoRepository;

    private final VideoMapper videoMapper;

    public VideoResponseDTO execute(
            RegisterVideoRequestDTO dto
    ) {
        VideoEntity videoEntity = videoMapper.toDomain(dto);
        videoEntity.setStatus(Status.ACTIVE);

        videoEntity = this.jpaVideoRepository.save(videoEntity);

        return videoMapper.toInfra(videoEntity);
    }

}
