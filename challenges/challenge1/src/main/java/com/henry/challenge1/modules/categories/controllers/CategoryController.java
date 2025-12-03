package com.henry.challenge1.modules.categories.controllers;

import com.henry.challenge1.modules.categories.dtos.RegisterCategoryRequestDTO;
import com.henry.challenge1.modules.categories.dtos.CategoryResponseDTO;
import com.henry.challenge1.modules.categories.useCases.FetchCategoryUseCase;
import com.henry.challenge1.modules.categories.useCases.RegisterCategoryUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final RegisterCategoryUseCase registerCategoryUseCase;

    private final FetchCategoryUseCase fetchCategoryUseCase;

    @GetMapping
    public ResponseEntity<Page<CategoryResponseDTO>> findAll(Pageable pageable) {
        Page<CategoryResponseDTO> categories = this.fetchCategoryUseCase.execute(pageable);

        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> findByCategoryId(
            @PathVariable Long categoryId
    ) {
        CategoryResponseDTO category = this.fetchCategoryUseCase.findByCategoryId(categoryId);

        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> register(
            @RequestBody @Valid RegisterCategoryRequestDTO dto,
            UriComponentsBuilder uriBuilder
    ) {
        CategoryResponseDTO response = this.registerCategoryUseCase.execute(dto);

        URI uri = uriBuilder.buildAndExpand("/api/v1/categories/{categoryId}", response.id()).toUri();

        return ResponseEntity.created(uri).body(response);
    }

}
