package com.henry.challenge1.modules.categories.models;

import com.henry.challenge1.modules.videos.models.VideoEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String title;

    private String color;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<VideoEntity> videos;

}
