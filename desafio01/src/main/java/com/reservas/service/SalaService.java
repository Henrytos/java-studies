package com.reservas.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservas.dto.request.SalaRequest;
import com.reservas.dto.response.SalaResponse;
import com.reservas.exception.EntidadeNaoEncontradaException;
import com.reservas.exception.RegraDeNegocioException;
import com.reservas.model.Sala;
import com.reservas.repository.SalaRepository;

@Service
public class SalaService {

    private final SalaRepository salaRepository;

    public SalaService(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    @Transactional(readOnly = true)
    public Page<SalaResponse> listar(Pageable pageable) {
        return salaRepository.findAll(pageable).map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public SalaResponse buscarPorId(Long id) {
        return toResponse(obterSala(id));
    }

    @Transactional
    public SalaResponse criar(SalaRequest request) {
        if (salaRepository.findByNomeIgnoreCase(request.nome()).isPresent()) {
            throw new RegraDeNegocioException("Já existe uma sala com esse nome");
        }
        Sala sala = new Sala();
        sala.setNome(request.nome());
        if (request.capacidade() <= 0) {
            throw new RegraDeNegocioException("capacidade deve ser maior que zero");
        }
        sala.setCapacidade(request.capacidade());
        sala.setAtiva(request.ativa() == null || request.ativa());
        return toResponse(salaRepository.save(sala));
    }

    @Transactional
    public SalaResponse atualizar(Long id, SalaRequest request) {
        Sala sala = obterSala(id);
        if (salaRepository.existsByNomeIgnoreCaseAndIdNot(request.nome(), id)) {
            throw new RegraDeNegocioException("Já existe uma sala com esse nome");
        }
        if (request.capacidade() <= 0) {
            throw new RegraDeNegocioException("capacidade deve ser maior que zero");
        }
        sala.setNome(request.nome());
        sala.setCapacidade(request.capacidade());
        if (request.ativa() != null) {
            sala.setAtiva(request.ativa());
        }
        return toResponse(salaRepository.save(sala));
    }

    @Transactional
    public void desativar(Long id) {
        Sala sala = obterSala(id);
        sala.setAtiva(false);
        salaRepository.save(sala);
    }

    @Transactional(readOnly = true)
    public Sala obterSala(Long id) {
        return salaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Sala não encontrada"));
    }

    private SalaResponse toResponse(Sala sala) {
        return new SalaResponse(sala.getId(), sala.getNome(), sala.getCapacidade(), sala.isAtiva());
    }
}