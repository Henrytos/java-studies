package com.log.dev.api.modules.user.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.log.dev.api.dtos.AuthUserRequestDTO;
import com.log.dev.api.dtos.AuthUserResponseDTO;
import com.log.dev.api.dtos.RegisterUserRequestDTO;
import com.log.dev.api.dtos.ErrorMessageDTO;
import com.log.dev.api.dtos.MessageResponseDTO;
import com.log.dev.api.modules.user.entities.UserEntity;
import com.log.dev.api.modules.user.useCases.AuthUserUseCase;
import com.log.dev.api.modules.user.useCases.GetProfileUseCase;
import com.log.dev.api.modules.user.useCases.RegisterUserUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/auth")
public class AuthUserController {

    private AuthUserUseCase authUserUseCase;

    private RegisterUserUseCase registerUserUseCase;

    final private GetProfileUseCase getProfileUseCase;

    public AuthUserController(AuthUserUseCase authUserUseCase, RegisterUserUseCase registerUserUseCase,
            GetProfileUseCase getProfileUseCase) {
        this.authUserUseCase = authUserUseCase;
        this.registerUserUseCase = registerUserUseCase;
        this.getProfileUseCase = getProfileUseCase;
    }

    @PostMapping("/user")
    @Tag(name = "Auth")
    @Operation(summary = "User Authentication Route", description = "User Authentication with Username/Password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful authentication", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AuthUserResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "poorly made request", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = ErrorMessageDTO.class)))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MessageResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "Wrong credentials", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MessageResponseDTO.class))),

    })
    public ResponseEntity<AuthUserResponseDTO> login(@Valid @RequestBody AuthUserRequestDTO dto) {
        AuthUserResponseDTO authUserResponse = this.authUserUseCase.execute(dto);

        return ResponseEntity.ok().body(authUserResponse);
    }

    @PostMapping("/register")
    @Tag(name = "Auth")
    @Operation(summary = "Register User", description = "Register User in application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful authentication", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserEntity.class))),
            @ApiResponse(responseCode = "400", description = "poorly made request", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = ErrorMessageDTO.class)))),
            @ApiResponse(responseCode = "401", description = "Wrong credentials", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MessageResponseDTO.class))),

    })
    public ResponseEntity<UserEntity> register(@Valid @RequestBody RegisterUserRequestDTO dto) {
        UserEntity user = this.registerUserUseCase.execute(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/me")
    @Operation(summary = "Get User Profile", description = "Get User Profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful authentication", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserEntity.class))),
            @ApiResponse(responseCode = "400", description = "poorly made request", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = ErrorMessageDTO.class)))),
            @ApiResponse(responseCode = "401", description = "Wrong credentials", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MessageResponseDTO.class))),

    })
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserEntity> me(HttpServletRequest request) {
        var userId = request.getAttribute("userId");

        var profile = getProfileUseCase.execute(UUID.fromString(userId.toString()));

        return ResponseEntity.ok().body(profile);
    }

}
