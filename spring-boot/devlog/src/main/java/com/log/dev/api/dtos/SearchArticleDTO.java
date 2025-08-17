package com.log.dev.api.dtos;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchArticleDTO {
    @Schema(description = "Article id", example = "0e3hdq0o-dh543543bqwoe-dhb432qwo432e")
    private UUID id;

    @Schema(description = "Author name", example = "henry")
    private String authorName;

    @Schema(description = "Article title", example = "My article")
    private String title;

    @Schema(description = "Article sub-title", example = "My article sub-title")
    private String subTitle;

    @Schema(description = "Article quantity likes", example = "10")
    private int quantityLikes;

    @Schema(description = "Article quantity comments", example = "10")
    private int quantityComments;
}
