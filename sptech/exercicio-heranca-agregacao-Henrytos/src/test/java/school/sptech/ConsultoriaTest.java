package school.sptech;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.especialistas.DesenvolvedorWeb;
import school.sptech.factory.ConsultoriaFactory;
import school.sptech.provider.consultoria.BuscarMenorSalarioProvider;
import school.sptech.provider.consultoria.BuscarPorSalarioMaiorQueProvider;
import school.sptech.provider.consultoria.BuscarPorTecnologiaProvider;
import school.sptech.provider.consultoria.ContratarFullstackProvider;
import school.sptech.provider.consultoria.ContratarProvider;
import school.sptech.provider.consultoria.GetTotalDesenvolvedoresMobileProvider;
import school.sptech.provider.consultoria.GetTotalSalariosPorTecnologiaProvider;
import school.sptech.provider.consultoria.GetTotalSalariosProvider;

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
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Consultoria")
public class ConsultoriaTest {

  Map<String, Field> campos() throws ReflectiveOperationException {
    Class<Consultoria> clazz = Consultoria.class;

    Map<String, Field> mapCampos = new HashMap<>();
    String[] nomeCampos = { "nome", "vagas", "desenvolvedores" };

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
      Class<Consultoria> clazz = Consultoria.class;

      assertAll(
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("nome"), "Deve possuir o atributo nome"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("vagas"), "Deve possuir o atributo vagas"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("desenvolvedores"), "Deve possuir o atributo desenvolvedores")
      );
    }
  }

  @Nested
  @DisplayName("2. Métodos")
  class MetodosTest {

    @Test
    @DisplayName("1. Validar Métodos")
    void test1() {
      Class<Consultoria> clazz = Consultoria.class;

      assertAll(
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("contratar", Desenvolvedor.class), "Deve possuir o método contratar"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("contratarFullstack", DesenvolvedorWeb.class), "Deve possuir o método contratarFullstack"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("getTotalSalarios"), "Deve possuir o método getTotalSalarios"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("qtdDesenvolvedoresMobile"), "Deve possuir o método qtdDesenvolvedoresMobile"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("buscarPorSalarioMaiorIgualQue", Double.class), "Deve possuir o método buscarSalarioMaiorQue"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("buscarMenorSalario"), "Deve possuir o método buscarMenorSalario"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("buscarPorTecnologia", String.class), "Deve possuir o método buscarPorTecnologia"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("getTotalSalariosPorTecnologia", String.class), "Deve possuir o método getTotalSalarioPorTecnologia")
      );
    }
  }

  @Nested
  @DisplayName("3. Encapsulamento")
  class EncapsulamentoTest {

    @Test
    @DisplayName("1. Atributos Privados")
    void test1() {
      Class<Consultoria> clazz = Consultoria.class;
      Field[] campos = clazz.getDeclaredFields();

      Stream<Executable> validacoes = Arrays.stream(campos)
          .map((campo) -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
              String.format("%s deve ser privado", campo.getName())));

      assertAll(validacoes);
    }

    @Test
    @DisplayName("2. Métodos Públicos")
    void test2() {
      Class<Consultoria> clazz = Consultoria.class;

      List<Method> metodos = new ArrayList<>();

      try {
        metodos.add(clazz.getDeclaredMethod("contratar", Desenvolvedor.class));
      } catch (ReflectiveOperationException ignored) {}

      try {
        metodos.add(clazz.getDeclaredMethod("contratarFullstack", DesenvolvedorWeb.class));
      } catch (ReflectiveOperationException ignored) {}

      try {
        metodos.add(clazz.getDeclaredMethod("getTotalSalarios"));
      } catch (ReflectiveOperationException ignored) {}

      try {
        metodos.add(clazz.getDeclaredMethod("qtdDesenvolvedoresMobile"));
      } catch (ReflectiveOperationException ignored) {}

      try {
        metodos.add(clazz.getDeclaredMethod("buscarPorSalarioMaiorIgualQue", Double.class));
      } catch (ReflectiveOperationException ignored) {}

      try {
        metodos.add(clazz.getDeclaredMethod("buscarMenorSalario"));
      } catch (ReflectiveOperationException ignored) {}

      try {
        metodos.add(clazz.getDeclaredMethod("buscarPorTecnologia", String.class));
      } catch (ReflectiveOperationException ignored) {}

      try {
        metodos.add(clazz.getDeclaredMethod("getTotalSalariosPorTecnologia", String.class));
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
      Class<Consultoria> clazz = Consultoria.class;
      Field[] campos = Arrays.stream(clazz.getDeclaredFields())
          .filter(campo -> !campo.getName().equals("desenvolvedores"))
          .toArray(Field[]::new);

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
  @DisplayName("4. Método - contratar")
  class MetodoContratarTest {

    @ParameterizedTest
    @ArgumentsSource(ContratarProvider.class)
    @DisplayName("Teste 1")
    void test1(String nome, Integer vagas, List<?> desenvolvedores, Object desenvolvedor, List<?> expected) throws ReflectiveOperationException {
      Class<Consultoria> clazz = Consultoria.class;
      Method metodo = clazz.getDeclaredMethod("contratar", Desenvolvedor.class);

      // Case
      Object obj = ConsultoriaFactory.build(nome, vagas, new ArrayList<>(desenvolvedores));

      // When
      metodo.invoke(obj, desenvolvedor);

      // Then
      assertAll(
          () -> assertIterableEquals(new ArrayList<>(expected), (List<?>) campos().get("desenvolvedores").get(obj))
      );
    }
  }

  @Nested
  @DisplayName("5. Método - contratarFullstack")
  class MetodoContratarFullstackTest {

    @ParameterizedTest
    @ArgumentsSource(ContratarFullstackProvider.class)
    @DisplayName("Teste 1")
    void test1(String nome, Integer vagas, List<?> desenvolvedores, Object desenvolvedor, List<?> expected) throws ReflectiveOperationException {
      Class<Consultoria> clazz = Consultoria.class;
      Method metodo = clazz.getDeclaredMethod("contratarFullstack", DesenvolvedorWeb.class);

      // Case
      Object obj = ConsultoriaFactory.build(nome, vagas, new ArrayList<>(desenvolvedores));

      // When
      metodo.invoke(obj, desenvolvedor);

      // Then
      assertAll(
          () -> assertIterableEquals(new ArrayList<>(expected), (List<?>) campos().get("desenvolvedores").get(obj))
      );
    }
  }

  @Nested
  @DisplayName("6. Método - getTotalSalarios")
  class MetodoGetTotalSalariosTest {

    @ParameterizedTest
    @ArgumentsSource(GetTotalSalariosProvider.class)
    @DisplayName("Teste 1")
    void test1(String nome, Integer vagas, List<?> desenvolvedores, Double expected) throws ReflectiveOperationException {
      Class<Consultoria> clazz = Consultoria.class;
      Method metodo = clazz.getDeclaredMethod("getTotalSalarios");

      // Case
      Object obj = ConsultoriaFactory.build(nome, vagas, new ArrayList<>(desenvolvedores));

      // When
      Object resposta = metodo.invoke(obj);

      // Then
      assertAll(
          () -> assertEquals(expected, (Double) resposta, 0.01, "Deve calcular o total de salários corretamente")
      );
    }
  }

  @Nested
  @DisplayName("7. Método - qtdDesenvolvedoresMobile")
  class MetodoGetDesenvolvedoresMobileTest {

    @ParameterizedTest
    @ArgumentsSource(GetTotalDesenvolvedoresMobileProvider.class)
    @DisplayName("Teste 1")
    void test1(String nome, Integer vagas, List<?> desenvolvedores, Integer expected) throws ReflectiveOperationException {
      Class<Consultoria> clazz = Consultoria.class;
      Method metodo = clazz.getDeclaredMethod("qtdDesenvolvedoresMobile");

      // Case
      Object obj = ConsultoriaFactory.build(nome, vagas, new ArrayList<>(desenvolvedores));

      // When
      Object resposta = metodo.invoke(obj);

      // Then
      assertAll(
          () -> assertEquals(expected, resposta, "Deve retornar o total de desenvolvedores mobile corretamente")
      );
    }
  }

  @Nested
  @DisplayName("8. Método - buscarPorSalarioMaiorIgualQue")
  class MetodoBuscarSalarioMaiorQueTest {

    @ParameterizedTest
    @ArgumentsSource(BuscarPorSalarioMaiorQueProvider.class)
    @DisplayName("Teste 1")
    void test1(String nome, Integer vagas, List<?> desenvolvedores, Double salario, List<?> expected) throws ReflectiveOperationException {
      Class<Consultoria> clazz = Consultoria.class;
      Method metodo = clazz.getDeclaredMethod("buscarPorSalarioMaiorIgualQue", Double.class);

      // Case
      Object obj = ConsultoriaFactory.build(nome, vagas, new ArrayList<>(desenvolvedores));

      // When
      List<?> resposta = (List<?>) metodo.invoke(obj, salario);

      // Then
      assertAll(
          () -> assertIterableEquals(expected, resposta, "Deve retornar os desenvolvedores com salário maior")
      );
    }
  }

  @Nested
  @DisplayName("9. Método - buscarMenorSalario")
  class MetodoBuscarMenorSalarioTest {

    @ParameterizedTest
    @ArgumentsSource(BuscarMenorSalarioProvider.class)
    @DisplayName("Teste 1")
    void test1(String nome, Integer vagas, List<?> desenvolvedores, Object expected) throws ReflectiveOperationException {
      Class<Consultoria> clazz = Consultoria.class;
      Method metodo = clazz.getDeclaredMethod("buscarMenorSalario");

      // Case
      Object obj = ConsultoriaFactory.build(nome, vagas, new ArrayList<>(desenvolvedores));

      // When
      Object resposta = metodo.invoke(obj);

      // Then
      assertAll(
          () -> assertEquals(expected, resposta, "Deve retornar o desenvolvedor com menor salário")
      );
    }
  }

  @Nested
  @DisplayName("10. Método - buscarPorTecnologia")
  class MetodoBuscarPorTecnologiaTest {

    @ParameterizedTest
    @ArgumentsSource(BuscarPorTecnologiaProvider.class)
    @DisplayName("Teste 1")
    void test1(String nome, Integer vagas, List<?> desenvolvedores, String tecnologia, List<?> expected) throws ReflectiveOperationException {
      Class<Consultoria> clazz = Consultoria.class;
      Method metodo = clazz.getDeclaredMethod("buscarPorTecnologia", String.class);

      // Case
      Object obj = ConsultoriaFactory.build(nome, vagas, new ArrayList<>(desenvolvedores));

      // When
      List<?> resposta = (List<?>) metodo.invoke(obj, tecnologia);

      // Then
      assertAll(
          () -> assertIterableEquals(expected, resposta, "Deve retornar os desenvolvedores com a tecnologia especificada")
      );
    }
  }

  @Nested
  @DisplayName("11. Método - getTotalSalariosPorTecnologia")
  class MetodoGetTotalSalariosPorTecnologiaTest {

    @ParameterizedTest
    @ArgumentsSource(GetTotalSalariosPorTecnologiaProvider.class)
    @DisplayName("Teste 1")
    void test1(String nome, Integer vagas, List<?> desenvolvedores, String tecnologia, Double expected) throws ReflectiveOperationException {
      Class<Consultoria> clazz = Consultoria.class;
      Method metodo = clazz.getDeclaredMethod("getTotalSalariosPorTecnologia", String.class);

      // Case
      Object obj = ConsultoriaFactory.build(nome, vagas, new ArrayList<>(desenvolvedores));

      // When
      Object resposta = metodo.invoke(obj, tecnologia);

      // Then
      assertAll(
          () -> assertEquals(expected, (Double) resposta, 0.01, "Deve retornar o total de salário dos desenvolvedores com a tecnologia especificada")
      );
    }
  }
}
