package com.henry.challenge1.modules.videos.models;

import com.henry.challenge1.modules.categories.models.CategoryEntity;
import com.henry.challenge1.modules.videos.models.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "videos")
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

    @ManyToOne
    @JoinColumn(name = "fk_category", referencedColumnName = "category_id")
    private CategoryEntity category;

}
