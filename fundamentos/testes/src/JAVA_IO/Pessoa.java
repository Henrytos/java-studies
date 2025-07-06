package testes.src.JAVA_IO;

import java.time.LocalDate;

public class Pessoa {
    private String nome;
    private String email;
    private LocalDate dataDeNascimento;

    public Pessoa(String nome, String email, LocalDate dataDeNascimento) {
        this.nome = nome;
        this.email = email;
        this.dataDeNascimento = dataDeNascimento;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }
}
