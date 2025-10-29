package school.sptech;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.factory.ImagemFactory;
import school.sptech.provider.imagem.AdicionarProvider;
import school.sptech.provider.imagem.BuscarPorAreaMaiorQue20Provider;
import school.sptech.provider.imagem.BuscarQuadradosProvider;
import school.sptech.provider.imagem.CalcularSomaDasAreasProvider;

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

@DisplayName("Imagem")
public class ImagemTest {

  static Map<String, Field> campos() throws ReflectiveOperationException {
    Class<Imagem> clazz = Imagem.class;

    Map<String, Field> mapCampos = new HashMap<>();
    String[] nomeCampos = { "figuras" };

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
      Class<Imagem> clazz = Imagem.class;

      assertAll(
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("figuras"), "Deve possuir o atributo figuras")
      );
    }
  }

  @Nested
  @DisplayName("2. Métodos")
  class MetodosTest {

    @Test
    @DisplayName("1. Validar Métodos")
    void test1() {
      Class<Imagem> clazz = Imagem.class;

      assertAll(
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("adicionar", Figura.class), "Deve possuir o método adicionar"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("calcularSomaDasAreas"), "Deve possuir o método calcularSomaDasAreas"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("buscarPorAreaMaiorQue20"), "Deve possuir o método buscarPorAreaMaiorQue20"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("buscarQuadrados"), "Deve possuir o método buscarQuadrados")
      );
    }
  }

  @Nested
  @DisplayName("3. Encapsulamento")
  class EncapsulamentoTest {

    @Test
    @DisplayName("1. Atributos Privados")
    void test1() {
      Class<Imagem> clazz = Imagem.class;
      Field[] campos = clazz.getDeclaredFields();

      Stream<Executable> validacoes = Arrays.stream(campos)
          .map((campo) -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
              String.format("%s deve ser privado", campo.getName())));

      assertAll(validacoes);
    }

    @Test
    @DisplayName("2. Métodos Públicos")
    void test2() {
      Class<Imagem> clazz = Imagem.class;

      List<Method> metodos = new ArrayList<>();

      try {
        metodos.add(clazz.getDeclaredMethod("adicionar", Figura.class));
      } catch (ReflectiveOperationException ignored) {}

      try {
        metodos.add(clazz.getDeclaredMethod("calcularSomaDasAreas"));
      } catch (ReflectiveOperationException ignored) {}

      try {
        metodos.add(clazz.getDeclaredMethod("buscarPorAreaMaiorQue20"));
      } catch (ReflectiveOperationException ignored) {}

      try {
        metodos.add(clazz.getDeclaredMethod("buscarQuadrados"));
      } catch (ReflectiveOperationException ignored) {}

      Stream<Executable> validacoes = metodos.stream()
          .map((metodo) -> () -> {
            assertTrue(Modifier.isPublic(metodo.getModifiers()), String.format("%s deve ser público", metodo.getName()));
          });

      assertAll(validacoes);
    }
  }

  @Nested
  @DisplayName("4. Método - adicionar")
  class MetodoAdicionarTest {

    @ParameterizedTest
    @ArgumentsSource(AdicionarProvider.class)
    @DisplayName("Teste 1")
    void teste1(List<?> figuras, Object figura, List<?> expected) throws Exception {
      Class<Imagem> clazz = Imagem.class;
      Method metodo = clazz.getDeclaredMethod("adicionar", Figura.class);

      // Case
      Object obj = ImagemFactory.build(new ArrayList<>(figuras));

      // When
      metodo.invoke(obj, figura);

      // Then
      assertAll(
          () -> assertIterableEquals(expected, (List<?>) campos().get("figuras").get(obj))
      );
    }
  }

  @Nested
  @DisplayName("5. Método - calcularSomaDasAreas")
  class MetodoCalcularSomaDasAreasTest {

    @ParameterizedTest
    @ArgumentsSource(CalcularSomaDasAreasProvider.class)
    @DisplayName("Teste 1")
    void teste1(List<?> figuras, Double expected) throws Exception {
      Class<Imagem> clazz = Imagem.class;
      Method metodo = clazz.getDeclaredMethod("calcularSomaDasAreas");

      // Case
      Object obj = ImagemFactory.build(new ArrayList<>(figuras));

      // When
      Double result = (Double) metodo.invoke(obj);

      // Then
      assertAll(
          () -> assertEquals(expected, result, 0.01)
      );
    }
  }

  @Nested
  @DisplayName("6. Método - buscarPorAreaMaiorQue20")
  class MetodoBuscarPorAreaMaiorQue20Test {

    @ParameterizedTest
    @ArgumentsSource(BuscarPorAreaMaiorQue20Provider.class)
    @DisplayName("Teste 1")
    void teste1(List<?> figuras, List<?> expected) throws Exception {
      Class<Imagem> clazz = Imagem.class;
      Method metodo = clazz.getDeclaredMethod("buscarPorAreaMaiorQue20");

      // Case
      Object obj = ImagemFactory.build(new ArrayList<>(figuras));

      // When
      List<?> result = (List<?>) metodo.invoke(obj);

      // Then
      assertAll(
          () -> assertIterableEquals(expected, result)
      );
    }
  }

  @Nested
  @DisplayName("7. Método - buscarQuadrados")
  class MetodoBuscarQuadradosTest {

    @ParameterizedTest
    @ArgumentsSource(BuscarQuadradosProvider.class)
    @DisplayName("Teste 1")
    void teste1(List<?> figuras, List<?> expected) throws Exception {
      Class<Imagem> clazz = Imagem.class;
      Method metodo = clazz.getDeclaredMethod("buscarQuadrados");

      // Case
      Object obj = ImagemFactory.build(new ArrayList<>(figuras));

      // When
      List<?> result = (List<?>) metodo.invoke(obj);

      // Then
      assertAll(
          () -> assertIterableEquals(expected, result)
      );
    }
  }
}
