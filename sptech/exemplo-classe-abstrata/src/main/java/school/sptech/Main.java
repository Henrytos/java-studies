package school.sptech;

public class Main {

    public static void main(String[] args) {
        PetShop petShop = new PetShop("SPets");

        Cachorro cachorro = new Cachorro("Ozzy", 10.0, 5);
        Gato gato = new Gato("Selena Gomes", 70.0, 2);
        petShop.receberPet(cachorro);
        petShop.receberPet(gato);

        petShop.alimentarPets();
    }

}
