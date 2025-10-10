package school.sptech;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Stream;

@DisplayName("Produto")
public class ProdutoTest {

  @Nested
  @DisplayName("1. Atributos")
  class AtributosTests {

    @Test
    @DisplayName("Validar Atributos")
    void cenario1() {
      Class<Produto> clazz = Produto.class;

      Assertions.assertAll(
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredField("nome")),
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredField("categoria")),
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredField("preco"))
      );
    }
  }

  @Nested
  @DisplayName("2. Encapsulamento")
  class EncapsulamentoTests {

    @Test
    @DisplayName("Atributos Privados")
    void cenario1() {
      Class<Produto> clazz = Produto.class;
      Field[] campos = clazz.getDeclaredFields();

      Stream<Executable> validacoes = Arrays.stream(campos)
          .map((campo) -> () -> Assertions.assertTrue(Modifier.isPrivate(campo.getModifiers())));

      Assertions.assertAll(validacoes);
    }

    @Test
    @DisplayName("Atributos devem possuir getters e setters")
    void cenario2() {
      Class<Produto> clazz = Produto.class;
      Field[] campos = clazz.getDeclaredFields();

      Stream<Executable> validacoesGetter = Arrays.stream(campos)
          .map((campo) -> () -> {
            String getName = String.format("get%s", StringUtils.capitalize(campo.getName()));
            Method getter = clazz.getDeclaredMethod(getName);
            int getModifier = getter.getModifiers();
            Assertions.assertTrue(Modifier.isPublic(getModifier));
          });

      Stream<Executable> validacoesSetter = Arrays.stream(campos)
          .map((campo) -> () -> {
            String setName = String.format("set%s", StringUtils.capitalize(campo.getName()));
            Method setter = clazz.getDeclaredMethod(setName, campo.getType());
            int setModifier = setter.getModifiers();
            Assertions.assertTrue(Modifier.isPublic(setModifier));
          });

      Assertions.assertAll(Stream.concat(validacoesGetter, validacoesSetter));
    }
  }
}
