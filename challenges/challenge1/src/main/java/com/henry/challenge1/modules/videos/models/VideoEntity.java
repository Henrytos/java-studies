package com.henry.challenge1.modules.videos.models;

import com.henry.challenge1.modules.videos.controllers.dtos.RegisterVideoRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name = "videos")
@Data
@EqualsAndHashCode( of = "id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String url;



    public VideoEntity(RegisterVideoRequestDTO dto){
        this.title = dto.title();
        this.description = dto.description();
        this.url = dto.url();
    }

}
