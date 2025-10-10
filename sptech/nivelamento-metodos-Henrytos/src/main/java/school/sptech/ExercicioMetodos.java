package school.sptech;

import java.util.Arrays;

public class ExercicioMetodos {
    Boolean verificarMaioridade(Integer idade) {
        return idade >= 18;
    }

    Double calcularMedia(Double valor1, Double valor2, Double valor3) {
        return (valor1 + valor2 + valor3) / 3.0;
    }


    Integer maiorNumero(Integer valor1, Integer valor2, Integer valor3) {
        if (valor1 > valor2 && valor1 > valor3) {
            return valor1;
        } else if (valor2 > valor1 && valor2 > valor3) {
            return valor2;
        } else if (valor3 > valor2 && valor3 > valor1) {
            return valor3;
        } else {
            return valor1;
        }

    }

    Integer calcularFatorial(Integer valor) {
        if (valor.equals(0))
            return 1;

        if (valor.equals(1))
            return 1;

        return valor * calcularFatorial(valor - 1);
    }

    Boolean verificarPrimo(Integer valor) {
        if (valor <= 1)
            return false;

        for (int i = valor - 1; i >= 2; i--) {
            if (valor % i == 0) {
                return false;
            }
        }

        return true;
    }

    Integer calcularPotencia(Integer base, Integer expoente) {
        return calcularPotenciaRecursiva(base, expoente, 1);
    }

    static Integer calcularPotenciaRecursiva(Integer base, Integer exponent, Integer accumulator) {
        if (exponent.equals(0)) {
            return accumulator;
        }

        return calcularPotenciaRecursiva(base, exponent - 1, accumulator * base);
    }

    Integer calcularTrocoEmBalas(Double valorCompra, Double valorRecebido) {
        Double valorDaBala = 0.25;
        Double troco = valorRecebido - valorCompra;
        Integer quantidadeDeBalas = 0;

        while (troco >= valorDaBala) {
            quantidadeDeBalas++;
            troco -= valorDaBala;
        }

        return quantidadeDeBalas;
    }

    Boolean verificarPalindromo(String palavra) {
        palavra = palavra.replaceAll(" ", "").replaceAll(",", "").toLowerCase();
        int lastPosition = palavra.length() - 1;

        for (int i = 0; i <= lastPosition; i++)
            if (palavra.charAt(i) != palavra.charAt(lastPosition - i))
                return false;

        return true;
    }
}
