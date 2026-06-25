package com.reservas.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.reservas.dto.request.SalaRequest;
import com.reservas.dto.response.SalaResponse;
import com.reservas.service.SalaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/salas")
@Validated
public class SalaController {

    private final SalaService salaService;

    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }

    @GetMapping
    public Page<SalaResponse> listar(Pageable pageable) {
        return salaService.listar(pageable);
    }

    @GetMapping("/{id}")
    public SalaResponse buscarPorId(@PathVariable Long id) {
        return salaService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SalaResponse criar(@Valid @RequestBody SalaRequest request) {
        return salaService.criar(request);
    }

    @PutMapping("/{id}")
    public SalaResponse atualizar(@PathVariable Long id, @Valid @RequestBody SalaRequest request) {
        return salaService.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        salaService.desativar(id);
        return ResponseEntity.noContent().build();
    }
}