package br.com.course_rocket.api_course_rocket_seat.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlerController {

    private MessageSource messageSource;

    public ErrorHandlerController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handlerMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        List<ErrorMessageDTO> errors = new ArrayList<>();

        exception.getBindingResult().getFieldErrors().forEach(error -> {
            String message = this.messageSource.getMessage(error, LocaleContextHolder.getLocale());
            errors.add(new ErrorMessageDTO(message, error.getField()));

        });

        return ResponseEntity.badRequest().body(errors);

    }
}
