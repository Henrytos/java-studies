package school.sptech.parte1;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.Episodio;
import school.sptech.Midia;
import school.sptech.provider.episodio.BuscarProvider;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Episodio")
public class EpisodioTest {

    @Nested
    @DisplayName("1. Atributos")
    class AtributosTest {

        @Test
        @DisplayName("1. Validar Atributos")
        void test1() {
            Class<Episodio> clazz = Episodio.class;

            assertAll(
                    () -> assertDoesNotThrow(() -> clazz.getDeclaredField("podcast"), "Deve possuir o atributo podcast")
            );
        }

        @Test
        @DisplayName("2. Validar Atributos 'final'")
        void test2() {
            Class<Episodio> clazz = Episodio.class;

            Stream<Executable> validacoes = Stream.of("podcast")
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
            Class<Episodio> clazz = Episodio.class;

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
            Class<Episodio> clazz = Episodio.class;
            Field[] campos = clazz.getDeclaredFields();

            Stream<Executable> validacoes = Arrays.stream(campos)
                    .map(campo -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
                            String.format("%s deve ser privado", campo.getName())));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("2. Métodos Públicos")
        void test2() {
            Class<Episodio> clazz = Episodio.class;

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

      @Test
      @DisplayName("3. Atributos devem possuir apenas getters")
      void test3() {
        Class<Episodio> clazz = Episodio.class;
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
    }

    @Nested
    @DisplayName("4. Método - buscar")
    class MetodoBuscarTest {

        @DisplayName("Teste 1")
        @ParameterizedTest
        @ArgumentsSource(BuscarProvider.class)
        void test1(Object obj, Object query, Boolean expected) throws ReflectiveOperationException {
            Class<Episodio> clazz = Episodio.class;
            Method method = clazz.getDeclaredMethod("buscar", String.class);

            Object response = method.invoke(obj, (String) query);

            assertEquals(expected, response);
        }
    }
}
