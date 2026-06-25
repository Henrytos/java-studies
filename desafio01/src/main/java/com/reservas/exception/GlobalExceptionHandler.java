package com.reservas.exception;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<ApiErrorResponse> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException exception,
            HttpServletRequest request) {
        return build(HttpStatus.NOT_FOUND, "Recurso não encontrado", "Recurso não encontrado", request);
    }

    @ExceptionHandler(ReservaConflitanteException.class)
    public ResponseEntity<ApiErrorResponse> handleReservaConflitante(ReservaConflitanteException exception,
            HttpServletRequest request) {
        return build(HttpStatus.CONFLICT, "Conflito de horário na reserva", "Conflito de horário na reserva", request);
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<ApiErrorResponse> handleRegraDeNegocio(RegraDeNegocioException exception,
            HttpServletRequest request) {
        return build(HttpStatus.BAD_REQUEST, "Erro de negócio", exception.getMessage(), request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(MethodArgumentNotValidException exception,
            HttpServletRequest request) {
        String mensagem = exception.getBindingResult().getFieldErrors().stream()
                .map(this::formatFieldError)
                .collect(Collectors.joining("; "));
        return build(HttpStatus.BAD_REQUEST, "Erro de validação", mensagem, request);
    }

    private String formatFieldError(FieldError error) {
        return error.getField() + ": " + error.getDefaultMessage();
    }

    private ResponseEntity<ApiErrorResponse> build(HttpStatus status, String erro, String mensagem,
            HttpServletRequest request) {
        ApiErrorResponse response = new ApiErrorResponse(LocalDateTime.now(), status.value(), erro, mensagem,
                request.getRequestURI());
        return ResponseEntity.status(status).body(response);
    }
}