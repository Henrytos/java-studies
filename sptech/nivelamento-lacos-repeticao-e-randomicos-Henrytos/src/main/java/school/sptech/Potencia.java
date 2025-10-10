package school.sptech;

import java.util.Scanner;

public class Potencia {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite a base:");
        Integer base = scanner.nextInt();

        System.out.println("Digite o exponente:");
        Integer exponent = scanner.nextInt();

        String message = """
                %d ** %d = %d
                """.formatted(base,exponent, pow(base,exponent));

        System.out.println(message);
    }

    public static Long pow(Integer base, Integer exponent) {
        if (exponent < 0) {
            throw new RuntimeException("Expoente negativo não é suportado.");
        }

        if (base < 0) {
            throw new RuntimeException("Base negativa não é suportada.");
        }

        return powRecursive(base, exponent, 1L);
    }

    private static Long powRecursive(Integer base, Integer exponent, Long accumulator) {
        if (exponent.equals(0)) {
            return accumulator;
        }

        return powRecursive(base, exponent - 1, accumulator * base);
    }
}
