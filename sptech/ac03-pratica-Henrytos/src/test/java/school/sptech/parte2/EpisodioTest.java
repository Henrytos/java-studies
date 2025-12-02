package school.sptech.parte2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.Episodio;
import school.sptech.provider.episodio.AvaliarProvider;
import school.sptech.provider.episodio.CalcularMediaProvider;
import school.sptech.provider.episodio.ConstructorProvider;
import school.sptech.util.ObjectFieldMapping;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Episodio - Parte 2")
public class EpisodioTest {

  @Nested
  @DisplayName("1. Atributos")
  class AtributosTest {

    @Test
    @DisplayName("1. Validar Atributos")
    void test1() {
      Class<Episodio> clazz = Episodio.class;

      assertAll(
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("podcast"), "Deve possuir o atributo podcast"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("avaliacoes"), "Deve possuir o atributo avaliacoes")
      );
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
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("avaliar", Integer.class), "Deve possuir o método avaliar"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("calcularMedia"), "Deve possuir o método calcularMedia")
      );
    }
  }

  @Nested
  @DisplayName("3. Encapsulamento")
  class EncapsulamentoTest {

    @Test
    @DisplayName("1. Métodos Públicos")
    void test1() {
      Class<Episodio> clazz = Episodio.class;

      List<Method> metodos = new ArrayList<>();

      try {
        metodos.add(clazz.getDeclaredMethod("avaliar", Integer.class));
        metodos.add(clazz.getDeclaredMethod("calcularMedia"));
      } catch (ReflectiveOperationException ignored) {}

      Stream<Executable> validacoes = metodos.stream()
          .map(metodo -> () -> {
            assertTrue(Modifier.isPublic(metodo.getModifiers()));
          });

      assertAll(validacoes);
    }

    @ParameterizedTest
    @ArgumentsSource(ConstructorProvider.class)
    @DisplayName("2. Construtor deve inicializar os atributos corretamente")
    void test4(String nome, Double duracaoMinutos, String podcast) throws ReflectiveOperationException {
      Class<Episodio> clazz = Episodio.class;

      assertDoesNotThrow(() -> {
        clazz.getDeclaredConstructor(String.class, Double.class, String.class);
      }, "Construtor com parâmetros (String nome, Double duracaoMinutos, String podcast) deve existir");

      Object obj = clazz.getDeclaredConstructor(String.class, Double.class, String.class)
          .newInstance(nome, duracaoMinutos, podcast);

      ObjectFieldMapping<Episodio> mapping = new ObjectFieldMapping<>(Episodio.class);

      assertAll(
          () -> assertEquals(nome, mapping.get("nome").get(obj), "Nome deve ser inicializado corretamente"),
          () -> assertEquals(duracaoMinutos, mapping.get("duracaoMinutos").get(obj), "Duração Minutos deve ser inicializado corretamente"),
          () -> assertEquals(podcast, mapping.get("podcast").get(obj), "Podcast deve ser inicializado corretamente"),
          () -> assertIterableEquals(List.of(), (List<?>) mapping.get("avaliacoes").get(obj), "Avaliações deve ser inicializado corretamente")
      );
    }
  }

  @Nested
  @DisplayName("4. Método - avaliar")
  class MetodoBuscarTest {

    @ParameterizedTest
    @DisplayName("Deve adicionar avaliações corretamente")
    @ArgumentsSource(AvaliarProvider.class)
    void test1(Object obj, Integer nota, List<?> expected) throws ReflectiveOperationException {
      Class<Episodio> clazz = Episodio.class;
      Method method = clazz.getDeclaredMethod("avaliar", Integer.class);

      method.invoke(obj, nota);

      ObjectFieldMapping<Episodio> mapping = new ObjectFieldMapping<>(Episodio.class);
      List<?> atualAvaliacoes = (List<?>) mapping.get("avaliacoes").get(obj);

      assertIterableEquals(expected, atualAvaliacoes);
    }
  }

  @Nested
  @DisplayName("5. Método - calcularMedia")
  class MetodoCalcularMediaTest {

    @ParameterizedTest
    @DisplayName("Deve calcular a média corretamente")
    @ArgumentsSource(CalcularMediaProvider.class)
    void test1(Object obj, Double expected) throws ReflectiveOperationException {
      Class<Episodio> clazz = Episodio.class;
      Method method = clazz.getDeclaredMethod("calcularMedia");

      Object actual = method.invoke(obj);

      assertEquals(expected, (Double) actual, 0.01);
    }
  }
}
