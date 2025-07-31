package com.log.dev.api.modules.user.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.log.dev.api.dtos.CreateUserRequestDTO;
import com.log.dev.api.modules.user.UserEntity;
import com.log.dev.api.modules.user.useCases.CreateUserUseCase;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CreateUserUseCase createUserUseCase;

    @PostMapping()
    public ResponseEntity<UserEntity> create(@Valid @RequestBody CreateUserRequestDTO dto) {
        UserEntity user = this.createUserUseCase.execute(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

}
