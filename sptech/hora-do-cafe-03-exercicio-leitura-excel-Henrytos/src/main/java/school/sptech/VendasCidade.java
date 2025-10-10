package school.sptech;

public class VendasCidade {
    private String cidade;
    private Double faturamentoTotal;

    public VendasCidade() {
    }

    public VendasCidade(String cidade, Double faturamentoTotal) {
        this.cidade = cidade;
        this.faturamentoTotal = faturamentoTotal;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Double getFaturamentoTotal() {
        return faturamentoTotal;
    }

    public void setFaturamentoTotal(Double faturamentoTotal) {
        this.faturamentoTotal = faturamentoTotal;
    }

    @Override
    public String toString() {
        return "VendasCidade{" +
               "cidade='" + cidade + '\'' +
               ", faturamentoTotal=" + faturamentoTotal +
               '}';
    }

}
