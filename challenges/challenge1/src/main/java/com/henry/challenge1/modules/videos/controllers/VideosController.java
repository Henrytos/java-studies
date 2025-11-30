package com.henry.challenge1.modules.videos.controllers;

import com.henry.challenge1.modules.videos.controllers.dtos.RegisterVideoRequestDTO;
import com.henry.challenge1.modules.videos.controllers.dtos.UpdateVideoRequestDTO;
import com.henry.challenge1.modules.videos.controllers.dtos.VideoResponseDTO;
import com.henry.challenge1.modules.videos.useCases.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/videos")
@RequiredArgsConstructor
public class VideosController {

    private final FetchVideosUseCase fetchVideosUseCase;

    private final RegisterVideoUseCase registerVideoUseCase;

    private final FindByVideoIdUseCase findByVideoIdUseCase;

    private final EditVideoUseCase editVideoUseCase;

    private final DeleteByIdUseCase deleteByIdUseCase;

    @GetMapping
    public ResponseEntity<Page<VideoResponseDTO>> fetch(
            Pageable pageable
    ) {
        Page<VideoResponseDTO> videos = this.fetchVideosUseCase.execute(pageable);
        return ResponseEntity.ok(videos);
    }

    @GetMapping("/{videoId}")
    public ResponseEntity<VideoResponseDTO> get(
            @PathVariable Long videoId
    ) {
        VideoResponseDTO video = this.findByVideoIdUseCase.execute(videoId);

        return ResponseEntity.ok(video);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<VideoResponseDTO> register(@RequestBody @Valid RegisterVideoRequestDTO body, UriComponentsBuilder uriBuilder) {
        VideoResponseDTO response = this.registerVideoUseCase.execute(body);

        URI uri = uriBuilder.path("/api/v1/videos/{videoId}").buildAndExpand(response.id()).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{videoId}")
    @Transactional
    public ResponseEntity<VideoResponseDTO> update(
            @PathVariable Long videoId,
            @RequestBody UpdateVideoRequestDTO body
    ) {
        VideoResponseDTO response = this.editVideoUseCase.execute(videoId, body);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{videoId}")
    @Transactional
    public ResponseEntity<Object> delete(
            @PathVariable Long videoId
    ) {
        this.deleteByIdUseCase.execute(videoId);

        return ResponseEntity.noContent().build();
    }
}
