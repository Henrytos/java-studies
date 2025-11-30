package com.henry.challenge1.modules.videos.models;

import com.henry.challenge1.modules.videos.models.enums.Status;
import jakarta.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;
}
