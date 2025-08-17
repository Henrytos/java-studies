package com.log.dev.api.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchArticleDTO {
    private UUID id;
    private String authorName;
    private String title;
    private String subTitle;
    private int quantityLikes;
    private int quantityComments;
}
