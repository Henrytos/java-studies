package school.sptech;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Exercicios {

    // 🌀 Exercício 1 - Sequência de Fibonacci
    public int[] sequenciaDeFibonacci(int num) {
        List<Integer> fibonacci = new ArrayList<>();

        for (int i = 1; i <= num; i++) {

            if (fibonacci.size() >= 2) {
                Integer penultimateNumber = fibonacci.get(fibonacci.size() - 2);
                Integer lastNumber = fibonacci.getLast();

                fibonacci.add(penultimateNumber + lastNumber);
            } else {
                fibonacci.add(1);
            }

        }

        return fibonacci.stream().mapToInt(i -> i).toArray();
    }

    // 🧩 Exercício 2 - Prefixo Comum Mais Longo (Longest Common Prefix)
    public String prefixoComumMaisLongo(String[] strings) {
        if (strings.length == 0) {
            return "";
        }

        String prefixo = "";

        String menorPalavra = strings[0];

        for (String string : strings) {
            if (string.length() < menorPalavra.length()) menorPalavra = string;
        }

        System.out.println(menorPalavra);
        for (int i = 0; i < menorPalavra.toCharArray().length; i++) {
            char c = menorPalavra.charAt(i);

            for (String string : strings) {
                char ca = string.charAt(i);
                if (!(c == ca)) return prefixo;
            }

            prefixo += c;
        }

        return prefixo;
    }

    // 🔢 Exercício 3 - Converter para Hexadecimal
    public String converterParaHexadecimal(int num) {
        HashMap<Integer, String> decimalToHexaDecinal = new HashMap<>();

        for (int i = 0; i <= 9; i++) {
            decimalToHexaDecinal.put(i, String.valueOf(i));
        }

        decimalToHexaDecinal.put(10, "A");
        decimalToHexaDecinal.put(11, "B");
        decimalToHexaDecinal.put(12, "C");
        decimalToHexaDecinal.put(13, "D");
        decimalToHexaDecinal.put(14, "E");
        decimalToHexaDecinal.put(15, "F");

        List<Integer> restos = new ArrayList<>();

        Integer restAtual = num;

        while (restAtual >= 16) {
            Integer resto = restAtual % 16;
            restos.add(resto);

            restAtual = restAtual / 16;
        }

        if (restAtual > 0) {
            restos.add(restAtual);
        }

        String hexadecimal = "";

        for (int i = restos.size() - 1; i >= 0; i--) {
            Integer resto = restos.get(i);

            hexadecimal += decimalToHexaDecinal.get(resto);
        }

        return !hexadecimal.isEmpty() ? hexadecimal : "0";
    }

    // 🔄 Exercício 4 - Matriz Transposta
    public int[][] matrizTransposta(int[][] matriz) {
        int[][] matrizTranposta = new int[matriz[0].length][matriz.length];

        for (int l = 0; l < matriz.length; l++) {
            for (int c = 0; c < matriz[l].length; c++) {
                matrizTranposta[c][l] = matriz[l][c];
            }
        }

        return matrizTranposta;
    }
}
