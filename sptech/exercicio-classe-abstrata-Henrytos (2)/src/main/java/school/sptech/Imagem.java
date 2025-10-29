package school.sptech;

import java.util.ArrayList;
import java.util.List;

public class Imagem {

    private List<Figura> figuras;

    public Imagem() {
        this.figuras = new ArrayList<>();
    }

    public Imagem(List<Figura> figuras) {
        this.figuras = figuras;
    }

    public void adicionar(Figura figura) {
        this.figuras.add(figura);
    }

    public Double calcularSomaDasAreas() {
        return this.figuras.stream().mapToDouble(f -> f.calcularArea()).sum();
    }

    public List<Figura> buscarPorAreaMaiorQue20() {
        return this.figuras.stream().filter(f -> f.calcularArea() > 20.0).toList();
    }

    public List<Figura> buscarQuadrados() {
        List<Figura> quadrados = new ArrayList<>();

        for (Figura figura : this.figuras) {
            if (figura instanceof Quadrado quadrado) {
                quadrados.add(quadrado);
            }
        }

        return quadrados;
    }


}
