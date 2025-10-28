package school.sptech;

import java.util.ArrayList;
import java.util.List;

public class PetShop {

    private String nome;
    private List<Pet> pets;

    public PetShop() {
        this.pets = new ArrayList<>();
    }

    public PetShop(String nome) {
        this();
        this.nome = nome;
    }

    public void receberPet(Pet pet){
        this.pets.add(pet);
    }

    public void alimentarPets(){
        for (Pet pet : this.pets) {
            pet.comer();
            pet.emitirSom();
        }
    }

    @Override
    public String toString() {
        return "PetShop{" +
               "nome='" + nome + '\'' +
               ", pets=" + pets +
               '}';
    }
}
