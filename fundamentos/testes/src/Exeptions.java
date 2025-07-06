package testes.src;

import java.util.Scanner;

public class Exeptions {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("DIGITE SEU NOME:");
            String nome = scanner.next();

            System.out.println("DIGITE SUA IDADE:");
            int idade = scanner.nextInt();

            System.out.println("DIGITE SEU EMAIL:");
            String email = scanner.next();

            System.out.println("OLÁ " + nome);
            System.out.println("SUA IDADE " + idade);
            System.out.println("SEU EMAIL " + email);
        } catch (Exception e) {
            System.out.println("OCORREU UM ERRO");
        } finally {
            System.out.println("finalizando programa....");
        }
    }
}
