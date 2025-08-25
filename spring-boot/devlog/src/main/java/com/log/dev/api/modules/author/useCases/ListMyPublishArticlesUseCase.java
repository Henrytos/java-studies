package com.log.dev.api.modules.author.useCases;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.log.dev.api.dtos.ListMyPublishArticlesRequestDTO;
import com.log.dev.api.dtos.ListMyPublishArticlesResponseDTO;
import com.log.dev.api.dtos.SearchArticleDTO;
import com.log.dev.api.exceptions.UserNotFoundException;
import com.log.dev.api.modules.author.entities.PublishArticleEntity;
import com.log.dev.api.modules.author.repositories.PublishArticleRepository;
import com.log.dev.api.modules.user.repositories.UserRepository;

@Service
public class ListMyPublishArticlesUseCase {

    private final UserRepository userRepository;

    private final PublishArticleRepository publishArticleRepository;

    public ListMyPublishArticlesUseCase(
            UserRepository userRepository,
            PublishArticleRepository publishArticleRepository) {
        this.userRepository = userRepository;
        this.publishArticleRepository = publishArticleRepository;
    }

    public ListMyPublishArticlesResponseDTO execute(ListMyPublishArticlesRequestDTO dto) throws UserNotFoundException {

        var author = userRepository.findById(dto.getUserId())
                .orElseThrow(UserNotFoundException::new);

        Pageable pageable = PageRequest.of(dto.getPage(), dto.getPerPage());

        Page<PublishArticleEntity> page = publishArticleRepository.findManyByAuthorId(author.getId(), pageable);

        List<SearchArticleDTO> articleDTO = page.stream().map(article -> {
            SearchArticleDTO searchArticleDTO = SearchArticleDTO.builder()
                    .authorName(article.getArticle().getAuthor().getUsername())
                    .title(article.getArticle().getTitle())
                    .subTitle(article.getArticle().getSubTitle())
                    .quantityLikes(article.getLikes() == null ? 0 : article.getLikes().size())
                    .quantityComments(article.getComments() == null ? 0 : article.getComments().size())
                    .build();
            return searchArticleDTO;
        }).toList();

        return new ListMyPublishArticlesResponseDTO(
                articleDTO,
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements());

    }
}
