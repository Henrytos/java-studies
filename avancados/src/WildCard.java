// wild card => corigna, estabelece uma classe desconhecida que extend uma
// classe conhecida ou determinada

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

public class WildCard {

    static void mostarAnimais(List<? extends Animal> animais){

        for (Animal animal :animais){
            System.out.println(animal);
        }

    }


    public static void main(String[] args) {
        Cachorro cachorro = new Cachorro();
        Gato gato = new Gato();

        System.out.println("----------ANIMAIS----------");
        List<Animal> animais = List.of(cachorro, gato);
        mostarAnimais(animais);

        System.out.println("----------GATOS----------");
        List<Gato> gatos = List.of(gato, gato);
        mostarAnimais(gatos);

        System.out.println("----------CACHORROS----------");
        List<Cachorro> cachorros = List.of(cachorro, cachorro);
        mostarAnimais(cachorros);

    }

}
