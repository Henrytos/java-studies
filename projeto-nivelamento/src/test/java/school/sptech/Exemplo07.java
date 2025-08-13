package school.sptech;

import java.util.concurrent.ThreadLocalRandom;

public class Exemplo07 {
    public static void main(String[] args) {
        // Math.floor() * 11 + 10
        Integer numero = ThreadLocalRandom.current().nextInt(10,21);
        System.out.println(numero.toString());

        Double numero2 = ThreadLocalRandom.current().nextDouble(10,21);
        System.out.println(numero2.toString());

        Boolean b= ThreadLocalRandom.current().nextBoolean();
        System.out.println(b);
    }
}
