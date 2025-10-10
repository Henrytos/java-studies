package school.sptech;

import java.util.concurrent.ThreadLocalRandom;

public class Votacao {

    public static void main(String[] args) {

        Integer quantityVoteMozzarellaPizza = 0;
        Integer quantityVoteCalabresaPizza = 0;
        Integer quantityVoteFourCheesePizza = 0;

        for(int studentIndex = 1; studentIndex <=10; studentIndex++){
            Integer randomNumber = ThreadLocalRandom.current().nextInt(1,4);

            if(randomNumber == 1)
                quantityVoteMozzarellaPizza++;
            else if(randomNumber == 2)
                quantityVoteCalabresaPizza++;
            else
                quantityVoteFourCheesePizza++;
        }

        String pizzaWinner = "";

        if (quantityVoteMozzarellaPizza > quantityVoteCalabresaPizza && quantityVoteMozzarellaPizza > quantityVoteFourCheesePizza) {
            pizzaWinner = "Mussarela";
        } else if (quantityVoteCalabresaPizza > quantityVoteMozzarellaPizza && quantityVoteCalabresaPizza > quantityVoteFourCheesePizza) {
            pizzaWinner = "Calabresa";
        } else if (quantityVoteFourCheesePizza > quantityVoteMozzarellaPizza && quantityVoteFourCheesePizza > quantityVoteCalabresaPizza) {
            pizzaWinner = "Quatro queijos";
        } else {
            pizzaWinner = "Empate na votação";
        }


        String mesage = """
                    Quantidade de votos para mussarela: %d votos
                    Quantidade de votos para calabresa: %d votos
                    Quantidade de votos para quatro queijos: %d votos
                    
                    Vencedora foi: %s
                """.formatted(quantityVoteMozzarellaPizza, quantityVoteCalabresaPizza, quantityVoteFourCheesePizza, pizzaWinner);

        System.out.println(mesage);
    }
}
