package com.log.dev.api.modules.author.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.stereotype.Service;

import com.log.dev.api.dtos.CreateArticleRequestDTO;
import com.log.dev.api.exceptions.UserNotFoundException;
import com.log.dev.api.modules.author.entities.ArticleEntity;
import com.log.dev.api.modules.author.repositories.ArticleRepository;
import com.log.dev.api.modules.user.entities.UserEntity;
import com.log.dev.api.modules.user.repositories.UserRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class WriteArticleUseCaseTest {

    @Autowired
    private WriteArticleUseCase writeArticleUseCase;

}
