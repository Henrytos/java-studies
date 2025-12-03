package com.henry.challenge1.modules.categories.mappers;

import com.henry.challenge1.modules.categories.dtos.RegisterCategoryRequestDTO;
import com.henry.challenge1.modules.categories.dtos.CategoryResponseDTO;
import com.henry.challenge1.modules.categories.models.CategoryEntity;
import com.henry.challenge1.modules.videos.useCases.mappers.EntityMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper implements EntityMapper<CategoryEntity, CategoryResponseDTO> {

    @Override
    public CategoryEntity toDomain(CategoryResponseDTO dto) {
        return new  CategoryEntity(dto.id(), dto.title(), dto.color(), List.of());
    }

    public CategoryEntity toDomain(RegisterCategoryRequestDTO dto) {
        return new  CategoryEntity(null, dto.title(), dto.color(), List.of());
    }

    @Override
    public CategoryResponseDTO toInfra(CategoryEntity entity) {
        return new CategoryResponseDTO(entity.getId(), entity.getTitle(), entity.getColor());
    }
}
