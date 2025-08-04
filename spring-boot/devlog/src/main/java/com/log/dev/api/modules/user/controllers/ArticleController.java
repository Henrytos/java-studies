package com.log.dev.api.modules.user.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.log.dev.api.dtos.CreateArticleRequestDTO;
import com.log.dev.api.dtos.ErrorMessageDTO;
import com.log.dev.api.dtos.MessageResponseDTO;
import com.log.dev.api.modules.user.ArticleEntity;
import com.log.dev.api.modules.user.useCases.CreateArticleUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private CreateArticleUseCase createArticleUseCase;

    public ArticleController(CreateArticleUseCase createArticleUseCase) {
        this.createArticleUseCase = createArticleUseCase;
    }

    @PostMapping()
    @Tag(name = "Article")
    @Operation(summary = "Create article", description = "Create article by user authenticate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "article created success", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ArticleEntity.class))),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content(array = @ArraySchema(arraySchema = @Schema(implementation = ErrorMessageDTO.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "user not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MessageResponseDTO.class))),

    })
    public ResponseEntity<ArticleEntity> create(@Valid @RequestBody CreateArticleRequestDTO dto) {

        System.out.println(dto.getTitle());
        System.out.println(dto.getSubTitle());
        System.out.println(dto.getContent());
        System.out.println(dto.getReadingDurationInMinutes());
        System.out.println(dto.getAuthorId());

        ArticleEntity article = this.createArticleUseCase.execute(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }

}
