package com.henry.challenge1.modules.categories.useCases;

import com.henry.challenge1.modules.categories.dtos.CategoryResponseDTO;
import com.henry.challenge1.modules.categories.mappers.CategoryMapper;
import com.henry.challenge1.modules.categories.repositories.JpaCategoryRepository;
import com.henry.challenge1.modules.videos.controllers.dtos.VideoResponseDTO;
import com.henry.challenge1.modules.videos.repositories.JpaVideoRepository;
import com.henry.challenge1.modules._core.exceptions.ResourceNotFoundException;
import com.henry.challenge1.modules.videos.useCases.mappers.VideoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FetchCategoryUseCase {

    private final JpaCategoryRepository jpaCategoryRepository;

    private final JpaVideoRepository jpaVideoRepository;

    private final CategoryMapper categoryMapper;

    private final VideoMapper videoMapper;

    public Page<CategoryResponseDTO> execute(Pageable pageable) {

        return this.jpaCategoryRepository.findAllActive(pageable)
                .map(categoryMapper::toInfra);
    }


    public CategoryResponseDTO findByCategoryId(Long categoryId) {
        return this.jpaCategoryRepository.findByIdActive(categoryId)
                .stream().map(categoryMapper::toInfra)
                .findFirst().orElse(null);
    }


    public Page<VideoResponseDTO> fetchCategoriesWithVideo(Long categoryId, Pageable pageable) {

        this.jpaCategoryRepository.findByIdActive(categoryId).orElseThrow(() -> new ResourceNotFoundException("category not found"));

        return this.jpaVideoRepository.findAllByCategoryId(categoryId, pageable)
                .map(videoMapper::toInfra);
    }

}
