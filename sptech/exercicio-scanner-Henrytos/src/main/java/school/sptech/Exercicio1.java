package school.sptech;

import java.util.Scanner;

public class Exercicio1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite seu nome:");
        String nome = scanner.nextLine();

        System.out.println("Quantas provas fez?");
        Integer quantidadeDeProvas = scanner.nextInt();

        Double soma = 0.0;

        for (int i = quantidadeDeProvas; i >= 1; i--) {
            System.out.println("Digite nota da prova");
            soma += scanner.nextDouble();
        }

        Double media = soma / Double.valueOf(quantidadeDeProvas);

        String mensagem = """
                  Aluno: %s
                  Média: %.2f
                """.formatted(nome, media);

        System.out.println(mensagem);
    }
}