package com.henry.challenge1.modules.categories.mappers;

import com.henry.challenge1.modules.categories.dtos.CategoryWithVideoResponseDTO;
import com.henry.challenge1.modules.categories.dtos.RegisterCategoryRequestDTO;
import com.henry.challenge1.modules.categories.dtos.CategoryResponseDTO;
import com.henry.challenge1.modules.categories.models.CategoryEntity;
import com.henry.challenge1.modules.videos.controllers.dtos.VideoResponseDTO;
import com.henry.challenge1.modules.videos.useCases.mappers.EntityMapper;
import com.henry.challenge1.modules.videos.useCases.mappers.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper implements EntityMapper<CategoryEntity, CategoryResponseDTO> {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public CategoryEntity toDomain(CategoryResponseDTO dto) {

        return CategoryEntity.builder()
                .id(dto.id())
                .title(dto.title())
                .color(dto.color())
                .build();
    }

    public CategoryEntity toDomain(RegisterCategoryRequestDTO dto) {
        return CategoryEntity.builder()
                .title(dto.title())
                .color(dto.color())
                .build();
    }

    @Override
    public CategoryResponseDTO toInfra(CategoryEntity entity) {
        return new CategoryResponseDTO(entity.getId(), entity.getTitle(), entity.getColor());
    }

    public CategoryWithVideoResponseDTO toInfraWithVideo(CategoryEntity entity) {
        return new CategoryWithVideoResponseDTO(entity.getId(), entity.getTitle(), entity.getColor(),
                entity.getVideos().stream().map(videoMapper::toInfra).toList()
                );
    }
}
