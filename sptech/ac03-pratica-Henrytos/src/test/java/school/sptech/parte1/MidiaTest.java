package school.sptech.parte1;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import school.sptech.Midia;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Midia")
public class MidiaTest {

    @Nested
    @DisplayName("1. Atributos")
    class AtributosTest {

        @Test
        @DisplayName("1. Validar Atributos")
        void test1() {
            Class<Midia> clazz = Midia.class;

            assertAll(
                    () -> assertDoesNotThrow(() -> clazz.getDeclaredField("nome"), "Deve possuir o atributo nome"),
                    () -> assertDoesNotThrow(() -> clazz.getDeclaredField("duracaoMinutos"), "Deve possuir o atributo duracaoMinutos")
            );
        }

        @Test
        @DisplayName("2. Validar Atributos 'final'")
        void test2() {
            Class<Midia> clazz = Midia.class;

            Stream<Executable> validacoes = Stream.of("nome", "duracaoMinutos")
                    .map(nomeCampo -> () -> {
                        Field campo = clazz.getDeclaredField(nomeCampo);
                        assertTrue(Modifier.isFinal(campo.getModifiers()),
                                String.format("%s deve ser final", nomeCampo));
                    });

            assertAll(validacoes);
        }
    }

    @Nested
    @DisplayName("2. Métodos")
    class MetodosTest {

        @Test
        @DisplayName("1. Validar Métodos")
        void test1() {
            Class<Midia> clazz = Midia.class;

            assertAll(
                    () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("buscar", String.class), "Deve possuir o método buscar")
            );
        }

        @Test
        @DisplayName("2. Métodos devem ser abstratos")
        void test2() {
            Class<Midia> clazz = Midia.class;

            assertAll(
                    () -> assertTrue(Modifier.isAbstract(
                            clazz.getDeclaredMethod("buscar", String.class).getModifiers()), "O método buscar deve ser abstrato")
            );
        }
    }

    @Nested
    @DisplayName("3. Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("1. Atributos Privados")
        void test1() {
            Class<Midia> clazz = Midia.class;
            Field[] campos = clazz.getDeclaredFields();

            Stream<Executable> validacoes = Arrays.stream(campos)
                    .map((campo) -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
                            String.format("%s deve ser privado", campo.getName())));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("2. Métodos Públicos")
        void test2() {
            Class<Midia> clazz = Midia.class;

            List<Method> metodos = new ArrayList<>();

            try {
                metodos.add(clazz.getDeclaredMethod("buscar", String.class));
            } catch (ReflectiveOperationException ignored) {
            }

            Stream<Executable> validacoes = metodos.stream()
                    .map((metodo) -> () -> {
                        assertTrue(Modifier.isPublic(metodo.getModifiers()),
                                String.format("%s deve ser público", metodo.getName()));
                    });

            assertAll(validacoes);
        }

        @Test
        @DisplayName("3. Atributos devem possuir apenas getters")
        void test3() {
            Class<Midia> clazz = Midia.class;
            Field[] campos = clazz.getDeclaredFields();

            Stream<Executable> validacoesGetter = Arrays.stream(campos)
                    .map((campo) -> () -> {
                        String getName = String.format("get%s",
                                StringUtils.capitalize(campo.getName()));
                        assertDoesNotThrow(() -> {
                            Method getter = clazz.getDeclaredMethod(getName);
                            int getModifier = getter.getModifiers();
                            assertTrue(Modifier.isPublic(getModifier),
                                    String.format("Getter %s deve ser público", getName));
                        }, String.format("Deve possuir o getter %s", getName));
                    });

            Stream<Executable> validacoesSetter = Arrays.stream(campos)
                    .map(campo -> () -> {
                        String setName = String.format("set%s", StringUtils.capitalize(campo.getName()));
                        Assertions.assertThrows(NoSuchMethodException.class, () -> {
                            clazz.getDeclaredMethod(setName, campo.getType());
                        }, String.format("%s não deve possuir setter", campo.getName()));
                    });

            assertAll(Stream.concat(validacoesGetter, validacoesSetter));
        }

        @Test
        @DisplayName("4. Classe deve ser abstrata")
        void test4() {
            Class<Midia> clazz = Midia.class;
            assertAll(
                    () -> assertTrue(Modifier.isAbstract(clazz.getModifiers()),
                            "A classe deve ser abstrata"));
        }
    }
}
