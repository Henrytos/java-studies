package school.sptech;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.especialistas.DesenvolvedorWeb;
import school.sptech.provider.desenvolvedorweb.CalcularSalarioProvider;
import school.sptech.provider.desenvolvedorweb.IsFullstackProvider;

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

@DisplayName("DesenvolvedorWeb")
public class DesenvolvedorWebTest {

  Map<String, Field> campos() throws ReflectiveOperationException {
    Class<DesenvolvedorWeb> clazz = DesenvolvedorWeb.class;
    Class<Desenvolvedor> superClazz = Desenvolvedor.class;

    Map<String, Field> mapCampos = new HashMap<>();
    String[] nomeCamposSuper = { "nome", "qtdHoras", "valorHora" };
    String[] nomeCampos = { "backend", "frontend", "sgbd", "horasMentoria" };

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
      Class<DesenvolvedorWeb> clazz = DesenvolvedorWeb.class;

      assertAll(
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("backend"), "Deve possuir o atributo backend"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("frontend"), "Deve possuir o atributo frontend"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("sgbd"), "Deve possuir o atributo sgbd"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("horasMentoria"), "Deve possuir o atributo horasMentoria")
      );
    }
  }

  @Nested
  @DisplayName("2. Métodos")
  class MetodosTest {

    @Test
    @DisplayName("1. Validar Métodos")
    void test1() {
      Class<DesenvolvedorWeb> clazz = DesenvolvedorWeb.class;

      assertAll(
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("calcularSalario"), "Deve sobrescrever o método calcularSalario"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("isFullstack"), "Deve possuir o método isFullstack")
      );
    }
  }

  @Nested
  @DisplayName("3. Encapsulamento")
  class EncapsulamentoTest {

    @Test
    @DisplayName("1. Atributos Privados")
    void test1() {
      Class<DesenvolvedorWeb> clazz = DesenvolvedorWeb.class;
      Field[] campos = clazz.getDeclaredFields();

      Stream<Executable> validacoes = Arrays.stream(campos)
          .map((campo) -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
              String.format("%s deve ser privado", campo.getName())));

      assertAll(validacoes);
    }

    @Test
    @DisplayName("2. Métodos Públicos")
    void test2() {
      Class<DesenvolvedorWeb> clazz = DesenvolvedorWeb.class;

      List<Method> metodos = new ArrayList<>();

      try {
        metodos.add(clazz.getDeclaredMethod("calcularSalario"));
      } catch (ReflectiveOperationException ignored) {}

      try {
        metodos.add(clazz.getDeclaredMethod("isFullstack"));
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
      Class<DesenvolvedorWeb> clazz = DesenvolvedorWeb.class;
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
    void test1(String nome, Integer qtdHoras, Double valorHora,
               String frontend, String backend, String sgbd, Integer horasMentoria, Double expected) throws ReflectiveOperationException {
      Class<DesenvolvedorWeb> clazz = DesenvolvedorWeb.class;
      Method metodo = clazz.getDeclaredMethod("calcularSalario");

      Object obj = new DesenvolvedorWeb();

      // Case
      campos().get("nome").set(obj, nome);
      campos().get("qtdHoras").set(obj, qtdHoras);
      campos().get("valorHora").set(obj, valorHora);
      campos().get("frontend").set(obj, frontend);
      campos().get("backend").set(obj, backend);
      campos().get("sgbd").set(obj, sgbd);
      campos().get("horasMentoria").set(obj, horasMentoria);

      // When
      Object resposta = metodo.invoke(obj);

      // Then
      assertAll(() -> assertEquals(expected, (Double) resposta, 0.01, "calcularSalario deve calcular o salário corretamente"));
    }
  }

  @Nested
  @DisplayName("5. Método - isFullstack")
  class MetodoIsFullstackTest {

    @ParameterizedTest
    @ArgumentsSource(IsFullstackProvider.class)
    @DisplayName("Teste 1")
    void test1(String nome, Integer qtdHoras, Double valorHora,
               String frontend, String backend, String sgbd, Integer horasMentoria, Boolean expected) throws ReflectiveOperationException {
      Class<DesenvolvedorWeb> clazz = DesenvolvedorWeb.class;
      Method metodo = clazz.getDeclaredMethod("isFullstack");

      Object obj = new DesenvolvedorWeb();

      // Case
      campos().get("nome").set(obj, nome);
      campos().get("qtdHoras").set(obj, qtdHoras);
      campos().get("valorHora").set(obj, valorHora);
      campos().get("frontend").set(obj, frontend);
      campos().get("backend").set(obj, backend);
      campos().get("sgbd").set(obj, sgbd);
      campos().get("horasMentoria").set(obj, horasMentoria);

      // When
      Object resposta = metodo.invoke(obj);

      // Then
      assertAll(() -> assertEquals(expected, resposta, "isFullstack deve retornar corretamente"));
    }
  }
}
