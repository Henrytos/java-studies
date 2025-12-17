package com.henry.challenge1.modules.categories.useCases;

import com.henry.challenge1.modules.categories.dtos.CategoryResponseDTO;
import com.henry.challenge1.modules.categories.dtos.UpdateCategoryRequestDTO;
import com.henry.challenge1.modules.categories.mappers.CategoryMapper;
import com.henry.challenge1.modules.categories.models.CategoryEntity;
import com.henry.challenge1.modules.categories.repositories.JpaCategoryRepository;
import com.henry.challenge1.modules._core.exceptions.ResourceNotFoundException;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EditCategoryByCategoryIdUseCase {

    private final JpaCategoryRepository jpaCategoryRepository;

    private final CategoryMapper categoryMapper;

    public CategoryResponseDTO execute(
            Long categoryId,
            UpdateCategoryRequestDTO dto
    ) {

        CategoryEntity category = this.jpaCategoryRepository.findByIdActive(categoryId).orElseThrow(() -> new ResourceNotFoundException("category not found"));

        if (!StringUtils.isBlank(dto.title()))
            category.setTitle(dto.title());

        if (!StringUtils.isBlank(dto.color()))
            category.setColor(dto.color());

        this.jpaCategoryRepository.save(category);

        return this.categoryMapper.toInfra(category);
    }

}
