package school.sptech;

import java.util.concurrent.ThreadLocalRandom;

public class Acumulador {
    public static void main(String[] args) {
        Integer currentRandomNumber = ThreadLocalRandom.current().nextInt(0,11);
        Integer sumRandomNumbers = 0;

        String messageOperationMath = """
                %d + %d = %d
                """;

        while (!currentRandomNumber.equals(0)){
            System.out.println(messageOperationMath.formatted(sumRandomNumbers, currentRandomNumber, (sumRandomNumbers+currentRandomNumber)));
            sumRandomNumbers += currentRandomNumber.intValue();

            currentRandomNumber = ThreadLocalRandom.current().nextInt(0,11);
        }

        String message = """
                A soma dos números é %d
                """.formatted(sumRandomNumbers);

        System.out.println(message);
    }
}
