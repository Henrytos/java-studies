package school.sptech.parte2;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.Mockito;
import school.sptech.Avaliavel;
import school.sptech.Musica;
import school.sptech.Playlist;
import school.sptech.Streaming;
import school.sptech.provider.streaming.*;
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

@DisplayName("Streaming - Parte 2")
public class StreamingTest {

  @Nested
  @DisplayName("1. Atributos")
  class AtributosTest {

    @Test
    @DisplayName("1. Validar Atributos")
    void test1() {
      Class<Streaming> clazz = Streaming.class;

      assertAll(
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("nome"), "Deve possuir o atributo nome"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("midias"), "Deve possuir o atributo midias"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("playlists"), "Deve possuir o atributo playlists")
      );
    }
  }

  @Nested
  @DisplayName("2. Métodos")
  class MetodosTest {

    @Test
    @DisplayName("1. Validar Métodos")
    void test1() {
      Class<Streaming> clazz = Streaming.class;

      assertAll(
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("adicionarPlaylist", String.class), "Deve possuir o método adicionarPlaylist"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("adicionarMusicaNaPlaylist", Musica.class, String.class), "Deve possuir o método adicionarMusicaNaPlaylist"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("avaliarMidia", String.class, Integer.class), "Deve possuir o método avaliarMidia"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("avaliarPlaylist", String.class, Integer.class), "Deve possuir o método avaliarPlaylist"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("buscarPorMediaMaior", Double.class), "Deve possuir o método buscarPorMediaMaior")
      );
    }
  }

  @Nested
  @DisplayName("3. Encapsulamento")
  class EncapsulamentoTest {

    @Test
    @DisplayName("1. Atributos Privados")
    void test1() {
      Class<Streaming> clazz = Streaming.class;
      Field[] campos = clazz.getDeclaredFields();

      Stream<Executable> validacoes = Arrays.stream(campos)
          .map((campo) -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
              String.format("%s deve ser privado", campo.getName())));

      assertAll(validacoes);
    }

    @Test
    @DisplayName("2. Métodos Públicos")
    void test2() {
      Class<Streaming> clazz = Streaming.class;

      List<Method> metodos = new ArrayList<>();

      try {
        metodos.add(clazz.getDeclaredMethod("adicionarPlaylist", String.class));
        metodos.add(clazz.getDeclaredMethod("adicionarMusicaNaPlaylist", Musica.class, String.class));
        metodos.add(clazz.getDeclaredMethod("avaliarMidia", String.class, Integer.class));
        metodos.add(clazz.getDeclaredMethod("avaliarPlaylist", String.class, Integer.class));
        metodos.add(clazz.getDeclaredMethod("buscarPorMediaMaior", Double.class));
      } catch (ReflectiveOperationException ignored) {
      }

      Stream<Executable> validacoes = metodos.stream()
          .map((metodo) -> () -> {
            assertTrue(Modifier.isPublic(metodo.getModifiers()),
                String.format("%s deve ser público", metodo.getName()));
          });

      assertAll(validacoes);
    }

    @Test
    @DisplayName("3. Atributos devem possuir apenas getters")
    void test3() {
      Class<Streaming> clazz = Streaming.class;
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
      Class<Streaming> clazz = Streaming.class;

      assertDoesNotThrow(() -> {
        clazz.getDeclaredConstructor(String.class);
      }, "Construtor com parâmetros (String nome) deve existir");

      Object obj = clazz.getDeclaredConstructor(String.class).newInstance(nome);

      ObjectFieldMapping<Streaming> mapping = new ObjectFieldMapping<>(Streaming.class);

      assertAll(
          () -> assertEquals(nome, mapping.get("nome").get(obj), "Nome deve ser inicializado corretamente"),
          () -> assertIterableEquals(List.of(), (List<?>) mapping.get("midias").get(obj), "Midias deve ser inicializado corretamente"),
          () -> assertIterableEquals(List.of(), (List<?>) mapping.get("playlists").get(obj), "Playlists deve ser inicializado corretamente")
      );
    }
  }

  @Nested
  @DisplayName("4. Método - adicionarPlaylist")
  class AdicionarPlaylistTest {

    @ParameterizedTest
    @ArgumentsSource(AdicionarPlaylistProvider.class)
    @DisplayName("1. Deve adicionar playlist corretamente")
    void test1(Object streaming, String nomePlaylist, List<?> expected) throws ReflectiveOperationException {

      Class<Streaming> clazz = Streaming.class;
      Method method = clazz.getDeclaredMethod("adicionarPlaylist", String.class);

      method.invoke(streaming, nomePlaylist);

      ObjectFieldMapping<Streaming> mapping = new ObjectFieldMapping<>(Streaming.class);
      List<?> actual = (List<?>) mapping.get("playlists").get(streaming);

      assertEquals(expected.size(), actual.size(), "Número de playlists deve ser igual ao esperado");

      for (int i = 0; i < expected.size(); i++) {
        Object expectedPlaylist = expected.get(i);
        Object actualPlaylist = actual.get(i);

        ObjectFieldMapping<?> playlistMapping = new ObjectFieldMapping<>(expectedPlaylist.getClass());

        String expectedNome = (String) playlistMapping.get("nome").get(expectedPlaylist);
        String actualNome = (String) playlistMapping.get("nome").get(actualPlaylist);

        assertEquals(expectedNome, actualNome, String.format("Nome da playlist [%d] deve ser igual ao esperado", i));
      }
    }
  }

  @Nested
  @DisplayName("5. Método - adicionarMusicaNaPlaylist")
  class AdicionarMusicaNaPlaylistTest {

    @ParameterizedTest
    @ArgumentsSource(AdicionarMusicaNaPlaylistProvider.class)
    @DisplayName("1. Deve adicionar música na playlist corretamente")
    void test1(Object streaming, Object musica, String nomePlaylist, Object playlistExpected) throws ReflectiveOperationException {

      Class<Streaming> clazz = Streaming.class;
      Method method = clazz.getDeclaredMethod("adicionarMusicaNaPlaylist", Musica.class, String.class);

      ObjectFieldMapping<Streaming> mapping = new ObjectFieldMapping<>(Streaming.class);
      method.invoke(streaming, (Musica) musica, nomePlaylist);

      Object mock = Mockito.verify(playlistExpected, Mockito.times(1));

      Method adicionarMusicaMethod = Playlist.class.getDeclaredMethod("adicionarMusica", Musica.class);
      adicionarMusicaMethod.invoke(mock, (Musica) musica);
    }
  }

  @Nested
  @DisplayName("6. Método - avaliarMidia")
  class AvaliarMidiaTest {

    @ParameterizedTest
    @ArgumentsSource(AvaliarMidiaProvider.class)
    @DisplayName("1. Deve avaliar mídia corretamente")
    void test1(Object streaming, String nomeMidia, Integer nota, Object midiaExpected) throws ReflectiveOperationException {

      Class<Streaming> clazz = Streaming.class;
      Method method = clazz.getDeclaredMethod("avaliarMidia", String.class, Integer.class);

      method.invoke(streaming, nomeMidia, nota);

      Object mock = Mockito.verify(midiaExpected, Mockito.times(1));

      Method avaliarMethod = Avaliavel.class.getDeclaredMethod("avaliar", Integer.class);
      avaliarMethod.invoke(mock, nota);
    }
  }

  @Nested
  @DisplayName("7. Método - avaliarPlaylist")
  class AvaliarPlaylistTest {

    @ParameterizedTest
    @ArgumentsSource(AvaliarPlaylistProvider.class)
    @DisplayName("1. Deve avaliar playlist corretamente")
    void test1(Object streaming, String nomePlaylist, Integer nota, Object playlistExpected) throws ReflectiveOperationException {

      Class<Streaming> clazz = Streaming.class;
      Method method = clazz.getDeclaredMethod("avaliarPlaylist", String.class, Integer.class);

      method.invoke(streaming, nomePlaylist, nota);

      Object mock = Mockito.verify(playlistExpected, Mockito.times(1));

      Method avaliarMethod = Avaliavel.class.getDeclaredMethod("avaliar", Integer.class);
      avaliarMethod.invoke(mock, nota);
    }
  }

  @Nested
  @DisplayName("8. Método - buscarPorMediaMaior")
  class BuscarPorMediaMaiorTest {

    @ParameterizedTest
    @ArgumentsSource(BuscarPorMediaMaiorProvider.class)
    @DisplayName("1. Deve buscar por média maior corretamente")
    void test1(Object streaming, Double media, List<?> expected) throws ReflectiveOperationException {

      Class<Streaming> clazz = Streaming.class;
      Method method = clazz.getDeclaredMethod("buscarPorMediaMaior", Double.class);

      Object result = method.invoke(streaming, media);
      List<?> actual = (List<?>) result;

      assertIterableEquals(expected, actual, "Listas de avaliáveis devem ser iguais");
    }
  }
}
