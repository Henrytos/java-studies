package com.log.dev.api.modules.user.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.log.dev.api.dtos.AuthUserRequestDTO;
import com.log.dev.api.dtos.AuthUserResponseDTO;
import com.log.dev.api.dtos.ErrorMessageDTO;
import com.log.dev.api.dtos.MessageResponseDTO;
import com.log.dev.api.modules.user.useCases.AuthUserUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth/user")
public class AuthUserController {

    @Autowired
    private AuthUserUseCase authUserUseCase;

    @PostMapping()
    @Tag(name = "User")
    @Operation(summary = "User Authentication Route", description = "User User Authentication with Username/Password")
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

    @PostMapping()
    @Tag(name = "User")
    @Operation(summary = "User Authentication Route", description = "User User Authentication with Username/Password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful authentication", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AuthUserResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "poorly made request", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = ErrorMessageDTO.class)))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MessageResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "Wrong credentials", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MessageResponseDTO.class))),

    })
    public ResponseEntity<AuthUserResponseDTO> register(@Valid @RequestBody AuthUserRequestDTO dto) {
        AuthUserResponseDTO authUserResponse = this.authUserUseCase.execute(dto);

        return ResponseEntity.ok().body(authUserResponse);
    }

}
