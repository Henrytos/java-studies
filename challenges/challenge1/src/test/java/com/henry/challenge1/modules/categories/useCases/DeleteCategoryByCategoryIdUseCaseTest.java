package com.henry.challenge1.modules.categories.useCases;

import com.henry.challenge1.modules.categories.models.CategoryEntity;
import com.henry.challenge1.modules.categories.repositories.JpaCategoryRepository;
import com.henry.challenge1.modules.videos.models.VideoEntity;
import com.henry.challenge1.modules.videos.repositories.JpaVideoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DeleteCategoryByCategoryIdUseCaseTest {

    @InjectMocks
    private DeleteCategoryByCategoryIdUseCase deleteCategoryByCategoryIdUseCase;

    @Mock
    private JpaCategoryRepository jpaCategoryRepository;

    @Mock
    private JpaVideoRepository jpaVideoRepository;

    @Test
    @DisplayName("It should be possible to delete a video")
    void should_be_possible_to_delete_a_video(){
        Long categoryId = 10L;

        CategoryEntity categoryMock = Mockito.mock(CategoryEntity.class);
        categoryMock.setId(categoryId);

        Mockito.when(this.jpaCategoryRepository.findById(categoryId)).thenReturn(Optional.of(categoryMock));

        CategoryEntity categoryDefaultMock = Mockito.mock(CategoryEntity.class);
        categoryDefaultMock.setId(1L);

        Mockito.when(this.jpaCategoryRepository.findById(1L)).thenReturn(Optional.of(categoryDefaultMock));

        List<VideoEntity> videos = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            VideoEntity video = Mockito.mock(VideoEntity.class);
            video.setCategory(categoryMock);
            video.setId(Long.valueOf(i));

            videos.add(video);
        }

        Mockito.when(this.jpaVideoRepository.findAllByCategoryId(categoryId)).thenReturn(videos);

        Mockito.when(this.jpaVideoRepository.saveAll(Mockito.eq(videos))).thenReturn(videos);

        this.deleteCategoryByCategoryIdUseCase.execute(categoryId);
    }


    @Test
    @DisplayName("It shouldn't be possible to delete a category if it doesn't exist")
    void should_be_possible_to_delete_a_category_if_it_does_exist(){
        Long categoryId = 10L;

        Mockito.when(this.jpaCategoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(CategoryNotFoundException.class, ()->this.deleteCategoryByCategoryIdUseCase.execute(categoryId));

        Assertions.assertEquals(CategoryNotFoundException.class, exception.getClass());
    }

}