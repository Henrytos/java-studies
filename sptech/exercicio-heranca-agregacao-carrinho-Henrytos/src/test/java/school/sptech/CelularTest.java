package school.sptech;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.provider.celular.CalcularPrecoFinalProvider;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Celular")
public class CelularTest {

  Map<String, Field> campos() throws ReflectiveOperationException {
    Class<Celular> clazz = Celular.class;
    Class<Produto> superClazz = Produto.class;

    Map<String, Field> mapCampos = new HashMap<>();
    String[] nomeCamposSuper = { "nome", "preco", "peso" };
    String[] nomeCampos = { "garantiaMeses", "marca" };

    for (String campoNome : nomeCamposSuper) {
      Field campo = superClazz.getDeclaredField(campoNome);
      campo.trySetAccessible();

      mapCampos.put(campoNome, campo);
    }

    for (String campoNome : nomeCampos) {
      Field campo = clazz.getDeclaredField(campoNome);
      campo.trySetAccessible();

      mapCampos.put(campoNome, campo);
    }

    return mapCampos;
  }

  @Nested
  @DisplayName("1. Atributos")
  class AtributosTest {

    @Test
    @DisplayName("1. Validar Atributos")
    void test1() {
      Class<Celular> clazz = Celular.class;

      assertAll(
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("garantiaMeses"), "Deve possuir o atributo garantiaMeses"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("marca"), "Deve possuir o atributo marca")
      );
    }
  }

  @Nested
  @DisplayName("2. Métodos")
  class MetodosTest {

    @Test
    @DisplayName("1. Validar Métodos")
    void test1() {
      Class<Celular> clazz = Celular.class;

      assertAll(
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("calcularPrecoFinal"), "Deve sobrescrever o método calcularPrecoFinal")
      );
    }
  }

  @Nested
  @DisplayName("3. Encapsulamento")
  class EncapsulamentoTest {

    @Test
    @DisplayName("1. Atributos Privados")
    void test1() {
      Class<Celular> clazz = Celular.class;
      Field[] campos = clazz.getDeclaredFields();

      Stream<Executable> validacoes = Arrays.stream(campos)
          .map((campo) -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
              String.format("%s deve ser privado", campo.getName())));

      assertAll(validacoes);
    }

    @Test
    @DisplayName("2. Métodos Públicos")
    void test2() {
      Class<Celular> clazz = Celular.class;

      List<Method> metodos = new ArrayList<>();

      try {
        metodos.add(clazz.getDeclaredMethod("calcularPrecoFinal"));
      } catch (ReflectiveOperationException ignored) {}

      Stream<Executable> validacoes = metodos.stream()
          .map((metodo) -> () -> {
            assertTrue(Modifier.isPublic(metodo.getModifiers()), String.format("%s deve ser público", metodo.getName()));
          });

      assertAll(validacoes);
    }

    @Test
    @DisplayName("3. Atributos devem possuir getters e setters")
    void test3() {
      Class<Celular> clazz = Celular.class;
      Field[] campos = clazz.getDeclaredFields();

      Stream<Executable> validacoesGetter = Arrays.stream(campos)
          .map((campo) -> () -> {
            String getName = String.format("get%s", StringUtils.capitalize(campo.getName()));
            assertDoesNotThrow(() -> {
              Method getter = clazz.getDeclaredMethod(getName);
              int getModifier = getter.getModifiers();
              assertTrue(Modifier.isPublic(getModifier), String.format("Getter %s deve ser público", getName));
            }, String.format("Deve possuir o getter %s", getName));
          });

      Stream<Executable> validacoesSetter = Arrays.stream(campos)
          .map((campo) -> () -> {
            String setName = String.format("set%s", StringUtils.capitalize(campo.getName()));
            assertDoesNotThrow(() -> {
              Method setter = clazz.getDeclaredMethod(setName, campo.getType());
              int setModifier = setter.getModifiers();
              assertTrue(Modifier.isPublic(setModifier), String.format("Setter %s deve ser público", setName));
            }, String.format("Deve possuir o setter %s", setName));
          });

      assertAll(Stream.concat(validacoesGetter, validacoesSetter));
    }
  }

  @Nested
  @DisplayName("4. Método - calcularPrecoFinal")
  class MetodoCalcularPrecoFinalTest {

    @ParameterizedTest
    @ArgumentsSource(CalcularPrecoFinalProvider.class)
    @DisplayName("Teste 1")
    void test1(String nome, Double preco, Double peso,
               Integer garantiaMeses, String marca, Double expected) throws ReflectiveOperationException {
      Class<Celular> clazz = Celular.class;
      Method metodo = clazz.getDeclaredMethod("calcularPrecoFinal");

      Object obj = new Celular();

      // Case
      campos().get("nome").set(obj, nome);
      campos().get("preco").set(obj, preco);
      campos().get("peso").set(obj, peso);
      campos().get("garantiaMeses").set(obj, garantiaMeses);
      campos().get("marca").set(obj, marca);

      // When
      Object resposta = metodo.invoke(obj);

      // Then
      assertAll(() -> assertEquals(expected, (Double) resposta, 0.01, "calcularPrecoFinal deve calcular o preço corretamente"));
    }
  }
}
