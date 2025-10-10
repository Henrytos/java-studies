package school.sptech;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Loteria {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite um numero inteiro entre 0 e 10:");
        Integer numberTarget = scanner.nextInt();

        if (numberTarget > 10 || numberTarget < 0) {
            System.out.println("Opção invalida de numero , encerrando programa..:(");
            return;
        }

        Integer randomNumber = ThreadLocalRandom.current().nextInt(1,11);
        Integer quantityRandomNumber = 1;
        String message = """
                Sorteio %d
                Numero sorteado: %d
                """;

        StringBuilder messageFinal = new StringBuilder();

        while (!numberTarget.equals(randomNumber)){
            messageFinal.append(message.formatted(quantityRandomNumber,randomNumber)).append(System.lineSeparator());
            randomNumber = ThreadLocalRandom.current().nextInt(1,11);
            quantityRandomNumber++;
        }

        System.out.println(messageFinal);
        if(quantityRandomNumber < 4){
            System.out.println("Você é MUITO sortudo");
        } else if(quantityRandomNumber <= 10){
            System.out.println("Você é sortudo");
        }else {
            System.out.println("É melhor você parar de apostar e ir trabalhar");
        }

    }

}
