import java.util.List;

public class Biblioteca {

    private List<Livro> livros;
    private List<Autor> autores;
    private List<Emprestimo> emprestimos;
    private List<Cliente> clientes;

    public Biblioteca(
            List<Livro> livros,
            List<Autor> autores,
            List<Emprestimo> emprestimos,
            List<Cliente> clientes) {
        this.livros = livros;
        this.autores = autores;
        this.emprestimos = emprestimos;
        this.clientes = clientes;
    }

    public List<Livro> getLivros() {
        return this.livros;
    }

    public List<Autor> getAutores() {
        return this.autores;
    }

    public List<Emprestimo> getEmprestimos() {
        return this.emprestimos;
    }

    public int getQuantidadeLivrosDiponiveis() {
        int quantidade = 0;

        for (Livro livro : livros) {
            if (livro.getDisponivel())
                quantidade++;
        }

        return quantidade;

    }

    public List<Cliente> getClientes() {
        return this.clientes;
    }

}