package com.henry.challenge1.modules.categories.mappers;

import com.henry.challenge1.modules.categories.dtos.RegisterCategoryRequestDTO;
import com.henry.challenge1.modules.categories.dtos.RegisterCategoryResponseDTO;
import com.henry.challenge1.modules.categories.models.CategoryEntity;
import com.henry.challenge1.modules.videos.useCases.mappers.EntityMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper implements EntityMapper<CategoryEntity, RegisterCategoryResponseDTO> {

    @Override
    public CategoryEntity toDomain(RegisterCategoryResponseDTO dto) {
        return new  CategoryEntity(dto.id(), dto.title(), dto.color(), List.of());
    }

    public CategoryEntity toDomain(RegisterCategoryRequestDTO dto) {
        return new  CategoryEntity(null, dto.title(), dto.color(), List.of());
    }

    @Override
    public RegisterCategoryResponseDTO toInfra(CategoryEntity entity) {
        return new RegisterCategoryResponseDTO(entity.getId(), entity.getTitle(), entity.getColor());
    }
}
