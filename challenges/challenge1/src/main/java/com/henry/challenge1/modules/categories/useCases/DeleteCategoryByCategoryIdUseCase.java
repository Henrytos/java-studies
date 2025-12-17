package com.henry.challenge1.modules.categories.useCases;

import com.henry.challenge1.modules.categories.models.CategoryEntity;
import com.henry.challenge1.modules.categories.repositories.JpaCategoryRepository;
import com.henry.challenge1.modules.videos.models.VideoEntity;
import com.henry.challenge1.modules.videos.repositories.JpaVideoRepository;
import com.henry.challenge1.modules._core.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeleteCategoryByCategoryIdUseCase {

    private final JpaCategoryRepository jpaCategoryRepository;

    private final JpaVideoRepository jpaVideoRepository;

    public void execute(
            Long categoryId
    ) {

        CategoryEntity category = this.jpaCategoryRepository
                .findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("category not found"));

        category.inactiveCategory();

        CategoryEntity categoryDefault = this.jpaCategoryRepository.findById(1L).get();

        List<VideoEntity> videos = this.jpaVideoRepository.findAllByCategoryId(categoryId);
        for (VideoEntity video : videos) {
            video.setCategory(categoryDefault);
        }

        this.jpaVideoRepository.saveAll(videos);

    }

}
