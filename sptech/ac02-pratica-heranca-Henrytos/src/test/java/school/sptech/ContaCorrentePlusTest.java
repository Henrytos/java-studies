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
import school.sptech.factory.ContaCorrentePlusFactory;
import school.sptech.provider.contacorrente.ContaCorrenteConstrutorProvider;
import school.sptech.provider.contacorrenteplus.DepositarProvider;
import school.sptech.provider.contacorrenteplus.TrocarPontosProvider;

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

@DisplayName("ContaCorrentePlus")
public class ContaCorrentePlusTest {

  @Nested
  @DisplayName("1. Atributos")
  class AtributosTest {

    @Test
    @DisplayName("1. Validar Atributos")
    void test1() {
      Class<ContaCorrentePlus> clazz = ContaCorrentePlus.class;

      assertAll(
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("pontos"))
      );
    }
  }

  @Nested
  @DisplayName("2. Encapsulamento")
  class EncapsulamentoTest {

    @Test
    @DisplayName("1. Atributos Privados")
    void test1() {
      Class<ContaCorrentePlus> clazz = ContaCorrentePlus.class;
      Field[] campos = clazz.getDeclaredFields();

      Stream<Executable> validacoes = Arrays.stream(campos)
          .map(campo -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
              String.format("%s deve ser privado", campo.getName())));

      assertAll(validacoes);
    }

    @Test
    @DisplayName("2. Métodos Públicos")
    void test2() {
      Class<ContaCorrentePlus> clazz = ContaCorrentePlus.class;

      List<Method> metodos = new ArrayList<>();

      try {
        metodos.add(clazz.getDeclaredMethod("depositar", Double.class));
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
      Class<ContaCorrentePlus> clazz = ContaCorrentePlus.class;
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
      Class<ContaCorrentePlus> clazz = ContaCorrentePlus.class;

      assertDoesNotThrow(() -> {
        clazz.getDeclaredConstructor(String.class, String.class);
      }, "Construtor com parâmetros (String numero, String titular) deve existir");

      Object obj = clazz.getDeclaredConstructor(String.class, String.class).newInstance(numero, titular);

      assertAll(
          () -> assertEquals(numero, ContaCorrentePlusFactory.campos().get("numero").get(obj), "Número deve ser inicializado corretamente"),
          () -> assertEquals(titular, ContaCorrentePlusFactory.campos().get("titular").get(obj), "Titular deve ser inicializado corretamente"),
          () -> assertEquals(0.0, ContaCorrentePlusFactory.campos().get("saldo").get(obj), "Saldo deve ser inicializado corretamente"),
          () -> assertEquals(0, ContaCorrentePlusFactory.campos().get("pontos").get(obj), "Pontos deve ser inicializado corretamente")
      );
    }
  }

  @Nested
  @DisplayName("3. Método - Depositar")
  class MetodoDepositarTest {

    @ParameterizedTest
    @ArgumentsSource(DepositarProvider.class)
    void test(Object obj, Double valor, Double expected, Integer pontosExpected) throws ReflectiveOperationException {
      Class<ContaCorrentePlus> clazz = ContaCorrentePlus.class;
      Method method = clazz.getDeclaredMethod("depositar", Double.class);

      // When
      method.invoke(obj, valor);

      // Then
      assertAll(
              () -> assertEquals(expected, ContaCorrentePlusFactory.campos().get("saldo").get(obj), "Saldo deve corresponder ao valor esperado"),
              () -> assertEquals(pontosExpected, ContaCorrentePlusFactory.campos().get("pontos").get(obj), "Pontos deve corresponder ao valor esperado")
      );
    }
  }

  @Nested
  @DisplayName("4. Método - Trocar Pontos")
  class MetodoTrocarPontosTest {

    @ParameterizedTest
    @ArgumentsSource(TrocarPontosProvider.class)
    void test(Object obj, Double expected, Integer pointsExpected) throws ReflectiveOperationException {
      Class<ContaCorrentePlus> clazz = ContaCorrentePlus.class;
      Method trocarPontosMethod = clazz.getDeclaredMethod("trocarPontos");

      // When
      trocarPontosMethod.invoke(obj);

      // Then
      assertAll(
          () -> assertEquals(expected, ContaCorrentePlusFactory.campos().get("saldo").get(obj), "Saldo deve corresponder ao valor esperado após trocar pontos"),
          () -> assertEquals(pointsExpected, ContaCorrentePlusFactory.campos().get("pontos").get(obj), "Pontos deve corresponder ao valor esperado após trocar pontos")
      );
    }
  }
}
