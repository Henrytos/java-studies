package school.sptech;

public class Celular extends Produto {
    private Integer garantiaMeses;
    private String marca;

    public Celular() {
    }

    public Celular(Integer garantiaMeses, String marca) {
        this.garantiaMeses = garantiaMeses;
        this.marca = marca;
    }

    public Celular(Double peso, Double preco, String nome, Integer garantiaMeses, String marca) {
        super(peso, preco, nome);
        this.garantiaMeses = garantiaMeses;
        this.marca = marca;
    }

    public Double calcularPrecoFinal(){
        return super.calcularPrecoFinal()+(this.garantiaMeses * 50);
    }

    public Integer getGarantiaMeses() {
        return garantiaMeses;
    }

    public void setGarantiaMeses(Integer garantiaMeses) {
        this.garantiaMeses = garantiaMeses;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
