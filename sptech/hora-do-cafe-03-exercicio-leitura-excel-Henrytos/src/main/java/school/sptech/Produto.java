package school.sptech;

import java.time.LocalDate;

public class Produto {

    private Integer id;
    private String nome;
    private Double valor;
    private String marca;
    private LocalDate dataLancamento;

    public Produto() {
    }

    public Produto(Integer id, String nome, Double valor, String marca, LocalDate dataLancamento) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.marca = marca;
        this.dataLancamento = dataLancamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    @Override
    public String toString() {
        return "Produto{" +
               "id=" + id +
               ", nome='" + nome + '\'' +
               ", valor=" + valor +
               ", marca='" + marca + '\'' +
               ", dataLancamento=" + dataLancamento +
               '}';
    }
}
