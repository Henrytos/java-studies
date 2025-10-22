package school.sptech;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.factory.ContaCorrenteFactory;
import school.sptech.provider.contacorrente.ContaCorrenteConstrutorProvider;
import school.sptech.provider.contacorrente.DepositarProvider;
import school.sptech.provider.contacorrente.SacarProvider;

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

@DisplayName("ContaCorrente")
class ContaCorrenteTest {

  @Nested
  @DisplayName("1. Atributos")
  class AtributosTest {

    @Test
    @DisplayName("1. Validar Atributos")
    void test1() {
      Class<ContaCorrente> clazz = ContaCorrente.class;

      assertAll(
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("numero")),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("titular")),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("saldo"))
      );
    }

    @Test
    @DisplayName("2. Validar Atributos 'final'")
    void test2() {
      Class<ContaCorrente> clazz = ContaCorrente.class;

      Stream<Executable> validacoes = Stream.of("numero", "titular")
          .map(nomeCampo -> () -> {
            Field campo = clazz.getDeclaredField(nomeCampo);
            assertTrue(Modifier.isFinal(campo.getModifiers()),
                String.format("%s deve ser final", nomeCampo));
          });

      assertAll(validacoes);
    }
  }

  @Nested
  @DisplayName("2. Encapsulamento")
  class EncapsulamentoTest {

    @Test
    @DisplayName("1. Atributos Privados")
    void test1() {
      Class<ContaCorrente> clazz = ContaCorrente.class;
      Field[] campos = clazz.getDeclaredFields();

      Stream<Executable> validacoes = Arrays.stream(campos)
          .map(campo -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
              String.format("%s deve ser privado", campo.getName())));

      assertAll(validacoes);
    }

    @Test
    @DisplayName("2. Métodos Públicos")
    void test2() {
      Class<ContaCorrente> clazz = ContaCorrente.class;

      List<Method> metodos = new ArrayList<>();

      try {
        metodos.add(clazz.getDeclaredMethod("depositar", Double.class));
      } catch (ReflectiveOperationException ignored) {}

      try {
        metodos.add(clazz.getDeclaredMethod("sacar", Double.class));
      } catch (ReflectiveOperationException ignored) {}

      Stream<Executable> validacoes = metodos.stream()
          .map(metodo -> () -> {
            assertTrue(Modifier.isPublic(metodo.getModifiers()));
          });

      assertAll(validacoes);
    }

    @Test
    @DisplayName("3. Atributos devem possuir APENAS getters")
    void test3() {
      Class<ContaCorrente> clazz = ContaCorrente.class;
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
    void test4(String numero, String titular) throws ReflectiveOperationException {
      Class<ContaCorrente> clazz = ContaCorrente.class;

      assertDoesNotThrow(() -> {
        clazz.getDeclaredConstructor(String.class, String.class);
      }, "Construtor com parâmetros (String numero, String titular) deve existir");

      Object obj = clazz.getDeclaredConstructor(String.class, String.class).newInstance(numero, titular);

      assertAll(
          () -> assertEquals(numero, ContaCorrenteFactory.campos().get("numero").get(obj), "Número deve ser inicializado corretamente"),
          () -> assertEquals(titular, ContaCorrenteFactory.campos().get("titular").get(obj), "Titular deve ser inicializado corretamente"),
          () -> assertEquals(0.0, ContaCorrenteFactory.campos().get("saldo").get(obj), "Saldo deve ser inicializado corretamente")
      );
    }
  }

  @Nested
  @DisplayName("3. Método - Depositar")
  class MetodoDepositarTest {

    @ParameterizedTest
    @ArgumentsSource(DepositarProvider.class)
    void test(Object obj, Double valor, Double expected) throws ReflectiveOperationException {
      Class<ContaCorrente> clazz = ContaCorrente.class;
      Method method = clazz.getDeclaredMethod("depositar", Double.class);

      // When
      method.invoke(obj, valor);

      // Then
      assertAll(() -> assertEquals(expected, ContaCorrenteFactory.campos().get("saldo").get(obj), "Saldo deve corresponder ao valor esperado"));
    }
  }

  @Nested
  @DisplayName("4. Método - Sacar")
  class MetodoSacarTest {

    @ParameterizedTest
    @ArgumentsSource(SacarProvider.class)
    void test(Object obj, Double valor, Double expected) throws ReflectiveOperationException {
      Class<ContaCorrente> clazz = ContaCorrente.class;
      Method method = clazz.getDeclaredMethod("sacar", Double.class);

      // When
      method.invoke(obj, valor);

      // Then
      assertAll(() -> assertEquals(expected, ContaCorrenteFactory.campos().get("saldo").get(obj), "Saldo deve corresponder ao valor esperado"));
    }
  }
}
