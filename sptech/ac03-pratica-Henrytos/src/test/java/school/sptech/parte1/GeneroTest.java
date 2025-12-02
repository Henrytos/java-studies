package school.sptech.parte1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import school.sptech.Genero;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("Genero")
public class GeneroTest {

    @Nested
    @DisplayName("1. Constantes")
    class ConstantesTest {

        @Test
        @DisplayName("1. Validar Constantes")
        void test1() {
            Class<Genero> clazz = Genero.class;

            assertAll(
                    () -> assertDoesNotThrow(() -> clazz.getDeclaredField("ROCK")),
                    () -> assertDoesNotThrow(() -> clazz.getDeclaredField("POP")),
                    () -> assertDoesNotThrow(() -> clazz.getDeclaredField("MPB")),
                    () -> assertDoesNotThrow(() -> clazz.getDeclaredField("FUNK")),
                    () -> assertDoesNotThrow(() -> clazz.getDeclaredField("ELETRONICA")),
                    () -> assertDoesNotThrow(() -> clazz.getDeclaredField("SERTANEJO"))
            );
        }
    }
}
