package school.sptech;

public class Triangulo extends Figura {
    private Double base;
    private Double altura;

    public Triangulo(){}

    public Triangulo(Double base, Double altura) {
        this.base = base;
        this.altura = altura;
    }

    public Triangulo(String cor, Integer espessura, Double base, Double altura) {
        super(cor, espessura);
        this.base = base;
        this.altura = altura;
    }

    @Override
    public Double calcularArea() {
        return this.base * this.altura / 2.0;
    }

    public Double getBase() {
        return base;
    }

    public void setBase(Double base) {
        this.base = base;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    @Override
    public String toString() {
        return "Triangulo{" +
               "base=" + base +
               ", altura=" + altura +
               "} " + super.toString();
    }
}
