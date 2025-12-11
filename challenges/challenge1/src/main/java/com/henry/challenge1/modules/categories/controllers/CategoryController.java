package com.henry.challenge1.modules.categories.controllers;

import com.henry.challenge1.modules.categories.dtos.CategoryWithVideoResponseDTO;
import com.henry.challenge1.modules.categories.dtos.RegisterCategoryRequestDTO;
import com.henry.challenge1.modules.categories.dtos.CategoryResponseDTO;
import com.henry.challenge1.modules.categories.dtos.UpdateCategoryRequestDTO;
import com.henry.challenge1.modules.categories.useCases.DeleteCategoryByCategoryIdUseCase;
import com.henry.challenge1.modules.categories.useCases.EditCategoryByCategoryIdUseCase;
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
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final RegisterCategoryUseCase registerCategoryUseCase;

    private final FetchCategoryUseCase fetchCategoryUseCase;

    private final DeleteCategoryByCategoryIdUseCase deleteCategoryByCategoryIdUseCase;

    private final EditCategoryByCategoryIdUseCase editCategoryByCategoryIdUseCase;

    @GetMapping
    public ResponseEntity<Page<CategoryResponseDTO>> fetch(Pageable pageable) {
        Page<CategoryResponseDTO> categories = this.fetchCategoryUseCase.execute(pageable);

        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> find(
            @PathVariable Long categoryId
    ) {
        CategoryResponseDTO category = this.fetchCategoryUseCase.findByCategoryId(categoryId);

        return ResponseEntity.ok(category);
    }


    @GetMapping("/{categoryId}/videos")
    public ResponseEntity<List<CategoryWithVideoResponseDTO>> findVideos(
            @PathVariable Long categoryId
    ) {
        List<CategoryWithVideoResponseDTO> categories  = this.fetchCategoryUseCase.fetchCategoriesWithVideo(categoryId);

        return ResponseEntity.ok(categories);
    }

    @PatchMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> edit(
            @PathVariable Long categoryId,
            @Valid @RequestBody UpdateCategoryRequestDTO body
    ) {
        CategoryResponseDTO category = this.editCategoryByCategoryIdUseCase.execute(categoryId, body);

        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> delete(
            @PathVariable Long categoryId
    ) {
        this.deleteCategoryByCategoryIdUseCase.execute(categoryId);

        return ResponseEntity.ok().build();
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
