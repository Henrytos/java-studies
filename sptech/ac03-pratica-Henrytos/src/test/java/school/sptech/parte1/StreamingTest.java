package school.sptech.parte1;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.Genero;
import school.sptech.Midia;
import school.sptech.Streaming;
import school.sptech.provider.streaming.*;
import school.sptech.util.EnumConstantMapping;
import school.sptech.util.ObjectFieldMapping;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Streaming")
public class StreamingTest {

    @Nested
    @DisplayName("1. Atributos")
    class AtributosTest {

        @Test
        @DisplayName("1. Validar Atributos")
        void test1() {
            Class<Streaming> clazz = Streaming.class;

            assertAll(
                    () -> assertDoesNotThrow(() -> clazz.getDeclaredField("nome"), "Deve possuir o atributo nome"),
                    () -> assertDoesNotThrow(() -> clazz.getDeclaredField("midias"), "Deve possuir o atributo midias")
            );
        }
    }

    @Nested
    @DisplayName("2. Métodos")
    class MetodosTest {

        @Test
        @DisplayName("1. Validar Métodos")
        void test1() {
            Class<Streaming> clazz = Streaming.class;

            assertAll(
                    () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("adicionarMidia", Midia.class), "Deve possuir o método adicionarMidia"),
                    () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("buscarMidia", String.class), "Deve possuir o método buscarMidia"),
                    () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("buscarMusicaPorGenero", Genero.class), "Deve possuir o método buscarMusicaPorGenero"),
                    () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("getQuantidadeEpisodios"), "Deve possuir o método getQuantidadeEpisodios")
            );
        }
    }

    @Nested
    @DisplayName("3. Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("1. Atributos Privados")
        void test1() {
            Class<Streaming> clazz = Streaming.class;
            Field[] campos = clazz.getDeclaredFields();

            Stream<Executable> validacoes = Arrays.stream(campos)
                    .map((campo) -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
                            String.format("%s deve ser privado", campo.getName())));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("2. Métodos Públicos")
        void test2() {
            Class<Streaming> clazz = Streaming.class;

            List<Method> metodos = new ArrayList<>();

            try {
                metodos.add(clazz.getDeclaredMethod("adicionarMidia", Midia.class));
                metodos.add(clazz.getDeclaredMethod("buscarMidia", String.class));
                metodos.add(clazz.getDeclaredMethod("buscarMusicaPorGenero", Genero.class));
                metodos.add(clazz.getDeclaredMethod("getQuantidadeEpisodios"));
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
            Class<Streaming> clazz = Streaming.class;
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

        @ParameterizedTest
        @ArgumentsSource(ConstructorProvider.class)
        @DisplayName("4. Construtor deve inicializar os atributos corretamente")
        void test4(String nome) throws ReflectiveOperationException {
            Class<Streaming> clazz = Streaming.class;

            assertDoesNotThrow(() -> {
                clazz.getDeclaredConstructor(String.class);
            }, "Construtor com parâmetros (String nome) deve existir");

            Object obj = clazz.getDeclaredConstructor(String.class).newInstance(nome);

            ObjectFieldMapping<Streaming> mapping = new ObjectFieldMapping<>(Streaming.class);

            assertAll(
                    () -> assertEquals(nome, mapping.get("nome").get(obj), "Nome deve ser inicializado corretamente"),
                    () -> assertIterableEquals(List.of(), (List<?>) mapping.get("midias").get(obj), "Midias deve ser inicializado corretamente")
            );
        }
    }

    @Nested
    @DisplayName("4. Método - adicionarMidia")
    class AdicionarMidiaTest {

        @ParameterizedTest
        @ArgumentsSource(AdicionarMidiaProvider.class)
        @DisplayName("1. Deve adicionar mídias corretamente")
        void test1(Object streaming, Object midia, List<?> expected) throws ReflectiveOperationException {

            Class<Streaming> clazz = Streaming.class;
            Method adicionarMidiaMethod = clazz.getDeclaredMethod("adicionarMidia", Midia.class);

            adicionarMidiaMethod.invoke(streaming, (Midia) midia);

            ObjectFieldMapping<Streaming> mapping = new ObjectFieldMapping<>(Streaming.class);

            List<?> atualMidias = (List<?>) mapping.get("midias").get(streaming);
            assertIterableEquals(expected, atualMidias, "Lista de midias deve ser atualizada corretamente após adicionar mídia");
        }
    }

    @Nested
    @DisplayName("5. Método - buscarMidia")
    class BuscarMidiaTest {

        @ParameterizedTest
        @ArgumentsSource(BuscarMidiaProvider.class)
        @DisplayName("1. Deve buscar mídias corretamente")
        void test1(Object streaming, String query, List<?> expected) throws ReflectiveOperationException {

            Class<Streaming> clazz = Streaming.class;
            Method buscarMidiaMethod = clazz.getDeclaredMethod("buscarMidia", String.class);

            List<?> resultados = (List<?>) buscarMidiaMethod.invoke(streaming, query);

            assertIterableEquals(expected, resultados, "Resultados da busca devem corresponder ao esperado");
        }
    }

    @Nested
    @DisplayName("6. Método - buscarMusicaPorGenero")
    class BuscarMusicaPorGeneroTest {

        @ParameterizedTest
        @ArgumentsSource(BuscarMusicaPorGeneroProvider.class)
        @DisplayName("1. Deve buscar músicas por gênero corretamente")
        void test1(Object streaming, String genero, List<?> expected) throws ReflectiveOperationException {

            Class<Streaming> clazz = Streaming.class;
            Method buscarMusicaPorGeneroMethod = clazz.getDeclaredMethod("buscarMusicaPorGenero", Genero.class);

            Object generoEnum = EnumConstantMapping.getEnumConstant(Genero.class, genero);
            List<?> resultados = (List<?>) buscarMusicaPorGeneroMethod.invoke(streaming, generoEnum);

            assertIterableEquals(expected, resultados, "Resultados da busca por gênero devem corresponder ao esperado");
        }
    }

    @Nested
    @DisplayName("7. Método - getQuantidadeEpisodios")
    class GetQuantidadeEpisodiosTest {

        @ParameterizedTest
        @ArgumentsSource(GetQuantidadeEpisodios.class)
        @DisplayName("1. Deve retornar a quantidade de episódios corretamente")
        void test1(Object streaming, Integer expected) throws ReflectiveOperationException {

            Class<Streaming> clazz = Streaming.class;
            Method getQuantidadeEpisodiosMethod = clazz.getDeclaredMethod("getQuantidadeEpisodios");

            Integer quantidadeEpisodios = (Integer) getQuantidadeEpisodiosMethod.invoke(streaming);

            assertEquals(expected, quantidadeEpisodios, "Quantidade de episódios deve corresponder ao esperado");
        }
    }
}
