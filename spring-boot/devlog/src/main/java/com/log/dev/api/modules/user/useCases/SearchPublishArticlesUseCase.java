package com.log.dev.api.modules.user.useCases;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.log.dev.api.dtos.SearchArticleDTO;
import com.log.dev.api.dtos.SearchPublishArticleResponseDTO;
import com.log.dev.api.dtos.SearchPublishArticleRequestDTO;
import com.log.dev.api.exceptions.ArticleNotFoundException;
import com.log.dev.api.modules.author.entities.PublishArticleEntity;
import com.log.dev.api.modules.author.repositories.PublishArticleRepository;

@Service
public class SearchPublishArticlesUseCase {

    private PublishArticleRepository publishArticleRepository;

    public SearchPublishArticlesUseCase(PublishArticleRepository publishArticleRepository) {
        this.publishArticleRepository = publishArticleRepository;
    }

    public SearchPublishArticleResponseDTO execute(SearchPublishArticleRequestDTO search)
            throws ArticleNotFoundException {
        int initialPage = search.page() - 1;
        Pageable pageable = PageRequest.of(initialPage, search.perPage());

        Page<PublishArticleEntity> publishArticlesTargetPage = this.publishArticleRepository
                .search(
                        search.title(), search.content(), search.tags(), pageable);

        List<PublishArticleEntity> publishArticles = publishArticlesTargetPage.getContent();
        List<SearchArticleDTO> articleDTOs = publishArticles.stream().map(article -> {
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
                articleDTOs,
                search.page(),
                search.perPage(),
                publishArticlesTargetPage.getTotalPages(),
                publishArticlesTargetPage.getTotalElements());

    }

}
