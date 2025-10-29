package school.sptech;

public class Quadrado extends Figura {

    private Double lado;

    public Quadrado(){}

    public Quadrado(Double lado) {
        this.lado = lado;
    }

    public Quadrado(String cor, Integer espessura, Double lado) {
        super(cor, espessura);
        this.lado = lado;
    }

    public Double getLado() {
        return lado;
    }

    public void setLado(Double lado) {
        this.lado = lado;
    }

    @Override
    public Double calcularArea() {
        return this.lado * this.lado;
    }

    @Override
    public String toString() {
        return "Quadrado{" +
               "lado=" + lado +
               "} " + super.toString();
    }
}
