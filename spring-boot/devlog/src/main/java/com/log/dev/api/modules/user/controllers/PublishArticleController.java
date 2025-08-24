package com.log.dev.api.modules.user.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.log.dev.api.dtos.ArticleWithDetailsDTO;
import com.log.dev.api.dtos.ErrorMessageDTO;
import com.log.dev.api.dtos.MessageResponseDTO;
import com.log.dev.api.dtos.SearchPublishArticleRequestDTO;
import com.log.dev.api.dtos.SearchPublishArticleResponseDTO;
import com.log.dev.api.exceptions.WrongCredentialsException;
import com.log.dev.api.modules.author.entities.ArticleEntity;
import com.log.dev.api.modules.author.useCases.GetPublishArticleWithDetailsUseCase;
import com.log.dev.api.modules.user.useCases.ListPublishArticlesRecentUseCase;
import com.log.dev.api.modules.user.useCases.SearchPublishArticlesUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/publishArticles")
public class PublishArticleController {

    private final SearchPublishArticlesUseCase searchPublishArticlesUseCase;

    private final ListPublishArticlesRecentUseCase listPublishArticlesRecentUseCase;

    private final GetPublishArticleWithDetailsUseCase getPublishArticleWithDetailsUseCase;

    public PublishArticleController(SearchPublishArticlesUseCase searchPublishArticlesUseCase,
            ListPublishArticlesRecentUseCase listPublishArticlesRecentUseCase,
            GetPublishArticleWithDetailsUseCase getPublishArticleWithDetailsUseCase) {
        this.searchPublishArticlesUseCase = searchPublishArticlesUseCase;
        this.listPublishArticlesRecentUseCase = listPublishArticlesRecentUseCase;
        this.getPublishArticleWithDetailsUseCase = getPublishArticleWithDetailsUseCase;
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

    @GetMapping("/recent")
    @Operation(description = "list publish articles by title, content and tags", summary = "List publish articles")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "successful list", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = SearchPublishArticleResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "invalid page or perPage", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MessageResponseDTO.class)))
    })
    public ResponseEntity<SearchPublishArticleResponseDTO> get(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer perPage) {

        if (page <= 0 || perPage <= 0 || perPage > 100) {
            throw new WrongCredentialsException("Page and perPage must be greater than 0");
        }

        return ResponseEntity.ok().body(listPublishArticlesRecentUseCase.execute(page, perPage));
    }

    record SearchBody(String title, String content, List<String> tags) {
    }

    @GetMapping("/{articleId}")
    @Tag(name = "Article")
    @Operation(summary = "get article", description = "get article by user authenticate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "article found success", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ArticleEntity.class))),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content(array = @ArraySchema(arraySchema = @Schema(implementation = ErrorMessageDTO.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "401", description = "wrong credentials", content = @Content(array = @ArraySchema(arraySchema = @Schema(implementation = ErrorMessageDTO.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "user not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MessageResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "article not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MessageResponseDTO.class))),

    })
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ArticleWithDetailsDTO> get(
            @PathVariable String articleId,
            HttpServletRequest request) {

        var userId = request.getAttribute("userId");

        ArticleWithDetailsDTO article = getPublishArticleWithDetailsUseCase.execute(
                UUID.fromString(userId.toString()),
                UUID.fromString(articleId));

        return ResponseEntity.ok().body(article);
    }
}
