package school.sptech;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Sorteio2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite um numero entre 0 a 100");
        Integer numberTarget = scanner.nextInt();

        if (numberTarget > 100 || numberTarget < 0) {
            throw new RuntimeException("Numero precisa esta no intervalo de 0 a 100 seu jegue ;-;");
        }

        Integer MAX_RANDOM_NUMBERS = 200;
        Integer quantityNumbersPairs = 0;
        Integer quantityNumbersOdd = 0;

        Integer firstRandomTarget = 0;
        for (int i = 1; i <= MAX_RANDOM_NUMBERS; i++) {
            Integer randomNumber = ThreadLocalRandom.current().nextInt(0, 201);

            if (randomNumber.equals(numberTarget))
                firstRandomTarget = i;

            if (randomNumber % 2 == 0)
                quantityNumbersPairs++;
        }
        quantityNumbersOdd = MAX_RANDOM_NUMBERS - quantityNumbersPairs;

        String message = """
                    Numero escolhido foi sorteado na %d vez
                    Foram sorteados %d pares.
                    Foram sorteados %d impares.
                """.formatted(firstRandomTarget, quantityNumbersPairs, quantityNumbersOdd);

        System.out.println(message);
    }

}
