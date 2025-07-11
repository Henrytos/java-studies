import java.time.LocalDate;
import java.util.UUID;

public class Pessoa {
    private UUID id;
    private String nome;
    private LocalDate dataNascimento;

    public Pessoa(
            String nome,
            LocalDate dataNascimento) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public UUID getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public LocalDate getDataNascimento() {
        return this.dataNascimento;
    }
}
