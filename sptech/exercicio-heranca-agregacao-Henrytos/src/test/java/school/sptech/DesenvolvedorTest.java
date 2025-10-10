package school.sptech;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.provider.desenvolvedor.CalcularSalarioProvider;

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

@DisplayName("Desenvolvedor")
class DesenvolvedorTest {

  Map<String, Field> campos() throws ReflectiveOperationException {
    Class<Desenvolvedor> clazz = Desenvolvedor.class;

    Map<String, Field> mapCampos = new HashMap<>();
    String[] nomeCampos = { "nome", "qtdHoras", "valorHora" };

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
      Class<Desenvolvedor> clazz = Desenvolvedor.class;

      assertAll(
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("nome"), "Deve possuir o atributo nome"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("qtdHoras"), "Deve possuir o atributo qtdHoras"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("valorHora"), "Deve possuir o atributo valorHora")
      );
    }
  }

  @Nested
  @DisplayName("2. Métodos")
  class MetodosTest {

    @Test
    @DisplayName("1. Validar Métodos")
    void test1() {
      Class<Desenvolvedor> clazz = Desenvolvedor.class;

      assertAll(
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("calcularSalario"), "Deve possuir o método calcularSalario")
      );
    }
  }

  @Nested
  @DisplayName("3. Encapsulamento")
  class EncapsulamentoTest {

    @Test
    @DisplayName("1. Atributos Protegidos")
    void test1() {
      Class<Desenvolvedor> clazz = Desenvolvedor.class;
      Field[] campos = clazz.getDeclaredFields();

      Stream<Executable> validacoes = Arrays.stream(campos)
          .map((campo) -> () -> assertTrue(Modifier.isProtected(campo.getModifiers()),
              String.format("%s deve ser protegido", campo.getName())));

      assertAll(validacoes);
    }

    @Test
    @DisplayName("2. Métodos Públicos")
    void test2() {
      Class<Desenvolvedor> clazz = Desenvolvedor.class;

      List<Method> metodos = new ArrayList<>();

      try {
        metodos.add(clazz.getDeclaredMethod("calcularSalario"));
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
      Class<Desenvolvedor> clazz = Desenvolvedor.class;
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
  @DisplayName("4. Método - calcularSalario")
  class MetodoCalcularSalarioTest {

    @ParameterizedTest
    @ArgumentsSource(CalcularSalarioProvider.class)
    @DisplayName("Teste 1")
    void test1(String nome, Integer qtdHoras, Double valorHora, Double expected) throws ReflectiveOperationException {
      Class<Desenvolvedor> clazz = Desenvolvedor.class;
      Method metodo = clazz.getDeclaredMethod("calcularSalario");

      Object obj = new Desenvolvedor();

      // Case
      campos().get("nome").set(obj, nome);
      campos().get("qtdHoras").set(obj, qtdHoras);
      campos().get("valorHora").set(obj, valorHora);

      // When
      Object resposta = metodo.invoke(obj);

      // Then
      assertAll(() -> assertEquals(expected, (Double) resposta, 0.01, "calcularSalario deve calcular o salário corretamente"));
    }
  }
}