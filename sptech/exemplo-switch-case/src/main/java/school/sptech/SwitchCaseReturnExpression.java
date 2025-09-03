package school.sptech;

import java.util.Scanner;

public class SwitchCaseReturnExpression {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Integer target = scanner.nextInt();

        String menssagen = switch (target) {
            case 1 -> "domingo";
            case 2 -> "segunda feira";
            case 3 -> "terca feira";
            case 4 -> "quarta feira";
            case 5 -> "quinta feira";
            case 6 -> "sexta feira";
            case 7 -> "sabado";
            default -> "chapou";
        };

        System.out.println();

    }

}
