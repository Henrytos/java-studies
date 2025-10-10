package school.sptech;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.factory.CarrinhoFactory;
import school.sptech.provider.carrinho.AdicionarProdutoProvider;
import school.sptech.provider.carrinho.BuscarCelularesForaDaGarantiaProvider;
import school.sptech.provider.carrinho.BuscarCelularesPorMarcaProvider;
import school.sptech.provider.carrinho.BuscarProdutoMaisBaratoProvider;
import school.sptech.provider.carrinho.BuscarProdutoMaisCaroProvider;
import school.sptech.provider.carrinho.BuscarProdutoPorNomeProvider;
import school.sptech.provider.carrinho.BuscarTopCincoProdutosMaisBaratosProvider;
import school.sptech.provider.carrinho.CalcularValorTotalProvider;
import school.sptech.provider.carrinho.RemoverProdutoProvider;

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

@DisplayName("Carrinho")
public class CarrinhoTest {

  Map<String, Field> campos() throws ReflectiveOperationException {
    Class<Carrinho> clazz = Carrinho.class;

    Map<String, Field> mapCampos = new HashMap<>();
    String[] nomeCampos = { "id", "produtos" };

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
      Class<Carrinho> clazz = Carrinho.class;

      assertAll(
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("id"), "Deve possuir o atributo id"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("produtos"), "Deve possuir o atributo produtos")
      );
    }
  }

  @Nested
  @DisplayName("2. Métodos")
  class MetodosTest {

    @Test
    @DisplayName("1. Validar Métodos")
    void test1() {
      Class<Carrinho> clazz = Carrinho.class;

      assertAll(
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("adicionarProduto", Produto.class), "Deve possuir o método adicionarProduto"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("removerProduto", String.class), "Deve possuir o método removerProduto"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("buscarProdutoPorNome", String.class), "Deve possuir o método buscarProdutoPorNome"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("calcularValorTotal"), "Deve possuir o método calcularValorTotal"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("buscarProdutoMaisBarato"), "Deve possuir o método buscarProdutoMaisBarato"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("buscarProdutoMaisCaro"), "Deve possuir o método buscarProdutoMaisCaro"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("buscarCelularesPorMarca", String.class), "Deve possuir o método buscarCelularesPorMarca"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("buscarCelularesForaDaGarantia", Integer.class), "Deve possuir o método buscarCelularesForaDaGarantia"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("buscarTopCincoProdutosMaisBaratos"), "Deve possuir o método buscarTopCincoProdutosMaisBaratos")
      );
    }
  }

  @Nested
  @DisplayName("3. Encapsulamento")
  class EncapsulamentoTest {

    @Test
    @DisplayName("1. Atributos Privados")
    void test1() {
      Class<Carrinho> clazz = Carrinho.class;
      Field[] campos = clazz.getDeclaredFields();

      Stream<Executable> validacoes = Arrays.stream(campos)
          .map((campo) -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
              String.format("%s deve ser privado", campo.getName())));

      assertAll(validacoes);
    }

    @Test
    @DisplayName("2. Métodos Públicos")
    void test2() {
      Class<Carrinho> clazz = Carrinho.class;

      List<Method> metodos = new ArrayList<>();

      try {
        metodos.add(clazz.getDeclaredMethod("adicionarProduto", Produto.class));
      } catch (ReflectiveOperationException ignored) {}

      try {
        metodos.add(clazz.getDeclaredMethod("removerProduto", String.class));
      } catch (ReflectiveOperationException ignored) {}

      try {
        metodos.add(clazz.getDeclaredMethod("buscarProdutoPorNome", String.class));
      } catch (ReflectiveOperationException ignored) {}

      try {
        metodos.add(clazz.getDeclaredMethod("calcularValorTotal"));
      } catch (ReflectiveOperationException ignored) {}

      try {
        metodos.add(clazz.getDeclaredMethod("buscarProdutoMaisBarato"));
      } catch (ReflectiveOperationException ignored) {}

      try {
        metodos.add(clazz.getDeclaredMethod("buscarProdutoMaisCaro"));
      } catch (ReflectiveOperationException ignored) {}

      try {
        metodos.add(clazz.getDeclaredMethod("buscarCelularesPorMarca", String.class));
      } catch (ReflectiveOperationException ignored) {}

      try {
        metodos.add(clazz.getDeclaredMethod("buscarCelularesForaDaGarantia", Integer.class));
      } catch (ReflectiveOperationException ignored) {}

      try {
        metodos.add(clazz.getDeclaredMethod("buscarTopCincoProdutosMaisBaratos"));
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
      Class<Carrinho> clazz = Carrinho.class;
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
  @DisplayName("4. Método - adicionarProduto")
  class MetodoAdicionarProdutoTest {

    @ParameterizedTest
    @ArgumentsSource(AdicionarProdutoProvider.class)
    @DisplayName("Teste 1")
    void test1(Integer id, List<?> produtos, Object produto, List<?> expected) throws ReflectiveOperationException {
      Class<Carrinho> clazz = Carrinho.class;
      Method metodo = clazz.getDeclaredMethod("adicionarProduto", Produto.class);

      // Case
      Object obj = CarrinhoFactory.build(id, new ArrayList<>(produtos));

      // When
      metodo.invoke(obj, produto);

      // Then
      assertAll(
          () -> assertIterableEquals(new ArrayList<>(expected), (List<?>) campos().get("produtos").get(obj))
      );
    }
  }

  @Nested
  @DisplayName("5. Método - removerProduto")
  class MetodoRemoverProdutoTest {

    @ParameterizedTest
    @ArgumentsSource(RemoverProdutoProvider.class)
    @DisplayName("Teste 1")
    void test1(Integer id, List<?> produtos, String nome, List<?> expected) throws ReflectiveOperationException {
      Class<Carrinho> clazz = Carrinho.class;
      Method metodo = clazz.getDeclaredMethod("removerProduto", String.class);

      // Case
      Object obj = CarrinhoFactory.build(id, new ArrayList<>(produtos));

      // When
      metodo.invoke(obj, nome);

      // Then
      assertAll(
          () -> assertIterableEquals(new ArrayList<>(expected), (List<?>) campos().get("produtos").get(obj))
      );
    }
  }

  @Nested
  @DisplayName("6. Método - buscarProdutoPorNome")
  class MetodoBuscarProdutoPorNomeTest {

    @ParameterizedTest
    @ArgumentsSource(BuscarProdutoPorNomeProvider.class)
    @DisplayName("Teste 1")
    void test1(Integer id, List<?> produtos, String nome, Object expected) throws ReflectiveOperationException {
      Class<Carrinho> clazz = Carrinho.class;
      Method metodo = clazz.getDeclaredMethod("buscarProdutoPorNome", String.class);

      // Case
      Object obj = CarrinhoFactory.build(id, new ArrayList<>(produtos));

      // When
      Object resposta = metodo.invoke(obj, nome);

      // Then
      assertAll(
          () -> assertEquals(expected, resposta),
          () -> assertEquals(expected, resposta)
      );
    }
  }

  @Nested
  @DisplayName("7. Método - calcularValorTotal")
  class MetodoCalcularValorTotalTest {

    @ParameterizedTest
    @ArgumentsSource(CalcularValorTotalProvider.class)
    @DisplayName("Teste 1")
    void test1(Integer id, List<?> produtos, Double expected) throws ReflectiveOperationException {
      Class<Carrinho> clazz = Carrinho.class;
      Method metodo = clazz.getDeclaredMethod("calcularValorTotal");

      // Case
      Object obj = CarrinhoFactory.build(id, new ArrayList<>(produtos));

      // When
      Double resposta = (Double) metodo.invoke(obj);

      // Then
      assertAll(
          () -> assertEquals(expected, resposta, 0.01)
      );
    }
  }

  @Nested
  @DisplayName("8. Método - buscarProdutoMaisBarato")
  class MetodoBuscarProdutoMaisBaratoTest {

    @ParameterizedTest
    @ArgumentsSource(BuscarProdutoMaisBaratoProvider.class)
    @DisplayName("Teste 1")
    void test1(Integer id, List<?> produtos, Object expected) throws ReflectiveOperationException {
      Class<Carrinho> clazz = Carrinho.class;
      Method metodo = clazz.getDeclaredMethod("buscarProdutoMaisBarato");

      // Case
      Object obj = CarrinhoFactory.build(id, new ArrayList<>(produtos));

      // When
      Object resposta = metodo.invoke(obj);

      // Then
      assertAll(
          () -> assertEquals(expected, resposta),
          () -> assertEquals(expected, resposta)
      );
    }
  }

  @Nested
  @DisplayName("9. Método - buscarProdutoMaisCaro")
  class MetodoBuscarProdutoMaisCaroTest {

    @ParameterizedTest
    @ArgumentsSource(BuscarProdutoMaisCaroProvider.class)
    @DisplayName("Teste 1")
    void test1(Integer id, List<?> produtos, Object expected) throws ReflectiveOperationException {
      Class<Carrinho> clazz = Carrinho.class;
      Method metodo = clazz.getDeclaredMethod("buscarProdutoMaisCaro");

      // Case
      Object obj = CarrinhoFactory.build(id, new ArrayList<>(produtos));

      // When
      Object resposta = metodo.invoke(obj);

      // Then
      assertAll(
          () -> assertEquals(expected, resposta)
      );
    }
  }

  @Nested
  @DisplayName("10. Método - buscarCelularesPorMarca")
  class MetodoBuscarCelularesPorMarcaTest {

    @ParameterizedTest
    @ArgumentsSource(BuscarCelularesPorMarcaProvider.class)
    @DisplayName("Teste 1")
    void test1(Integer id, List<?> produtos, String marca, List<?> expected) throws ReflectiveOperationException {
      Class<Carrinho> clazz = Carrinho.class;
      Method metodo = clazz.getDeclaredMethod("buscarCelularesPorMarca", String.class);

      // Case
      Object obj = CarrinhoFactory.build(id, new ArrayList<>(produtos));

      // When
      List<?> resposta = (List<?>) metodo.invoke(obj, marca);

      // Then
      assertAll(
          () -> assertIterableEquals(new ArrayList<>(expected), resposta)
      );
    }
  }

  @Nested
  @DisplayName("11. Método - buscarCelularesForaDaGarantia")
  class MetodoBuscarCelularesForaDaGarantiaTest {

    @ParameterizedTest
    @ArgumentsSource(BuscarCelularesForaDaGarantiaProvider.class)
    @DisplayName("Teste 1")
    void test1(Integer id, List<?> produtos, Integer mesesUso, List<?> expected) throws ReflectiveOperationException {
      Class<Carrinho> clazz = Carrinho.class;
      Method metodo = clazz.getDeclaredMethod("buscarCelularesForaDaGarantia", Integer.class);

      // Case
      Object obj = CarrinhoFactory.build(id, new ArrayList<>(produtos));

      // When
      List<?> resposta = (List<?>) metodo.invoke(obj, mesesUso);

      // Then
      assertAll(
          () -> assertIterableEquals(new ArrayList<>(expected), resposta)
      );
    }
  }

  @Nested
  @DisplayName("12. Método - buscarTopCincoProdutosMaisBaratos")
  class MetodoBuscarTopCincoProdutosMaisBaratosTest {

    @ParameterizedTest
    @ArgumentsSource(BuscarTopCincoProdutosMaisBaratosProvider.class)
    @DisplayName("Teste 1")
    void test1(Integer id, List<?> produtos, List<?> expected) throws ReflectiveOperationException {
      Class<Carrinho> clazz = Carrinho.class;
      Method metodo = clazz.getDeclaredMethod("buscarTopCincoProdutosMaisBaratos");

      // Case
      Object obj = CarrinhoFactory.build(id, new ArrayList<>(produtos));

      // When
      List<?> resposta = (List<?>) metodo.invoke(obj);

      // Then
      assertAll(
          () -> assertIterableEquals(new ArrayList<>(expected), resposta)
      );
    }
  }
}
