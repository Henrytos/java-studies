package school.sptech;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Exercicio2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numeroUsuario;

        do {
            System.out.print("Digite um número entre 0 e 100: ");
            numeroUsuario = scanner.nextInt();
        } while (numeroUsuario < 0 || numeroUsuario > 100);

        int sorteios = 0;
        int numeroSorteado;
        int primeiraOcorrencia = -1;

        System.out.println("Sorteando 200 números...");
        for (int i = 1; i <= 200 && primeiraOcorrencia == -1; i++) {
            numeroSorteado = ThreadLocalRandom.current().nextInt(0, 101);
            sorteios++;

            if (numeroSorteado == numeroUsuario)
                primeiraOcorrencia = sorteios;

        }

        if (primeiraOcorrencia == -1) {
            System.out.println("Vish nunca que acerta haha...");
            return;
        }

        if (primeiraOcorrencia < 10) {
            System.out.println("Você é MUITO sortudo");
        } else if (primeiraOcorrencia <= 50) {
            System.out.println("Você é sortudo");
        } else {
            System.out.println("Melhor parar de apostar e ir trabalhar!");
        }
    }
}