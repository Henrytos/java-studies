package com.henry.challenge1.configs;


import com.henry.challenge1.configs.dtos.ErrorMessageResponseDTO;
import com.henry.challenge1.modules._core.exceptions.ConflictResourceException;
import com.henry.challenge1.modules._core.exceptions.ResourceNotFoundException;
import com.henry.challenge1.modules._core.exceptions.WrongCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler {

    //    UseCases
    @ExceptionHandler(ConflictResourceException.class)
    public ResponseEntity<ErrorMessageResponseDTO> handleConflictResourceException(ConflictResourceException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(new ErrorMessageResponseDTO(ex.getMessage(), ex.getStatusCode()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessageResponseDTO> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(new ErrorMessageResponseDTO(ex.getMessage(), ex.getStatusCode()));
    }

    @ExceptionHandler(WrongCredentialsException.class)
    public ResponseEntity<ErrorMessageResponseDTO> handleWrongCredentialsException(WrongCredentialsException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(new ErrorMessageResponseDTO(ex.getMessage(), ex.getStatusCode()));
    }

//    Spring security

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorMessageResponseDTO> handleAuthenticationException(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorMessageResponseDTO(ex.getMessage(), HttpStatus.UNAUTHORIZED.value()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorMessageResponseDTO> handleAccessDeniedException(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorMessageResponseDTO(ex.getMessage(), HttpStatus.FORBIDDEN.value()));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageResponseDTO> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessageResponseDTO(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }
}
