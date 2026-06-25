package com.reservas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "salas")
public class Sala {
	/** Identificador técnico gerado automaticamente para a sala. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** Nome legível da sala, usado para identificação humana e regras de unicidade. */
	@Column(nullable = false, unique = true)
	private String nome;

	/** Capacidade máxima de pessoas que a sala comporta. */
	@Positive
	@Column(nullable = false)
	private int capacidade;

	/** Indica se a sala pode ser usada para novas reservas. */
	@Column(nullable = false)
	private boolean ativa = true;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public boolean isAtiva() {
		return ativa;
	}

	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}
}