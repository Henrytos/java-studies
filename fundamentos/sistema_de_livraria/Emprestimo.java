import java.time.LocalDate;
import java.util.UUID;

public class Emprestimo {

    private UUID id;
    private LocalDate dataDeEmprestimo;
    private LocalDate dataDeDevolucao;
    private Cliente cliente;

    public Emprestimo(
            UUID id,
            LocalDate dataDeEmprestimo,
            LocalDate dataDeDevolucao,
            Cliente cliente

    ) {
        this.id = id;
        this.dataDeEmprestimo = dataDeEmprestimo;
        this.dataDeDevolucao = dataDeDevolucao;
        this.cliente = cliente;
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getDataDeEmprestimo() {
        return dataDeEmprestimo;
    }

    public LocalDate getDataDeDevolucao() {
        return dataDeDevolucao;
    }

    public Cliente getCliente() {
        return cliente;
    }

}
