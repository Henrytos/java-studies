package com.henry.challenge1.configs;


import com.henry.challenge1.configs.dtos.ErrorMessageResponseDTO;
import com.henry.challenge1.modules._core.exceptions.ConflictResourceException;
import com.henry.challenge1.modules._core.exceptions.ResourceNotFoundException;
import com.henry.challenge1.modules._core.exceptions.WrongCredentialsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

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

}
