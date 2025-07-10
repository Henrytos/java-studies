import java.util.List;

public class Biblioteca {

    List<Livro> livros;
    List<Autor> autores;
    List<Emprestimo> emprestimos;

    public Biblioteca(
            List<Livro> livros,
            List<Autor> autores,
            List<Emprestimo> emprestimos) {
        this.livros = livros;
        this.autores = autores;
        this.emprestimos = emprestimos;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

}