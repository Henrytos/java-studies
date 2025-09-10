package school.sptech;

import java.util.Scanner;

public class SwitchCaseMultiplesCases {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Integer target = scanner.nextInt();

        String menssagen = switch (target) {
            case 1:
            case 7:
                yield "final de semana bb";
            default:
                yield "dia util";
        };

        String menssagen2 = switch (target) {
            case 1, 7:
                yield "final de semana bb";
            default:
                yield "dia util";
        };


        String menssagen3 = switch (target) {
            case 1, 7 -> "final de semana bb";
            default -> "dia util";
        };

        System.out.println(menssagen);

    }

}
