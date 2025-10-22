package school.sptech;

import java.util.Objects;

public class Produto {
    protected Double peso;
    protected Double preco;
    protected String nome;

    public Produto() {
    }

    public Produto(Double peso, Double preco, String nome) {
        this.peso = peso;
        this.preco = preco;
        this.nome = nome;
    }

    public Double calcularPrecoFinal(){
        return preco;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Produto{" +
               "peso=" + peso +
               ", preco=" + preco +
               ", nome='" + nome + '\'' +
               '}';
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(peso, produto.peso) && Objects.equals(preco, produto.preco) && Objects.equals(nome, produto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(peso, preco, nome);
    }
}
