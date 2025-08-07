package com.log.dev.api.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.log.dev.api.dtos.ErrorMessageDTO;

@ControllerAdvice
public class ExceptionHandlerMiddleware {

    MessageSource messageSource;

    public ExceptionHandlerMiddleware(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handlerMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        List<ErrorMessageDTO> dto = new ArrayList<>();

        e.getBindingResult().getFieldErrors().forEach(error -> {
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            dto.add(new ErrorMessageDTO(message, error.getField()));
        });

        return ResponseEntity.badRequest().body(dto);
    }

    @ExceptionHandler(WrongCredentialsException.class)
    public ResponseEntity<MessageResponseDTO> handleWrongCredentialsException(WrongCredentialsException ex) {
        MessageResponseDTO response = new MessageResponseDTO(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(response);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<MessageResponseDTO> handleUserNotFoundException(UserNotFoundException ex) {
        MessageResponseDTO response = new MessageResponseDTO(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(response);
    }

}
