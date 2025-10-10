package school.sptech;

import java.util.Arrays;
import java.util.Scanner;

public class CalculateIncomeByChildrenUseCase {

    public static void main(String[] args) {
        System.out.println("""
                Sistema de receita de renda 
                com base na quantidade de filhos
                """);

       try {
           Scanner scanner = new Scanner(System.in);

           System.out.println("Quantidade de filhos de 0-3 anos:");
           Integer quantityBabies = scanner.nextInt();

           System.out.println("Quantidade de filhos de 4-16 anos:");
           Integer quantityKids = scanner.nextInt();

           System.out.println("Quantidade de filhos de 17-18 anos:");
           Integer quantityAdolescent = scanner.nextInt();

           Integer totalChildren = sum(quantityBabies, quantityKids, quantityAdolescent);
           Double totalIncome = execute(quantityBabies, quantityKids, quantityAdolescent);

           String message = """
                Você tem um total de %d filhos e vai receber R$%.2f de bolsa
                """.formatted(totalChildren, totalIncome);

           System.out.println(message);
       } catch (Exception e) {
           System.out.println("Erro no sistema volte mais tarde :(....");
       }
    }

    private static Double execute(Integer quantityBabies, Integer quantityKids, Integer quantityAdolescent) {
        return quantityBabies * 25.12 + quantityKids * 15.88 + quantityAdolescent * 12.44;
    }

    private static Integer sum(Integer... numbers) {
        return Arrays.stream(numbers).reduce(0, Integer::sum);
    }
}
