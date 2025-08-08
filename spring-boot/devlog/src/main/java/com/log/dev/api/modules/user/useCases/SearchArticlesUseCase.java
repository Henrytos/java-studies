package com.log.dev.api.modules.user.useCases;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.log.dev.api.dtos.SearchArticleResponseDTO;
import com.log.dev.api.dtos.SearchArticlesRequestDTO;
import com.log.dev.api.exceptions.ArticleNotFoundException;
import com.log.dev.api.modules.author.entities.PublishArticleEntity;
import com.log.dev.api.modules.author.repositories.PublishArticleRepository;

@Service
public class SearchArticlesUseCase {

    private PublishArticleRepository publishArticleRepository;

    public SearchArticlesUseCase(PublishArticleRepository publishArticleRepository) {
        this.publishArticleRepository = publishArticleRepository;
    }

    public SearchArticleResponseDTO execute(SearchArticlesRequestDTO search) throws ArticleNotFoundException {
        int initialPage = search.page() - 1;
        Pageable pageable = PageRequest.of(initialPage, search.perPage());

        Page<PublishArticleEntity> publishArticlesTargetPage = this.publishArticleRepository
                .findByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseOrTagsNameContainingIgnoreCase(
                        search.title(), search.content(), search.tags(), pageable);

        if (publishArticlesTargetPage.isEmpty()) {
            throw new ArticleNotFoundException();
        }

        List<PublishArticleEntity> publishArticles = publishArticlesTargetPage.getContent();

        return new SearchArticleResponseDTO(
                publishArticles,
                search.page(),
                search.perPage(),
                publishArticlesTargetPage.getTotalPages(),
                publishArticlesTargetPage.getTotalElements());

    }

}
