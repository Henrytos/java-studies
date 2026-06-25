package com.reservas.model;

import java.time.LocalDateTime;

import com.reservas.model.enums.ReservaStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "reservas")
public class Reserva {
	/** Identificador técnico gerado automaticamente para a reserva. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** Sala reservada para o evento ou agendamento. */
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "sala_id", nullable = false)
	private Sala sala;

	/** Usuário responsável por criar e manter a reserva. */
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

	/** Momento de início do uso da sala. */
	@NotNull
	@Column(nullable = false)
	private LocalDateTime inicio;

	/** Momento de término do uso da sala. */
	@NotNull
	@Column(nullable = false)
	private LocalDateTime fim;

	/** Estado atual da reserva, usado para diferenciar ativa de cancelada. */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ReservaStatus status = ReservaStatus.ATIVA;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}

	public LocalDateTime getFim() {
		return fim;
	}

	public void setFim(LocalDateTime fim) {
		this.fim = fim;
	}

	public ReservaStatus getStatus() {
		return status;
	}

	public void setStatus(ReservaStatus status) {
		this.status = status;
	}
}