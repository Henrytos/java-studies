package school.sptech.provider;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class MatrizTranspostaProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(
          org.junit.jupiter.api.extension.ExtensionContext context) {
        return Stream.of(
              // 🔹 Caso base: matriz 3x3
              Arguments.of(
                    new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                    new int[][]{{1, 4, 7}, {2, 5, 8}, {3, 6, 9}}
              ),

              // 🔹 Matriz retangular 2x3 → 3x2
              Arguments.of(
                    new int[][]{{1, 2, 3}, {4, 5, 6}},
                    new int[][]{{1, 4}, {2, 5}, {3, 6}}
              ),

              // 🔹 Matriz 3x2 → 2x3
              Arguments.of(
                    new int[][]{{1, 2}, {3, 4}, {5, 6}},
                    new int[][]{{1, 3, 5}, {2, 4, 6}}
              ),

              // 🔹 Matriz identidade 3x3
              Arguments.of(
                    new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}},
                    new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}}
              ),

              // 🔹 Matriz com todos iguais
              Arguments.of(
                    new int[][]{{7, 7, 7}, {7, 7, 7}},
                    new int[][]{{7, 7}, {7, 7}, {7, 7}}
              ),

              // 🔹 Matriz com números negativos
              Arguments.of(
                    new int[][]{{-1, -2, -3}, {-4, -5, -6}},
                    new int[][]{{-1, -4}, {-2, -5}, {-3, -6}}
              ),

              // 🔹 Matriz com zeros e positivos
              Arguments.of(
                    new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}},
                    new int[][]{{0, 3, 6}, {1, 4, 7}, {2, 5, 8}}
              ),

              // 🔹 Matriz 1x1
              Arguments.of(
                    new int[][]{{42}},
                    new int[][]{{42}}
              ),

              // 🔹 Matriz 1x4 → 4x1
              Arguments.of(
                    new int[][]{{9, 8, 7, 6}},
                    new int[][]{{9}, {8}, {7}, {6}}
              ),

              // 🔹 Matriz 4x1 → 1x4
              Arguments.of(
                    new int[][]{{1}, {2}, {3}, {4}},
                    new int[][]{{1, 2, 3, 4}}
              ),

              // 🔹 Matriz com grandes números
              Arguments.of(
                    new int[][]{{1000, 2000}, {3000, 4000}},
                    new int[][]{{1000, 3000}, {2000, 4000}}
              ),

              // 🔹 Matriz “em escada”
              Arguments.of(
                    new int[][]{{1, 2, 3, 4}, {0, 5, 6, 7}, {0, 0, 8, 9}},
                    new int[][]{{1, 0, 0}, {2, 5, 0}, {3, 6, 8}, {4, 7, 9}}
              )
        );
    }

}