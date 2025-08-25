package io;

import java.util.Scanner;

public class ExampleScanner {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("DIGITE SEU NOME:");
        String nome = scanner.nextLine();

        System.out.println("DIGITE SEU SOBRE NOME:");
        String sobreNOme = scanner.nextLine();


        System.out.printf("Olá %s %s bem vindo a seues estudos", nome, sobreNOme);

        scanner.close(); // sempre liberar recursos da jvm
    }

}
