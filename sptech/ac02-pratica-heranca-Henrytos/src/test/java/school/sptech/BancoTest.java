package school.sptech;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.factory.BancoFactory;
import school.sptech.factory.ContaCorrentePlusFactory;
import school.sptech.provider.banco.BuscarContasComPontosMaiorProvider;
import school.sptech.provider.banco.BuscarContasPlusProvider;
import school.sptech.provider.banco.BuscarPorNumeroProvider;
import school.sptech.provider.banco.CriarContaPlusProvider;
import school.sptech.provider.banco.CriarContaProvider;
import school.sptech.provider.banco.RemoverPorNumeroProvider;
import school.sptech.provider.contacorrente.ContaCorrenteConstrutorProvider;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Banco")
public class BancoTest {

    @Nested
    @DisplayName("1. Atributos")
    class AtributosTest {

        @Test
        @DisplayName("1. Validar Atributos")
        void test1() {
            Class<Banco> clazz = Banco.class;

            assertAll(
                    () -> assertDoesNotThrow(() -> clazz.getDeclaredField("nome")),
                    () -> assertDoesNotThrow(() -> clazz.getDeclaredField("contas"))
            );
        }
    }

    @Nested
    @DisplayName("2. Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("1. Atributos Privados")
        void test1() {
            Class<Banco> clazz = Banco.class;
            Field[] campos = clazz.getDeclaredFields();

            Stream<Executable> validacoes = Arrays.stream(campos)
                    .map(campo -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
                            String.format("%s deve ser privado", campo.getName())));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("2. Métodos Públicos")
        void test2() {
            Class<Banco> clazz = Banco.class;

            List<Method> metodos = new ArrayList<>();

            try {
                metodos.add(clazz.getDeclaredMethod("criarConta", String.class, String.class));
            } catch (ReflectiveOperationException ignored) {}

            try {
                metodos.add(clazz.getDeclaredMethod("criarContaPlus", String.class, String.class));
            } catch (ReflectiveOperationException ignored) {}

            try {
                metodos.add(clazz.getDeclaredMethod("buscarPorNumero", String.class));
            } catch (ReflectiveOperationException ignored) {}

            try {
                metodos.add(clazz.getDeclaredMethod("removerPorNumero", String.class));
            } catch (ReflectiveOperationException ignored) {}

            try {
                metodos.add(clazz.getDeclaredMethod("buscarContasPlus"));
            } catch (ReflectiveOperationException ignored) {}

            try {
                metodos.add(clazz.getDeclaredMethod("buscarContasComPontosMaior", Integer.class));
            } catch (ReflectiveOperationException ignored) {}

            Stream<Executable> validacoes = metodos.stream()
                    .map(metodo -> () -> assertTrue(Modifier.isPublic(metodo.getModifiers())));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("3. Atributos devem possuir APENAS getters")
        void test3() {
            Class<Banco> clazz = Banco.class;
            Field[] campos = clazz.getDeclaredFields();

            Stream<Executable> validacoesGetter = Arrays.stream(campos)
                    .map(campo -> () -> {
                        String getName = String.format("get%s", StringUtils.capitalize(campo.getName()));
                        assertDoesNotThrow(() -> {
                            Method getter = clazz.getDeclaredMethod(getName);
                            int getModifier = getter.getModifiers();
                            assertTrue(Modifier.isPublic(getModifier));
                        });
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
        @ArgumentsSource(ContaCorrenteConstrutorProvider.class)
        @DisplayName("4. Construtor deve inicializar atributos TODOS atributos corretamente")
        void test4(String nome) throws ReflectiveOperationException {
            Class<Banco> clazz = Banco.class;

            assertDoesNotThrow(() -> {
                clazz.getDeclaredConstructor(String.class);
            }, "Construtor com parâmetros (String nome) deve existir");

            Object obj = clazz.getDeclaredConstructor(String.class).newInstance(nome);

            assertAll(
                () -> assertEquals(nome, BancoFactory.campos().get("nome").get(obj), "Nome deve ser inicializado corretamente"),
                () -> assertIterableEquals(new ArrayList<>(), (List<?>) BancoFactory.campos().get("contas").get(obj), "Contas deve ser inicializado corretamente")
            );
        }
    }

    @Nested
    @DisplayName("3. Método - criarConta")
    class CriarContaTest {

        @ParameterizedTest
        @ArgumentsSource(CriarContaProvider.class)
        void test(List<?> contas, String numero, String titular, List<?> expected) throws ReflectiveOperationException {
            Class<Banco> clazz = Banco.class;
            Method method = clazz.getDeclaredMethod("criarConta", String.class, String.class);

            // Case
            Object obj = BancoFactory.build("Teste", new ArrayList<>(contas));

            // When
            method.invoke(obj, numero, titular);

            // Then
            assertAll(
                    () -> assertIterableEquals(expected, (List<?>) BancoFactory.campos().get("contas").get(obj))
            );
        }
    }

    @Nested
    @DisplayName("4. Método - criarContaPlus")
    class CriarContaPlusTest {

        @ParameterizedTest
        @ArgumentsSource(CriarContaPlusProvider.class)
        void test(List<?> contas, String numero, String titular, List<?> expected) throws ReflectiveOperationException {
            Class<Banco> clazz = Banco.class;
            Method method = clazz.getDeclaredMethod("criarContaPlus", String.class, String.class);

            // Case
            Object obj = BancoFactory.build("Teste", new ArrayList<>(contas));

            // When
            method.invoke(obj, numero, titular);

            // Then
            assertAll(
                    () -> assertIterableEquals(expected, (List<?>) BancoFactory.campos().get("contas").get(obj))
            );
        }
    }

    @Nested
    @DisplayName("5. Método - buscarPorNumero")
    class BuscarPorNumeroTest {

        @ParameterizedTest
        @ArgumentsSource(BuscarPorNumeroProvider.class)
        void test(List<?> contas, String numero, Object expected) throws ReflectiveOperationException {
            Class<Banco> clazz = Banco.class;
            Method method = clazz.getDeclaredMethod("buscarPorNumero", String.class);

            // Case
            Object obj = BancoFactory.build("Teste", new ArrayList<>(contas));

            // When
            Object response = method.invoke(obj, numero);

            // Then
            assertAll(
                    () -> assertEquals(expected, response)
            );
        }
    }

    @Nested
    @DisplayName("6. Método - removerPorNumero")
    class RemoverPorNumeroTest {

        @ParameterizedTest
        @ArgumentsSource(RemoverPorNumeroProvider.class)
        void test(List<?> contas, String numero, List<?> expected) throws ReflectiveOperationException {
            Class<Banco> clazz = Banco.class;
            Method method = clazz.getDeclaredMethod("removerPorNumero", String.class);

            // Case
            Object obj = BancoFactory.build("Teste", new ArrayList<>(contas));

            // When
            method.invoke(obj, numero);

            // Then
            assertAll(
                    () -> assertIterableEquals(expected, (List<?>) BancoFactory.campos().get("contas").get(obj))
            );
        }
    }

    @Nested
    @DisplayName("7. Método - buscarContasPlus")
    class BuscarContasPlusTest {

        @ParameterizedTest
        @ArgumentsSource(BuscarContasPlusProvider.class)
        void test(List<?> contas, List<?> expected) throws ReflectiveOperationException {
            Class<Banco> clazz = Banco.class;
            Method method = clazz.getDeclaredMethod("buscarContasPlus");

            // Case
            Object obj = BancoFactory.build("Teste", new ArrayList<>(contas));

            // When
            Object response = method.invoke(obj);

            // Then
            assertAll(
                    () -> assertIterableEquals(expected, (List<?>) response)
            );
        }
    }

    @Nested
    @DisplayName("8. Método - buscarContasComPontosMaior")
    class BuscarContasComPontosMaiorTest {

        @ParameterizedTest
        @ArgumentsSource(BuscarContasComPontosMaiorProvider.class)
        void test(List<?> contas, Integer pontos, List<?> expected) throws ReflectiveOperationException {
            Class<Banco> clazz = Banco.class;
            Method method = clazz.getDeclaredMethod("buscarContasComPontosMaior", Integer.class);

            // Case
            Object obj = BancoFactory.build("Teste", new ArrayList<>(contas));

            // When
            Object response = method.invoke(obj, pontos);

            // Then
            assertAll(
                    () -> assertIterableEquals(expected, (List<?>) response)
            );
        }
    }
}
