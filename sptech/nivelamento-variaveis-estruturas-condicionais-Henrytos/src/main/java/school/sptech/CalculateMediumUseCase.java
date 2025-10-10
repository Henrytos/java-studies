package school.sptech;

import java.util.Arrays;
import java.util.Scanner;

public class CalculateMediumUseCase {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("DIgite seu nome:");
        String username = scanner.next();

        System.out.println("Digite um numero:");
        Double numberOne = scanner.nextDouble();

        System.out.println("Digite um numero novamente:");
        Double numberTwo = scanner.nextDouble();

        String message = """
                Olá, %s. Sua média foi de %.2f
                """.formatted(username, sum(numberOne, numberTwo));

        System.out.println(message);
    }

    static public Double sum(Double ...args){
        return Arrays.stream(args).reduce(0.0, Double::sum);
    }

}
