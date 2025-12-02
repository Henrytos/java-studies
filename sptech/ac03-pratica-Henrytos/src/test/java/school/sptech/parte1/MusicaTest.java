package school.sptech.parte1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.Musica;
import school.sptech.provider.musica.BuscarProvider;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Musica")
public class MusicaTest {

    @Nested
    @DisplayName("1. Atributos")
    class AtributosTest {

        @Test
        @DisplayName("1. Validar Atributos")
        void test1() {
            Class<Musica> clazz = Musica.class;

            assertAll(
                    () -> assertDoesNotThrow(() -> clazz.getDeclaredField("artista"), "Deve possuir o atributo artista"),
                    () -> assertDoesNotThrow(() -> clazz.getDeclaredField("album"), "Deve possuir o atributo album"),
                    () -> assertDoesNotThrow(() -> clazz.getDeclaredField("genero"), "Deve possuir o atributo genero")
            );
        }

        @Test
        @DisplayName("2. Validar Atributos 'final'")
        void test2() {
            Class<Musica> clazz = Musica.class;

            Stream<Executable> validacoes = Stream.of("artista", "album", "genero")
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
            Class<Musica> clazz = Musica.class;

            assertAll(
                    () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("buscar", String.class), "Deve possuir o método buscar")
            );
        }
    }

    @Nested
    @DisplayName("3. Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("1. Atributos Privados")
        void test1() {
            Class<Musica> clazz = Musica.class;
            Field[] campos = clazz.getDeclaredFields();

            Stream<Executable> validacoes = Arrays.stream(campos)
                    .map(campo -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
                            String.format("%s deve ser privado", campo.getName())));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("2. Métodos Públicos")
        void test2() {
            Class<Musica> clazz = Musica.class;

            List<Method> metodos = new ArrayList<>();

            try {
                metodos.add(clazz.getDeclaredMethod("buscar", String.class));
            } catch (ReflectiveOperationException ignored) {}

            Stream<Executable> validacoes = metodos.stream()
                    .map(metodo -> () -> {
                        assertTrue(Modifier.isPublic(metodo.getModifiers()));
                    });

            assertAll(validacoes);
        }
    }

    @Nested
    @DisplayName("4. Método - buscar")
    class MetodoBuscarTest {

        @DisplayName("Teste 1")
        @ParameterizedTest
        @ArgumentsSource(BuscarProvider.class)
        void test1(Object obj, Object query, Boolean expected) throws ReflectiveOperationException {
            Class<Musica> clazz = Musica.class;
            Method method = clazz.getDeclaredMethod("buscar", String.class);

            Object response = method.invoke(obj, (String) query);

            assertEquals(expected, response);
        }
    }
}
