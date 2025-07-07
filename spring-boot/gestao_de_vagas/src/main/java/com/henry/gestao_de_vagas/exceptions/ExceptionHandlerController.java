package com.henry.gestao_de_vagas.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
 
    MessageSource messageSource;

    public ExceptionHandlerController(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDto>> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<ErrorMessageDto> dto = new ArrayList<>();

        e.getBindingResult().getFieldErrors().forEach(error->{
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            
            dto.add(new ErrorMessageDto(message, error.getField()));
        });

        return new ResponseEntity<List<ErrorMessageDto>>(dto, HttpStatus.BAD_REQUEST);
    }
}
