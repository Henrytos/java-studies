package com.henry.gestao_de_vagas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class PrimeiroTest {

    @Test
    public void deve_ser_possivel_calcular_soma() {
        var result = calcularSoma(3, 2);

        assertEquals(result, 5);
    }

    @Test
    public void nao_deve_calcular_erroniamente() {
        var result = calcularSoma(3, 2);

        assertNotEquals(result, 4);
    }

    public static int calcularSoma(int a, int b) {
        return a + b;
    }
}
