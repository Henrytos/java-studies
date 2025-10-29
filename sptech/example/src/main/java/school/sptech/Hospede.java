package school.sptech;

public abstract class Hospede {
    private String nome;
    private String documento;
    private Integer quarto;
    private Integer dias;
    private Double consumo;

    public abstract Double calcularDiaria();

    public Double calcularTotalEstadia() {
        return this.calcularDiaria() * dias;
    }


    public Hospede() {
    }

    public Hospede(String nome, String documento, Integer quarto, Integer dias, Double consumo) {
        this.nome = nome;
        this.documento = documento;
        this.quarto = quarto;
        this.dias = dias;
        this.consumo = consumo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Integer getQuarto() {
        return quarto;
    }

    public void setQuarto(Integer quarto) {
        this.quarto = quarto;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public Double getConsumo() {
        return consumo;
    }

    public void setConsumo(Double consumo) {
        this.consumo = consumo;
    }

    @Override
    public String toString() {
        return "Hospede{" +
               "nome='" + nome + '\'' +
               ", documento='" + documento + '\'' +
               ", quarto=" + quarto +
               ", dias=" + dias +
               ", consumo=" + consumo +
               '}';
    }
}
