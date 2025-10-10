package school.sptech;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Carrinho {
    private String cliente;
    private List<Produto> produtos;


    public Integer getQuantidade() {
        return this.produtos.size();
    }

    public void adicionar(Produto produto) {
        this.produtos.add(produto);
    }

    public Boolean existsPorNome(String nome) {
        return this.produtos.stream().anyMatch(p->p.getNome().equalsIgnoreCase(nome));
    }

    public Integer getQuantidadePorCategoria(String categoria) {
        return this.produtos.stream().filter(p -> p.getCategoria().equals(categoria)).toList().size();
    }

    public void limpar() {
        this.produtos.clear();
    }

    public void removerPorNome(String nome) {
        this.produtos.removeIf((p)->p.getNome().equalsIgnoreCase(nome));
    }

    public Produto getPorNome(String nome) {
        Optional<Produto> target = this.produtos.stream().filter((p) -> p.getNome().equalsIgnoreCase(nome)).findFirst();
        return target.orElse(null);

    }

    public Double getValorTotal() {

        return this.produtos.stream()
                .map(Produto::getPreco)
                .reduce(0.0, Double::sum);
    }
}

