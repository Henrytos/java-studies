// wild card => corigna, estabelece uma classe desconhecida que extend uma
// classe conhecida ou determinada

// UPPER BOUNDED WILDCARDS => somente com subclasses (extends)
// LOWER BOUNDED WILDCARDS => somente com superclasse (super)
// BOUNDED WILDCARDS => => os w acima mas sem tipagem segura

import java.util.ArrayList;
import java.util.List;

abstract  class Animal{
}

class Gato extends Animal{
    @Override
    public  String toString(){
        return "sou um gato na memoria miau";
    }
}

class Cachorro extends Animal{
    @Override
    public  String toString(){
        return "sou um cachorro na memoria auau";
    }
}

public class UpperBoundedWildCard {

    static void mostarAnimais(List<? extends Animal> animais){

        for (Animal animal :animais){
            System.out.println(animal);
        }

    }


    public static void main(String[] args) {
        Cachorro cachorro = new Cachorro();
        Gato gato = new Gato();

        System.out.println("----------ANIMAIS----------");
        List<Animal> animais = new ArrayList<>();
        animais.add(cachorro);
        animais.add(gato);
        mostarAnimais(animais);

        System.out.println("----------GATOS----------");
        List<Gato> gatos = new ArrayList<>();
        gatos.add(gato);
        gatos.add(gato);
        mostarAnimais(gatos);

        System.out.println("----------CACHORROS----------");
        List<Cachorro> cachorros = new ArrayList<>();
        cachorros.add(cachorro);
        cachorros.add(cachorro);
        
        mostarAnimais(cachorros);

    }

}

