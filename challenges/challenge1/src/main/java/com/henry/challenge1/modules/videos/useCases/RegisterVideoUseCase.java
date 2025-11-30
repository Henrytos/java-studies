package com.henry.challenge1.modules.videos.useCases;

import com.henry.challenge1.modules.videos.controllers.dtos.RegisterVideoRequestDTO;
import com.henry.challenge1.modules.videos.controllers.dtos.RegisterVideoResponseDTO;
import com.henry.challenge1.modules.videos.models.VideoEntity;
import com.henry.challenge1.modules.videos.repositories.JpaVideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterVideoUseCase {

    public final JpaVideoRepository jpaVideoRepository;

    public RegisterVideoResponseDTO execute(
            RegisterVideoRequestDTO dto
    ) {
        VideoEntity videoEntity = new VideoEntity(dto);
        videoEntity = this.jpaVideoRepository.save(videoEntity);

        return new RegisterVideoResponseDTO(videoEntity.getId(), videoEntity.getTitle(), videoEntity.getDescription(), videoEntity.getUrl());
    }

}
