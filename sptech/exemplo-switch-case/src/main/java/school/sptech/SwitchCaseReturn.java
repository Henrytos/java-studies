package school.sptech;

import java.util.Scanner;

public class SwitchCaseReturn {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Integer target = scanner.nextInt();

        String menssagen = switch (target) {
            case 1:
                yield "domingo";
            case 2:
                yield "segunda feira";
            case 3:
                yield "terca feira";
            case 4:
                yield "quarta feira";
            case 5:
                yield "quinta feira";
            case 6:
                yield "sexta feira";
            case 7:
                yield "sabado";
            default:
                yield "chapou";
        };

        System.out.println();

    }

}
