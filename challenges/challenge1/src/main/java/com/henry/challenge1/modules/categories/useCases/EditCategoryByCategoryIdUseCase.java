package com.henry.challenge1.modules.categories.useCases;

import com.henry.challenge1.modules.categories.dtos.CategoryResponseDTO;
import com.henry.challenge1.modules.categories.dtos.UpdateCategoryRequestDTO;
import com.henry.challenge1.modules.categories.mappers.CategoryMapper;
import com.henry.challenge1.modules.categories.models.CategoryEntity;
import com.henry.challenge1.modules.categories.repositories.JpaCategoryRepository;
import com.henry.challenge1.modules.categories.useCases.exceptions.CategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class EditCategoryByCategoryIdUseCase {

    private final JpaCategoryRepository  jpaCategoryRepository;

    private final CategoryMapper categoryMapper;

    public CategoryResponseDTO execute(
            Long categoryId,
            UpdateCategoryRequestDTO dto
    ){

        CategoryEntity category = this.jpaCategoryRepository.findByIdActive(categoryId).orElseThrow(CategoryNotFoundException::new);

        if(!StringUtils.isEmpty(dto.title()))
            category.setTitle(dto.title());

        if(!StringUtils.isEmpty(dto.color()))
            category.setColor(dto.color());

        this.jpaCategoryRepository.save(category);

        return this.categoryMapper.toInfra(category);
    }

}
