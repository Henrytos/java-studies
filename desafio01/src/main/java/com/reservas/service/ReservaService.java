package com.reservas.service;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservas.dto.request.ReservaRequest;
import com.reservas.dto.response.ReservaResponse;
import com.reservas.dto.response.SalaResponse;
import com.reservas.dto.response.UsuarioResponse;
import com.reservas.exception.EntidadeNaoEncontradaException;
import com.reservas.exception.ReservaConflitanteException;
import com.reservas.exception.RegraDeNegocioException;
import com.reservas.model.Reserva;
import com.reservas.model.Sala;
import com.reservas.model.Usuario;
import com.reservas.model.enums.ReservaStatus;
import com.reservas.repository.ReservaRepository;
import com.reservas.repository.SalaRepository;
import com.reservas.repository.UsuarioRepository;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final SalaRepository salaRepository;
    private final UsuarioRepository usuarioRepository;

    public ReservaService(ReservaRepository reservaRepository, SalaRepository salaRepository,
            UsuarioRepository usuarioRepository) {
        this.reservaRepository = reservaRepository;
        this.salaRepository = salaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public Page<ReservaResponse> listar(Pageable pageable) {
        return reservaRepository.findAll(pageable).map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public ReservaResponse buscarPorId(Long id) {
        return toResponse(obterReserva(id));
    }

    @Transactional
    public ReservaResponse criar(ReservaRequest request) {
        if (request.inicio() == null || request.fim() == null || !request.inicio().isBefore(request.fim())) {
            throw new RegraDeNegocioException("inicio deve ser anterior a fim");
        }

        Sala sala = salaRepository.findById(request.salaId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Sala não encontrada"));
        if (!sala.isAtiva()) {
            throw new RegraDeNegocioException("Sala deve estar ativa para ser reservada");
        }
        if (sala.getCapacidade() <= 0) {
            throw new RegraDeNegocioException("Capacidade deve ser positiva");
        }

        Usuario usuario = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado"));

        if (!reservaRepository.findConflitantes(sala.getId(), request.inicio(), request.fim()).isEmpty()) {
            throw new ReservaConflitanteException("Conflito de horário na reserva");
        }

        Reserva reserva = new Reserva();
        reserva.setSala(sala);
        reserva.setUsuario(usuario);
        reserva.setInicio(request.inicio());
        reserva.setFim(request.fim());
        reserva.setStatus(ReservaStatus.ATIVA);
        return toResponse(reservaRepository.save(reserva));
    }

    @Transactional
    public ReservaResponse cancelar(Long id) {
        Reserva reserva = obterReserva(id);
        reserva.setStatus(ReservaStatus.CANCELADA);
        return toResponse(reservaRepository.save(reserva));
    }

    @Transactional(readOnly = true)
    public Reserva obterReserva(Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Reserva não encontrada"));
    }

    private ReservaResponse toResponse(Reserva reserva) {
        Sala sala = reserva.getSala();
        Usuario usuario = reserva.getUsuario();
        return new ReservaResponse(reserva.getId(), new SalaResponse(sala.getId(), sala.getNome(), sala.getCapacidade(),
                sala.isAtiva()), new UsuarioResponse(usuario.getId(), usuario.getNome(), usuario.getEmail()),
                reserva.getInicio(), reserva.getFim(), reserva.getStatus());
    }
}