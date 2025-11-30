package com.henry.challenge1.modules.videos.controllers;

import com.henry.challenge1.modules.videos.controllers.dtos.RegisterVideoRequestDTO;
import com.henry.challenge1.modules.videos.controllers.dtos.VideoResponseDTO;
import com.henry.challenge1.modules.videos.useCases.RegisterVideoUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/videos")
@RequiredArgsConstructor
public class RegisterVideoController {

    private final RegisterVideoUseCase registerVideoUseCase;

    @PostMapping
    public ResponseEntity<VideoResponseDTO> register(@RequestBody @Valid RegisterVideoRequestDTO body, UriComponentsBuilder uriBuilder) {
        VideoResponseDTO response = this.registerVideoUseCase.execute(body);

        URI uri = uriBuilder.path("/api/v1/videos/{videoId}").buildAndExpand(response.id()).toUri();

        return ResponseEntity.created(uri).body(response);
    }

}
