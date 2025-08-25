package com.log.dev.api.modules.author.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.log.dev.api.dtos.CreateArticleRequestDTO;
import com.log.dev.api.dtos.ErrorMessageDTO;
import com.log.dev.api.dtos.MessageResponseDTO;
import com.log.dev.api.dtos.PublishArticleRequestDTO;
import com.log.dev.api.dtos.UpdateArticleRequestDTO;
import com.log.dev.api.modules.author.entities.ArticleEntity;
import com.log.dev.api.modules.author.useCases.DeleteArticleUseCase;
import com.log.dev.api.modules.author.useCases.EditArticleUseCase;
import com.log.dev.api.modules.author.useCases.ListMyPublishArticlesUseCase;
import com.log.dev.api.modules.author.useCases.PublishArticleByAuthorUseCase;
import com.log.dev.api.modules.author.useCases.WriteArticleUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/articles")
public class ArticleController {
        final private WriteArticleUseCase writeArticleUseCase;

        final private EditArticleUseCase updateArticleUseCase;

        final private DeleteArticleUseCase deleteArticleUseCase;

        final private PublishArticleByAuthorUseCase publishArticleByAuthorUseCase;

        final private ListMyPublishArticlesUseCase listMyPublishArticlesUseCase;

        public ArticleController(
                        WriteArticleUseCase writeArticleUseCase,
                        EditArticleUseCase updateArticleUseCase,
                        DeleteArticleUseCase deleteArticleUseCase,
                        PublishArticleByAuthorUseCase publishArticleByAuthorUseCase,
                        ListMyPublishArticlesUseCase listMyPublishArticlesUseCase) {
                this.writeArticleUseCase = writeArticleUseCase;
                this.updateArticleUseCase = updateArticleUseCase;
                this.deleteArticleUseCase = deleteArticleUseCase;
                this.publishArticleByAuthorUseCase = publishArticleByAuthorUseCase;
                this.listMyPublishArticlesUseCase = listMyPublishArticlesUseCase;

        }

        @GetMapping("/me")
        @Tag(name = "Article")
        @Operation(summary = "list me article published", description = "list articles published by the authenticated user")
        @ApiResponses(value = {
        })
        @PreAuthorize("hasRole('USER')")
        public ResponseEntity<MessageResponseDTO> me(
                        HttpServletRequest request,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int perPage) {
                var userId = request.getAttribute("userId");
                PublishArticleRequestDTO dto = new PublishArticleRequestDTO(UUID.fromString(userId.toString()),
                                UUID.fromString(articleId));

                this.publishArticleByAuthorUseCase.execute(dto);

                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(new MessageResponseDTO("article published success"));
        }

        @PostMapping("/{articleId}")
        @Tag(name = "Article")
        @Operation(summary = "write article", description = "write article by user authenticate")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "article created success", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ArticleEntity.class))),
                        @ApiResponse(responseCode = "400", description = "bad request", content = @Content(array = @ArraySchema(arraySchema = @Schema(implementation = ErrorMessageDTO.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)),
                        @ApiResponse(responseCode = "401", description = "wrong credentials", content = @Content(array = @ArraySchema(arraySchema = @Schema(implementation = ErrorMessageDTO.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)),
                        @ApiResponse(responseCode = "404", description = "user not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MessageResponseDTO.class))),
                        @ApiResponse(responseCode = "404", description = "article not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MessageResponseDTO.class))),
        })
        @PreAuthorize("hasRole('USER')")
        public ResponseEntity<MessageResponseDTO> publish(
                        @PathVariable String articleId,
                        HttpServletRequest request) {
                var userId = request.getAttribute("userId");
                PublishArticleRequestDTO dto = new PublishArticleRequestDTO(UUID.fromString(userId.toString()),
                                UUID.fromString(articleId));

                this.publishArticleByAuthorUseCase.execute(dto);

                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(new MessageResponseDTO("article published success"));
        }

        @PostMapping()
        @Tag(name = "Article")
        @Operation(summary = "write article", description = "write article by user authenticate")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "article created success", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ArticleEntity.class))),
                        @ApiResponse(responseCode = "400", description = "bad request", content = @Content(array = @ArraySchema(arraySchema = @Schema(implementation = ErrorMessageDTO.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)),
                        @ApiResponse(responseCode = "404", description = "user not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MessageResponseDTO.class))),

        })
        @PreAuthorize("hasRole('USER')")
        public ResponseEntity<ArticleEntity> write(
                        @Valid @RequestBody CreateArticleRequestDTO dto,
                        HttpServletRequest request) {
                var userId = request.getAttribute("userId");

                ArticleEntity article = this.writeArticleUseCase.execute(UUID.fromString(userId.toString()), dto);

                return ResponseEntity.status(HttpStatus.CREATED).body(article);
        }

        @PutMapping("/{articleId}")
        @Tag(name = "Article")
        @Operation(summary = "UPdate article", description = "Update article by user authenticate")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = "article updated success"),
                        @ApiResponse(responseCode = "400", description = "bad request", content = @Content(array = @ArraySchema(arraySchema = @Schema(implementation = ErrorMessageDTO.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)),
                        @ApiResponse(responseCode = "404", description = "article not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MessageResponseDTO.class))),

        })
        @PreAuthorize("hasRole('USER')")
        public ResponseEntity<ArticleEntity> edit(
                        @Valid @RequestBody UpdateArticleRequestDTO dto,
                        @PathVariable String articleId,
                        HttpServletRequest request) {

                var userId = request.getAttribute("userId");
                this.updateArticleUseCase.execute(UUID.fromString(userId.toString()), UUID.fromString(articleId), dto);

                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        @DeleteMapping("/{articleId}")
        @Tag(name = "Article")
        @Operation(summary = "delete article", description = "delete article by user authenticate")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = "article deleted success"),
                        @ApiResponse(responseCode = "400", description = "bad request", content = @Content(array = @ArraySchema(arraySchema = @Schema(implementation = ErrorMessageDTO.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)),
                        @ApiResponse(responseCode = "401", description = "wrong credentials", content = @Content(array = @ArraySchema(arraySchema = @Schema(implementation = ErrorMessageDTO.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)),
                        @ApiResponse(responseCode = "404", description = "user not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MessageResponseDTO.class))),
                        @ApiResponse(responseCode = "404", description = "article not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MessageResponseDTO.class))),

        })
        @PreAuthorize("hasRole('USER')")
        public ResponseEntity<ArticleEntity> delete(
                        @PathVariable String articleId,
                        HttpServletRequest request) {

                var userId = request.getAttribute("userId");

                deleteArticleUseCase.execute(UUID.fromString(userId.toString()), UUID.fromString(articleId));

                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

}
