package com.henry.challenge1.modules.categories.useCases;

import com.henry.challenge1.modules.categories.dtos.RegisterCategoryRequestDTO;
import com.henry.challenge1.modules.categories.dtos.CategoryResponseDTO;
import com.henry.challenge1.modules.categories.mappers.CategoryMapper;
import com.henry.challenge1.modules.categories.models.CategoryEntity;
import com.henry.challenge1.modules.categories.repositories.JpaCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterCategoryUseCase {

    private final JpaCategoryRepository jpaCategoryRepository;

    private final CategoryMapper categoryMapper;

    public CategoryResponseDTO execute(RegisterCategoryRequestDTO dto) {
        boolean categoryExists = this.jpaCategoryRepository.findByTitleActive(dto.title()).isPresent();

        if (categoryExists)
            throw new RuntimeException("Category already exists");

        CategoryEntity category = this.categoryMapper.toDomain(dto);
        category.activeCategory();

        this.jpaCategoryRepository.save(category);

        return this.categoryMapper.toInfra(category);
    }


}
