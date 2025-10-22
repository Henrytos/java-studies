package school.sptech;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private Integer id;
    private List<Produto> produtos = new ArrayList<>();

    public Carrinho() {
    }

    public Carrinho(Integer id, List<Produto> produtos) {
        this.id = id;
        this.produtos = produtos;
    }

    public void adicionarProduto(Produto produto) {
        if (
                produto.preco == null
                ||
                produto.preco < 0
                ||
                produto.getNome() == null
                ||
                produto.getNome().isBlank()
        ) return;

        this.produtos.add(produto);
    }

    public void removerProduto(String nome) {
        if (nome == null) {
            return;
        }

        for (int i = this.produtos.size() - 1; i >= 0; i--) {
            Produto produto = produtos.get(i);

            if (produto.getNome().equalsIgnoreCase(nome))
                produtos.remove(produto);
        }

//        this.produtos = this.produtos.stream().filter(p -> !p.getNome().equalsIgnoreCase(nome)).toList();
    }

//    public Produto buscarProdutoPorNome(String nome) {
//        return this.produtos.stream().filter(p -> p.getNome().equalsIgnoreCase(nome)).findFirst().orElse(null);
//    }

    public Produto buscarProdutoPorNome(String nome) {
        Produto produto = null;

        for (Produto produtoAtual : this.produtos) {
            if (produtoAtual.getNome().equalsIgnoreCase(nome)) {
                produto = produtoAtual;
                break;
            }
        }

        return produto;
    }

//    public Double calcularValorTotal() {
//
//        return this.produtos.stream().mapToDouble(Produto::calcularPrecoFinal).sum();
//    }
//

    public Double calcularValorTotal() {
        Double valorTotal = 0.0;

        for (Produto produto : this.produtos) {
            valorTotal += produto.calcularPrecoFinal();
        }

        return valorTotal;
    }


    //    public List<Celular> buscarCelularesPorMarca(String marca) {
//        List<Celular> celulares = new ArrayList<>();
//
//        for (Produto produto : this.produtos) {
//            if (produto instanceof Celular)
//                celulares.add((Celular) produto);
//        }
//
//        return celulares.stream().filter(p -> p.getMarca().equalsIgnoreCase(marca)).toList();
//    }
    public List<Celular> buscarCelularesPorMarca(String marca) {
        List<Celular> celulares = new ArrayList<>();

        for (Produto produto : this.produtos) {
            if (produto instanceof Celular)
                celulares.add((Celular) produto);
        }
        for (int i = celulares.size() - 1; i >= 0; i--) {
            Celular celular = celulares.get(i);
            if (!celular.getMarca().equals(marca)) {
                celulares.remove(celular);
            }

        }
        return celulares;
    }

//    public List<Celular> buscarCelularesForaDaGarantia(Integer mesesDeUso) {
//        List<Celular> celulares = new ArrayList<>();
//
//        for (Produto produto : this.produtos) {
//            if (produto instanceof Celular)
//                celulares.add((Celular) produto);
//        }
//
//        return celulares.stream().filter(c -> mesesDeUso > c.getGarantiaMeses()).toList();
//    }

    public List<Celular> buscarCelularesForaDaGarantia(Integer mesesDeUso) {
        List<Celular> celulares = new ArrayList<>();

        for (Produto produto : this.produtos) {
            if (produto instanceof Celular)
                celulares.add((Celular) produto);
        }

        for (int i = celulares.size() - 1; i >= 0; i--) {
            Celular celular = celulares.get(i);
            if (!(mesesDeUso > celular.getGarantiaMeses())) {
                celulares.remove(celular);
            }
        }

        return celulares;
    }

    public Produto buscarProdutoMaisBarato() {
        if (this.produtos.isEmpty())
            return null;

        Produto produtoMaisBarato = this.produtos.getFirst();

        for (Produto produto : this.produtos) {
            if (produto.getPreco() < produtoMaisBarato.getPreco()) {
                produtoMaisBarato = produto;
            }
        }

        return produtoMaisBarato;
    }

    public Produto buscarProdutoMaisCaro() {
        if (this.produtos.isEmpty())
            return null;

        Produto produtoMaisCaro = this.produtos.get(0);

        for (Produto produto : this.produtos) {
            if (produto.calcularPrecoFinal() > produtoMaisCaro.calcularPrecoFinal()) {
                produtoMaisCaro = produto;
            }
        }

        return produtoMaisCaro;
    }

    public List<Produto> buscarTopCincoProdutosMaisBaratos() {
        List<Produto> produtosMaisBaratos = new ArrayList<>();
        Integer lastIndex = Math.min(this.produtos.size(), 5);

        while (produtosMaisBaratos.size() < lastIndex) {
            Produto produtoMaisBarato = this.produtos.get(0);

            for (int i = 1; i < this.produtos.size(); i++) {
                Produto produtoAtual = this.produtos.get(i);
                if (
                        produtoAtual.calcularPrecoFinal() < produtoMaisBarato.calcularPrecoFinal()
                ) {
                    produtoMaisBarato = produtoAtual;
                }
            }

            produtosMaisBaratos.add(produtoMaisBarato);
            this.produtos.remove(produtoMaisBarato);
        }

        return produtosMaisBaratos;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
