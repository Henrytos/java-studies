package com.reservas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.reservas.dto.request.ReservaRequest;
import com.reservas.exception.RegraDeNegocioException;
import com.reservas.exception.ReservaConflitanteException;
import com.reservas.model.Reserva;
import com.reservas.model.Sala;
import com.reservas.model.Usuario;
import com.reservas.model.enums.ReservaStatus;
import com.reservas.repository.ReservaRepository;
import com.reservas.repository.SalaRepository;
import com.reservas.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
class ReservaServiceTest {

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private SalaRepository salaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private ReservaService reservaService;

    private Sala salaAtiva;
    private Sala salaInativa;
    private Usuario usuario;
    private LocalDateTime inicio;
    private LocalDateTime fim;

    @BeforeEach
    void setUp() {
        salaAtiva = new Sala();
        salaAtiva.setId(1L);
        salaAtiva.setNome("Sala 01");
        salaAtiva.setCapacidade(10);
        salaAtiva.setAtiva(true);

        salaInativa = new Sala();
        salaInativa.setId(2L);
        salaInativa.setNome("Sala 02");
        salaInativa.setCapacidade(10);
        salaInativa.setAtiva(false);

        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Ana");
        usuario.setEmail("ana@exemplo.com");

        inicio = LocalDateTime.of(2026, 6, 24, 10, 0);
        fim = LocalDateTime.of(2026, 6, 24, 12, 0);
    }

    @Test
    void deve_criar_reserva_com_sucesso_quando_nao_houver_conflito() {
        when(salaRepository.findById(1L)).thenReturn(Optional.of(salaAtiva));
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(reservaRepository.findConflitantes(1L, inicio, fim)).thenReturn(List.of());
        when(reservaRepository.save(any(Reserva.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var response = reservaService.criar(new ReservaRequest(1L, 1L, inicio, fim));

        assertEquals(ReservaStatus.ATIVA, response.status());
        assertEquals(1L, response.sala().id());
        assertEquals(1L, response.usuario().id());
        verify(reservaRepository).save(any(Reserva.class));
    }

    @Test
    void deve_lancar_excecao_quando_houver_conflito_de_horario() {
        when(salaRepository.findById(1L)).thenReturn(Optional.of(salaAtiva));
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(reservaRepository.findConflitantes(1L, inicio, fim)).thenReturn(List.of(new Reserva()));

        assertThrows(ReservaConflitanteException.class,
                () -> reservaService.criar(new ReservaRequest(1L, 1L, inicio, fim)));
        verify(reservaRepository, never()).save(any());
    }

    @Test
    void deve_permitir_reserva_quando_inicio_for_igual_ao_fim_de_outra() {
        LocalDateTime novoInicio = fim;
        LocalDateTime novoFim = fim.plusHours(2);
        when(salaRepository.findById(1L)).thenReturn(Optional.of(salaAtiva));
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(reservaRepository.findConflitantes(1L, novoInicio, novoFim)).thenReturn(List.of());
        when(reservaRepository.save(any(Reserva.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var response = reservaService.criar(new ReservaRequest(1L, 1L, novoInicio, novoFim));

        assertEquals(ReservaStatus.ATIVA, response.status());
    }

    @Test
    void deve_bloquear_reserva_quando_houver_um_minuto_de_sobreposicao() {
        LocalDateTime novoInicio = fim.minusMinutes(1);
        LocalDateTime novoFim = fim.plusHours(1);
        when(salaRepository.findById(1L)).thenReturn(Optional.of(salaAtiva));
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(reservaRepository.findConflitantes(1L, novoInicio, novoFim)).thenReturn(List.of(new Reserva()));

        assertThrows(ReservaConflitanteException.class,
                () -> reservaService.criar(new ReservaRequest(1L, 1L, novoInicio, novoFim)));
    }

    @Test
    void deve_lancar_excecao_quando_sala_estiver_inativa() {
        when(salaRepository.findById(1L)).thenReturn(Optional.of(salaInativa));

        assertThrows(RegraDeNegocioException.class,
                () -> reservaService.criar(new ReservaRequest(1L, 1L, inicio, fim)));
        verify(usuarioRepository, never()).findById(any());
    }

    @Test
    void deve_lancar_excecao_quando_inicio_for_maior_ou_igual_ao_fim() {
        assertThrows(RegraDeNegocioException.class,
                () -> reservaService.criar(new ReservaRequest(1L, 1L, fim, inicio)));
    }

    @Test
    void deve_cancelar_reserva_quando_cancelamento_for_solicitado() {
        Reserva reserva = reservaExistente();
        when(reservaRepository.findById(1L)).thenReturn(Optional.of(reserva));
        when(reservaRepository.save(any(Reserva.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var response = reservaService.cancelar(1L);

        assertEquals(ReservaStatus.CANCELADA, response.status());
    }

    @Test
    void deve_permitir_novo_agendamento_quando_reserva_anterior_estiver_cancelada() {
        LocalDateTime mesmoInicio = inicio;
        LocalDateTime mesmoFim = fim;
        when(salaRepository.findById(1L)).thenReturn(Optional.of(salaAtiva));
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(reservaRepository.findConflitantes(1L, mesmoInicio, mesmoFim)).thenReturn(List.of());
        when(reservaRepository.save(any(Reserva.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var response = reservaService.criar(new ReservaRequest(1L, 1L, mesmoInicio, mesmoFim));

        assertEquals(ReservaStatus.ATIVA, response.status());
        ArgumentCaptor<Reserva> captor = ArgumentCaptor.forClass(Reserva.class);
        verify(reservaRepository).save(captor.capture());
        assertTrue(captor.getValue().getStatus() == ReservaStatus.ATIVA);
    }

    private Reserva reservaExistente() {
        Reserva reserva = new Reserva();
        reserva.setId(1L);
        reserva.setSala(salaAtiva);
        reserva.setUsuario(usuario);
        reserva.setInicio(inicio);
        reserva.setFim(fim);
        reserva.setStatus(ReservaStatus.ATIVA);
        return reserva;
    }
}