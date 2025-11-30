package com.henry.challenge1.modules.videos.controllers;

import com.henry.challenge1.modules.videos.controllers.dtos.VideoResponseDTO;
import com.henry.challenge1.modules.videos.useCases.FetchVideosUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/videos")
@RequiredArgsConstructor
public class FetchVideosController {

    private final FetchVideosUseCase fetchVideosUseCase;

    @GetMapping
    public ResponseEntity<Page<VideoResponseDTO>> fetch(
            Pageable pageable
    ){
        Page<VideoResponseDTO> videos  = this.fetchVideosUseCase.execute(pageable);
        return ResponseEntity.ok(videos);
    }

}
