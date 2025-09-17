package school.sptech;

import java.util.concurrent.ThreadLocalRandom;

public class Exemplo {
    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i <1000; i++) {
        int number = ThreadLocalRandom.current().nextInt(1,5);
            builder.append(number).append(System.lineSeparator());
        }

        System.out.println(builder);

    }
}
