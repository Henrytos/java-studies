package school.sptech;

public class Roupa extends Produto {

    private String cor;
    private Double tamanho;
    private String material;

    public Roupa() {
    }

    public Roupa(String cor, Double tamanho, String material) {
        this.cor = cor;
        this.tamanho = tamanho;
        this.material = material;
    }

    public Roupa(Double peso, Double preco, String nome, String cor, Double tamanho, String material) {
        super(peso, preco, nome);
        this.cor = cor;
        this.tamanho = tamanho;
        this.material = material;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Double getTamanho() {
        return tamanho;
    }

    public void setTamanho(Double tamanho) {
        this.tamanho = tamanho;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Double calcularPrecoFinal() {
        Double precoFinal = super.calcularPrecoFinal();

        if (this.material.equalsIgnoreCase("couro"))
            precoFinal = precoFinal * 0.85;

        return precoFinal;
    }

}
