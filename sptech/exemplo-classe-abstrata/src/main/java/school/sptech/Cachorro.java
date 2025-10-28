package school.sptech;

import java.util.SortedMap;

public class Cachorro extends Pet{

    public Cachorro() {
    }

    public Cachorro(String nome, Double peso, Integer idade) {
        super(nome, peso, idade);
    }

    @Override
    public void comer() {
        System.out.println("Comendo ração Pedigree");
    }

    @Override
    public void emitirSom() {
        System.out.println("AU AU AU....");
    }


}
