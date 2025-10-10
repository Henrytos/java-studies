package school.sptech;

public class Operacao {

    private String categoria;
    private String descricao;
    private Double valor;

    public Operacao() {
    }

    public Operacao(String categoria, String descricao, Double valor) {
        this.categoria = categoria;
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
