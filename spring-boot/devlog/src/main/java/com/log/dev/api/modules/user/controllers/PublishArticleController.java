package com.log.dev.api.modules.user.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.log.dev.api.dtos.MessageResponseDTO;
import com.log.dev.api.dtos.SearchPublishArticleRequestDTO;
import com.log.dev.api.dtos.SearchPublishArticleResponseDTO;
import com.log.dev.api.exceptions.WrongCredentialsException;
import com.log.dev.api.modules.user.useCases.SearchPublishArticlesUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/publishArticles")
public class PublishArticleController {

    private final SearchPublishArticlesUseCase searchPublishArticlesUseCase;

    public PublishArticleController(SearchPublishArticlesUseCase searchPublishArticlesUseCase) {
        this.searchPublishArticlesUseCase = searchPublishArticlesUseCase;
    }

    @GetMapping("/search")
    @Operation(description = "Search publish articles by title, content and tags", summary = "Search publish articles")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "successful search", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = SearchPublishArticleResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "invalid page or perPage", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MessageResponseDTO.class)))
    })
    public ResponseEntity<SearchPublishArticleResponseDTO> search(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer perPage,
            @RequestBody SearchBody body) {

        if (page <= 0 || perPage <= 0) {
            throw new WrongCredentialsException("Page and perPage must be greater than 0");
        }

        SearchPublishArticleRequestDTO dto = new SearchPublishArticleRequestDTO(
                body.title,
                body.content,
                body.tags,
                page,
                perPage);

        SearchPublishArticleResponseDTO response = searchPublishArticlesUseCase.execute(dto);

        return ResponseEntity.ok().body(response);
    }

    record SearchBody(String title, String content, List<String> tags) {
    }

}
