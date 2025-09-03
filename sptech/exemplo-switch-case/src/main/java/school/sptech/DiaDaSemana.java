package school.sptech;

import java.util.Scanner;

public class DiaDaSemana {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite u numero de 1-7");
        Integer target = scanner.nextInt();
        String mensagem = null;
        switch (target) {
            case 1 -> mensagem = "domingo";
            case 2 -> mensagem = "segunda feira";
            case 3 -> mensagem = "terca feira";
            case 4 -> mensagem = "quarta feira";
            case 5 -> mensagem = "quinta feira";
            case 6 -> mensagem = "sexta feira";
            case 7 -> mensagem = "sabado";
            default -> mensagem = "chapou";
        }

        System.out.println(mensagem);
    }

}
