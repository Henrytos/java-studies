package school.sptech;

import java.util.Scanner;

public class SwitchCase {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite u numero de 1-7");
        Integer target = scanner.nextInt();


        switch (target){
            case 1:
                System.out.println("domingo");
                break;
            case 2:
                System.out.println("segunda feira");
                break;
            case 3:
                System.out.println("terca feira");
                break;
            case 4:
                System.out.println("quarta feira");
                break;
            case 5:
                System.out.println("quinta feira");
                break;
            case 6:
                System.out.println("sexta feira");
                break;
            case 7:
                System.out.println("sabado");
                break;
            default:
                System.out.println("chapou");
                break;
        }

        System.out.println();

    }

}
