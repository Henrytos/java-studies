package com.reservas.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.reservas.dto.request.ReservaRequest;
import com.reservas.dto.response.ReservaResponse;
import com.reservas.service.ReservaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public Page<ReservaResponse> listar(Pageable pageable) {
        return reservaService.listar(pageable);
    }

    @GetMapping("/{id}")
    public ReservaResponse buscarPorId(@PathVariable Long id) {
        return reservaService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservaResponse criar(@Valid @RequestBody ReservaRequest request) {
        return reservaService.criar(request);
    }

    @PatchMapping("/{id}/cancelar")
    public ReservaResponse cancelar(@PathVariable Long id) {
        return reservaService.cancelar(id);
    }
}