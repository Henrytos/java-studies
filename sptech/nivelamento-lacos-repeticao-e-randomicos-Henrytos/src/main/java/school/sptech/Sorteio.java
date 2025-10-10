package school.sptech;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Sorteio {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite um numero entre 0 a 100");
        Integer numberTarget = scanner.nextInt();

        if (numberTarget > 100 || numberTarget < 0) {
            throw new RuntimeException("Numero precisa esta no intervalo de 0 a 100 seu jegue ;-;");
        }

        List<Integer> quantityNumber = new ArrayList<>();
        quantityNumber.add(0);
        quantityNumber.add(0);

        Optional<Integer> firstRandomTarget = Optional.empty();
        for (int i = 1; i <= 200; i++) {
            Integer randomNumber = ThreadLocalRandom.current().nextInt(0, 201);
            Integer position = randomNumber % 2;

            if (randomNumber.equals(numberTarget) && firstRandomTarget.isEmpty()) {
                firstRandomTarget = Optional.of(i);
            }

            quantityNumber.set(position, quantityNumber.get(position) + 1);
        }

        String message = """
                    Numero escolhido foi sorteado na %d vez
                    Foram sorteados %d pares.
                    Foram sorteados %d impares.
                """.formatted(firstRandomTarget.get(), quantityNumber.get(0), quantityNumber.get(1));

        System.out.println(message);
    }

}
