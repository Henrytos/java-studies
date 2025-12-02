package com.henry.challenge1.modules.categories.controllers;

import com.henry.challenge1.modules.categories.dtos.RegisterCategoryRequestDTO;
import com.henry.challenge1.modules.categories.dtos.RegisterCategoryResponseDTO;
import com.henry.challenge1.modules.categories.useCases.RegisterCategoryUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final RegisterCategoryUseCase registerCategoryUseCase;

    @PostMapping
    public ResponseEntity<RegisterCategoryResponseDTO> register(
            @RequestBody @Valid  RegisterCategoryRequestDTO dto,
            UriComponentsBuilder uriBuilder
    ){
        RegisterCategoryResponseDTO response = this.registerCategoryUseCase.execute(dto);

        URI uri = uriBuilder.buildAndExpand("/api/v1/categories/{categoryId}", response.id()).toUri();

        return  ResponseEntity.created(uri).body(response);
    }

}
