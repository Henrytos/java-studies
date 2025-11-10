package school.sptech.provider;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class SequenciaDeFibonacciProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(
          org.junit.jupiter.api.extension.ExtensionContext context) {
        return Stream.of(
              // Casos básicos
              Arguments.of(1, new int[]{1}),
              Arguments.of(2, new int[]{1, 1}),
              Arguments.of(3, new int[]{1, 1, 2}),
              Arguments.of(5, new int[]{1, 1, 2, 3, 5}),
              Arguments.of(7, new int[]{1, 1, 2, 3, 5, 8, 13}),
              Arguments.of(10, new int[]{1, 1, 2, 3, 5, 8, 13, 21, 34, 55}),

              // Casos maiores
              Arguments.of(12, new int[]{1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144}),
              Arguments.of(15, new int[]{1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610}),

              // Casos limite
              Arguments.of(0, new int[]{}), // Nenhum termo
              Arguments.of(-5, new int[]{}), // Número negativo → retorno vazio esperado

              // Teste de valor médio — verifica se a sequência continua correta
              Arguments.of(8, new int[]{1, 1, 2, 3, 5, 8, 13, 21}),

              // Teste para garantir consistência (mesmo resultado que 7 mas truncado)
              Arguments.of(6, new int[]{1, 1, 2, 3, 5, 8})
        );
    }

}