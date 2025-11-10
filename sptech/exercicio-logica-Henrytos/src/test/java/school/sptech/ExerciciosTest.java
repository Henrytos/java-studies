package school.sptech;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Method;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.provider.ConverterParaHexadecimalProvider;
import school.sptech.provider.MatrizTranspostaProvider;
import school.sptech.provider.PrefixoComumMaisLongoProvider;
import school.sptech.provider.SequenciaDeFibonacciProvider;


@DisplayName("Exercícios - Métodos")
class ExerciciosTest {

    @Nested
    @DisplayName("sequenciaDeFibonacci()")
    class SequenciaDeFibonacciTest {

        @ParameterizedTest
        @DisplayName("Deve gerar a sequência de Fibonacci até o número informado")
        @ArgumentsSource(SequenciaDeFibonacciProvider.class)
        void testSequenciaDeFibonacci(int num, int[] expected) throws Exception {
            Class<Exercicios> clazz = Exercicios.class;
            Method method = clazz.getDeclaredMethod("sequenciaDeFibonacci", int.class);
            method.trySetAccessible();

            Exercicios obj = new Exercicios();
            Object result = method.invoke(obj, num);

            assertArrayEquals(expected, (int[]) result);
        }
    }

    @Nested
    @DisplayName("prefixoComumMaisLongo()")
    class PrefixoComumMaisLongoTest {

        @ParameterizedTest
        @DisplayName("Deve encontrar o prefixo comum mais longo entre as strings")
        @ArgumentsSource(PrefixoComumMaisLongoProvider.class)
        void testPrefixoComumMaisLongo(String[] entrada, String expected) throws Exception {
            Class<Exercicios> clazz = Exercicios.class;
            Method method = clazz.getDeclaredMethod("prefixoComumMaisLongo", String[].class);
            method.trySetAccessible();

            Exercicios obj = new Exercicios();
            Object result = method.invoke(obj, (Object) entrada);

            assertEquals(expected, result);
        }
    }

    @Nested
    @DisplayName("converterParaHexadecimal()")
    class ConverterParaHexadecimalTest {

        @ParameterizedTest
        @DisplayName("Deve converter número decimal para hexadecimal")
        @ArgumentsSource(ConverterParaHexadecimalProvider.class)
        void testConverterParaHexadecimal(int num, String expected) throws Exception {
            Class<Exercicios> clazz = Exercicios.class;
            Method method = clazz.getDeclaredMethod("converterParaHexadecimal", int.class);
            method.trySetAccessible();

            Exercicios obj = new Exercicios();
            Object result = method.invoke(obj, num);

            assertEquals(expected, result);
        }
    }

    @Nested
    @DisplayName("matrizTransposta()")
    class MatrizTranspostaTest {

        @ParameterizedTest
        @DisplayName("Deve retornar a transposta da matriz")
        @ArgumentsSource(MatrizTranspostaProvider.class)
        void testMatrizTransposta(int[][] matriz, int[][] expected) throws Exception {
            Class<Exercicios> clazz = Exercicios.class;
            Method method = clazz.getDeclaredMethod("matrizTransposta", int[][].class);
            method.trySetAccessible();

            Exercicios obj = new Exercicios();
            Object result = method.invoke(obj, (Object) matriz);

            assertArrayEquals(expected, (int[][]) result);
        }
    }
}