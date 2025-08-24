package com.log.dev.api.modules.user.useCases;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.log.dev.api.dtos.SearchArticleDTO;
import com.log.dev.api.dtos.SearchPublishArticleResponseDTO;
import com.log.dev.api.modules.author.entities.PublishArticleEntity;
import com.log.dev.api.modules.author.repositories.PublishArticleRepository;

@Service
public class ListPublishArticlesRecentUseCase {

    private final PublishArticleRepository publishArticleRepository;

    public ListPublishArticlesRecentUseCase(PublishArticleRepository publishArticleRepository) {
        this.publishArticleRepository = publishArticleRepository;
    }

    public SearchPublishArticleResponseDTO execute(Integer page, Integer perPage) {

        Pageable pageable = PageRequest.of(page, perPage);

        Page<PublishArticleEntity> publishArticles = publishArticleRepository.findRecentArticles(pageable);

        List<SearchArticleDTO> publishArticlesDTO = publishArticles.stream().map(article -> {
            SearchArticleDTO searchArticleDTO = SearchArticleDTO.builder()
                    .authorName(article.getArticle().getAuthor().getUsername())
                    .title(article.getArticle().getTitle())
                    .subTitle(article.getArticle().getSubTitle())
                    .quantityLikes(article.getLikes() == null ? 0 : article.getLikes().size())
                    .quantityComments(article.getComments() == null ? 0 : article.getComments().size())
                    .build();
            return searchArticleDTO;
        }).collect(Collectors.toList());

        return new SearchPublishArticleResponseDTO(
                publishArticlesDTO,
                pageable.getPageNumber(),
                pageable.getPageSize(),
                publishArticles.getTotalPages(),
                publishArticles.getTotalElements());
    }

}
