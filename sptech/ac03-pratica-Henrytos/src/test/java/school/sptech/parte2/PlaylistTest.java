package school.sptech.parte2;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.Midia;
import school.sptech.Musica;
import school.sptech.Playlist;
import school.sptech.provider.playlist.AdicionarMusicaProvider;
import school.sptech.provider.playlist.AvaliarProvider;
import school.sptech.provider.playlist.CalcularDuracaoMinutosProvider;
import school.sptech.provider.playlist.CalcularMediaProvider;
import school.sptech.provider.playlist.ConstructorProvider;
import school.sptech.util.ObjectFieldMapping;

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
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Playlist - Parte 2")
public class PlaylistTest {

  @Nested
  @DisplayName("1. Atributos")
  class AtributosTest {

    @Test
    @DisplayName("1. Validar Atributos")
    void test1() {
      Class<Playlist> clazz = Playlist.class;

      assertAll(
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("nome"), "Deve possuir o atributo nome"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("avaliacoes"), "Deve possuir o atributo avaliacoes")
      );
    }

    @Test
    @DisplayName("2. Validar Atributos 'final'")
    void test2() {
      Class<Playlist> clazz = Playlist.class;

      Stream<Executable> validacoes = Stream.of("nome")
          .map(nomeCampo -> () -> {
            Field campo = clazz.getDeclaredField(nomeCampo);
            assertTrue(Modifier.isFinal(campo.getModifiers()),
                String.format("%s deve ser final", nomeCampo));
          });

      assertAll(validacoes);
    }
  }

  @Nested
  @DisplayName("2. Métodos")
  class MetodosTest {

    @Test
    @DisplayName("1. Validar Métodos")
    void test1() {
      Class<Playlist> clazz = Playlist.class;

      assertAll(
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("adicionarMusica", Musica.class), "Deve possuir o método adicionarMusica"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("calcularDuracaoMinutos"), "Deve possuir o método calcularDuracaoMinutos"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("avaliar", Integer.class), "Deve possuir o método avaliar"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("calcularMedia"), "Deve possuir o método calcularMedia")
      );
    }
  }

  @Nested
  @DisplayName("3. Encapsulamento")
  class EncapsulamentoTest {

    @Test
    @DisplayName("1. Atributos Privados")
    void test1() {
      Class<Playlist> clazz = Playlist.class;
      Field[] campos = clazz.getDeclaredFields();

      Stream<Executable> validacoes = Arrays.stream(campos)
          .map(campo -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
              String.format("%s deve ser privado", campo.getName())));

      assertAll(validacoes);
    }

    @Test
    @DisplayName("2. Métodos Públicos")
    void test2() {
      Class<Playlist> clazz = Playlist.class;

      List<Method> metodos = new ArrayList<>();

      try {
        metodos.add(clazz.getDeclaredMethod("adicionarMusica", Musica.class));
        metodos.add(clazz.getDeclaredMethod("calcularDuracaoMinutos"));
        metodos.add(clazz.getDeclaredMethod("avaliar", Integer.class));
        metodos.add(clazz.getDeclaredMethod("calcularMedia"));
      } catch (ReflectiveOperationException ignored) {}

      Stream<Executable> validacoes = metodos.stream()
          .map(metodo -> () -> {
            assertTrue(Modifier.isPublic(metodo.getModifiers()));
          });

      assertAll(validacoes);
    }

    @Test
    @DisplayName("3. Atributos devem possuir apenas getters")
    void test3() {
      Class<Playlist> clazz = Playlist.class;
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
      Class<Playlist> clazz = Playlist.class;

      assertDoesNotThrow(() -> {
        clazz.getDeclaredConstructor(String.class);
      }, "Construtor com parâmetros (String nome) deve existir");

      Object obj = clazz.getDeclaredConstructor(String.class)
          .newInstance(nome);

      ObjectFieldMapping<Playlist> mapping = new ObjectFieldMapping<>(Playlist.class);

      assertAll(
          () -> assertEquals(nome, mapping.get("nome").get(obj), "Nome deve ser inicializado corretamente"),
          () -> assertIterableEquals(List.of(), (List<?>) mapping.get("musicas").get(obj), "Musicas deve ser inicializado corretamente"),
          () -> assertIterableEquals(List.of(), (List<?>) mapping.get("avaliacoes").get(obj), "Avaliações deve ser inicializado corretamente")
      );
    }
  }

  @Nested
  @DisplayName("4. Método - adicionarMusica")
  class MetodoAdicionarMusicaTest {

    @ParameterizedTest
    @DisplayName("Deve adicionar músicas corretamente")
    @ArgumentsSource(AdicionarMusicaProvider.class)
    void test1(Object obj, Object musica, List<?> expected) throws ReflectiveOperationException {
      Class<Playlist> clazz = Playlist.class;
      Method method = clazz.getDeclaredMethod("adicionarMusica", Musica.class);

      method.invoke(obj, (Musica) musica);

      ObjectFieldMapping<Playlist> mapping = new ObjectFieldMapping<>(Playlist.class);
      List<?> musicas = (List<?>) mapping.get("musicas").get(obj);

      assertIterableEquals(expected, musicas, "Músicas devem ser adicionadas corretamente");
    }
  }

  @Nested
  @DisplayName("5. Método - calcularDuracaoMinutos")
  class MetodoCalcularDuracaoMinutosTest {

    @ParameterizedTest
    @DisplayName("Deve calcular a duração total corretamente")
    @ArgumentsSource(CalcularDuracaoMinutosProvider.class)
    void test1(Object obj, Double expected) throws ReflectiveOperationException {
      Class<Playlist> clazz = Playlist.class;

      Method calcularDuracaoMinutosMethod = clazz.getDeclaredMethod("calcularDuracaoMinutos");
      Object response = calcularDuracaoMinutosMethod.invoke(obj);

      assertEquals(expected, (Double) response, 0.01, "Duração total deve ser calculada corretamente");
    }
  }

  @Nested
  @DisplayName("6. Método - avaliar")
  class MetodoReceberPublicacaoTest {

    @ParameterizedTest
    @DisplayName("Deve adicionar avaliações corretamente")
    @ArgumentsSource(AvaliarProvider.class)
    void test1(Object obj, Integer nota, List<?> expected) throws ReflectiveOperationException {
      Class<Playlist> clazz = Playlist.class;
      Method method = clazz.getDeclaredMethod("avaliar", Integer.class);

      method.invoke(obj, nota);

      ObjectFieldMapping<Playlist> mapping = new ObjectFieldMapping<>(Playlist.class);
      List<?> atualAvaliacoes = (List<?>) mapping.get("avaliacoes").get(obj);

      assertIterableEquals(expected, atualAvaliacoes);
    }
  }

  @Nested
  @DisplayName("7. Método - calcularMedia")
  class MetodoCalcularMediaTest {

    @ParameterizedTest
    @DisplayName("Deve calcular a média corretamente")
    @ArgumentsSource(CalcularMediaProvider.class)
    void test1(Object obj, Double expected) throws ReflectiveOperationException {
      Class<Playlist> clazz = Playlist.class;
      Method method = clazz.getDeclaredMethod("calcularMedia");

      Object actual = method.invoke(obj);

      assertEquals(expected, (Double) actual, 0.01);
    }
  }
}
