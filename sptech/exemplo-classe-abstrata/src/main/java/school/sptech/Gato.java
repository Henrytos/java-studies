package school.sptech;

public class Gato extends Pet{

    public Gato() {
    }

    public Gato(String nome, Double peso, Integer idade) {
        super(nome, peso, idade);
    }

    @Override
    public void comer() {
        System.out.println("Comendo Whiskas");
    }

    @Override
    public void emitirSom() {
        System.out.println("MIAU MIAU MIAU");
    }
}
