package com.henry.challenge1.modules.categories.useCases;

import com.henry.challenge1.modules.categories.dtos.CategoryResponseDTO;
import com.henry.challenge1.modules.categories.dtos.CategoryWithVideoResponseDTO;
import com.henry.challenge1.modules.categories.mappers.CategoryMapper;
import com.henry.challenge1.modules.categories.repositories.JpaCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FetchCategoryUseCase {

    private final JpaCategoryRepository jpaCategoryRepository;

    private final CategoryMapper categoryMapper;

    public Page<CategoryResponseDTO> execute(Pageable pageable) {

        return this.jpaCategoryRepository.findAllActive(pageable)
                .map(categoryMapper::toInfra);
    }


    public CategoryResponseDTO findByCategoryId(Long categoryId) {
        return this.jpaCategoryRepository.findByIdActive(categoryId)
                .stream().map(categoryMapper::toInfra)
                .findFirst().orElse(null);
    }

    public List<CategoryWithVideoResponseDTO> fetchCategoriesWithVideo(Long categoryId) {
        return this.jpaCategoryRepository
                .findByIdActive(categoryId)
                .stream()
                .map(categoryMapper::toInfraWithVideo)
                .toList()
                ;
    }

}
