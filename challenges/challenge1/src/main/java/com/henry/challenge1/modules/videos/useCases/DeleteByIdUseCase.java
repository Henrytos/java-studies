package com.henry.challenge1.modules.videos.useCases;

import com.henry.challenge1.modules.videos.models.VideoEntity;
import com.henry.challenge1.modules.videos.models.enums.Status;
import com.henry.challenge1.modules.videos.repositories.JpaVideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteByIdUseCase {

    private final JpaVideoRepository jpaVideoRepository;

    public void execute(Long videoId){
        VideoEntity video = this.jpaVideoRepository.findByIdActive(videoId).orElseThrow(RuntimeException::new);
        video.setStatus(Status.INACTIVE);

        this.jpaVideoRepository.save(video);
    }

}
