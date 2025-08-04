package com.log.dev.api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateArticleRequestDTO {

    @NotNull
    @Schema()
    @Size(min = 10, max = 50)
    private String title;

    @NotNull
    @Size(min = 10, max = 100)
    private String subTitle;

    @NotNull
    @Size(min = 10, max = 100)
    private String content;

    @NotBlank
    @NotNull
    private String authorId;

    @NotNull
    private int readingDurationInMinutes;
}
