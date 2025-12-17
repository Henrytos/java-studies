package com.henry.challenge1.modules.videos.useCases;

import com.henry.challenge1.modules.categories.repositories.JpaCategoryRepository;
import com.henry.challenge1.modules.videos.controllers.dtos.RegisterVideoRequestDTO;
import com.henry.challenge1.modules.videos.controllers.dtos.VideoResponseDTO;
import com.henry.challenge1.modules.videos.models.VideoEntity;
import com.henry.challenge1.modules.videos.models.enums.Status;
import com.henry.challenge1.modules.videos.repositories.JpaVideoRepository;
import com.henry.challenge1.modules._core.exceptions.ResourceNotFoundException;
import com.henry.challenge1.modules.videos.useCases.mappers.VideoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterVideoUseCase {

    private final JpaVideoRepository jpaVideoRepository;

    private final JpaCategoryRepository jpaCategoryRepository;

    private final VideoMapper videoMapper;

    public VideoResponseDTO execute(
            RegisterVideoRequestDTO dto
    ) {
        VideoEntity videoEntity = videoMapper.toDomain(dto);
        videoEntity.setStatus(Status.ACTIVE);

        if (dto.categoryId() != null)
            videoEntity.setCategory(this.jpaCategoryRepository.findById(dto.categoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category Not Found")));

        videoEntity.setCategory(this.jpaCategoryRepository.findAll().getFirst());

        videoEntity = this.jpaVideoRepository.save(videoEntity);

        return videoMapper.toInfra(videoEntity);
    }

}
