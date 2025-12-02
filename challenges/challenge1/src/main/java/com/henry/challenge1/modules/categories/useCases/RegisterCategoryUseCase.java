package com.henry.challenge1.modules.categories.useCases;

import com.henry.challenge1.modules.categories.dtos.RegisterCategoryRequestDTO;
import com.henry.challenge1.modules.categories.dtos.RegisterCategoryResponseDTO;
import com.henry.challenge1.modules.categories.mappers.CategoryMapper;
import com.henry.challenge1.modules.categories.models.CategoryEntity;
import com.henry.challenge1.modules.categories.repositories.JpaCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegisterCategoryUseCase {

    private final JpaCategoryRepository jpaCategoryRepository;

    private final CategoryMapper categoryMapper;

    public RegisterCategoryResponseDTO execute(RegisterCategoryRequestDTO dto) {
        boolean categoryExists = this.jpaCategoryRepository.findByTitle(dto.title()).isPresent();

        if (categoryExists)
            throw new RuntimeException("Category already exists");

        CategoryEntity category = this.jpaCategoryRepository.save(this.categoryMapper.toDomain(dto));

        return this.categoryMapper.toInfra(category);
    }


}
