package com.reservas.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservas.dto.request.UsuarioRequest;
import com.reservas.dto.response.UsuarioResponse;
import com.reservas.exception.EntidadeNaoEncontradaException;
import com.reservas.exception.RegraDeNegocioException;
import com.reservas.model.Usuario;
import com.reservas.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public Page<UsuarioResponse> listar(Pageable pageable) {
        return usuarioRepository.findAll(pageable).map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public UsuarioResponse buscarPorId(Long id) {
        return toResponse(obterUsuario(id));
    }

    @Transactional
    public UsuarioResponse criar(UsuarioRequest request) {
        if (usuarioRepository.findByEmailIgnoreCase(request.email()).isPresent()) {
            throw new RegraDeNegocioException("Já existe um usuário com esse e-mail");
        }
        Usuario usuario = new Usuario();
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        return toResponse(usuarioRepository.save(usuario));
    }

    @Transactional
    public UsuarioResponse atualizar(Long id, UsuarioRequest request) {
        Usuario usuario = obterUsuario(id);
        if (usuarioRepository.existsByEmailIgnoreCaseAndIdNot(request.email(), id)) {
            throw new RegraDeNegocioException("Já existe um usuário com esse e-mail");
        }
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        return toResponse(usuarioRepository.save(usuario));
    }

    @Transactional
    public void remover(Long id) {
        Usuario usuario = obterUsuario(id);
        usuarioRepository.delete(usuario);
    }

    @Transactional(readOnly = true)
    public Usuario obterUsuario(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado"));
    }

    private UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}