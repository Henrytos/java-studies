import java.time.LocalDate;
import java.util.UUID;

public class Livro {
    private UUID id;
    private String titulo;
    private Autor autor;
    private boolean disponivel;
    private LocalDate dataCadastro;
    private LocalDate dataAtualização;

    public Livro(
            UUID id,
            Autor autor,
            boolean disponivel,
            LocalDate dataCadastro,
            LocalDate dataAtualização,
            String titulo) {
        this.id = id;
        this.autor = autor;
        this.disponivel = disponivel;
        this.dataCadastro = dataCadastro;
        this.dataAtualização = dataAtualização;
        this.titulo = titulo;
    }

    public UUID getId() {
        return this.id;
    }

    public Autor getAutor() {
        return this.autor;
    }

    public boolean getDisponivel() {
        return this.disponivel;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public LocalDate getDataCadastro() {
        return this.dataCadastro;
    }

    public LocalDate getDataAtualização() {
        return this.dataAtualização;
    }

}
