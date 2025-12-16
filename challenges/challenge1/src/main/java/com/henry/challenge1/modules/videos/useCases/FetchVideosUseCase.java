package com.henry.challenge1.modules.videos.useCases;

import com.henry.challenge1.modules.videos.controllers.dtos.VideoResponseDTO;
import com.henry.challenge1.modules.videos.repositories.JpaVideoRepository;
import com.henry.challenge1.modules.videos.useCases.mappers.VideoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FetchVideosUseCase {

    private final JpaVideoRepository jpaVideoRepository;

    private final VideoMapper videoMapper;

    public Page<VideoResponseDTO> execute(String search, Pageable pageable) {

        return this.jpaVideoRepository.findAllActive(search, pageable)
                .map(videoMapper::toInfra);
    }

    public List<VideoResponseDTO> free() {
        return this.jpaVideoRepository.findTop5ByOrderByIdDesc().stream().map(videoMapper::toInfra).toList();
    }
}
