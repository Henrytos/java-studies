package school.sptech.parte2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import school.sptech.Avaliavel;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("Avaliavel - Parte 2")
public class AvaliavelTest {

    @Nested
    @DisplayName("1. Interface")
    class InterfaceTest {

        @Test
        @DisplayName("1. Validar Interface")
        void test1() {
            Class<Avaliavel> clazz = Avaliavel.class;

            assertAll(
                    () -> assertDoesNotThrow(() -> {
                        if (!clazz.isInterface()) {
                            throw new NoSuchMethodException("Avaliavel deve ser uma interface");
                        }
                    }, "Avaliavel deve ser uma interface")
            );
        }
    }

    @Nested
    @DisplayName("2. Métodos")
    class MetodosTest {

        @Test
        @DisplayName("1. Validar Métodos")
        void test1() {
            Class<Avaliavel> clazz = Avaliavel.class;

            assertAll(
                    () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("avaliar", Integer.class), "Deve possuir o método avaliar"),
                    () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("calcularMedia"), "Deve possuir o método calcularMedia")
            );
        }
    }
}
