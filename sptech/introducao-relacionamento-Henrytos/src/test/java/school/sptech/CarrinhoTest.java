package school.sptech;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ValueSource;
import school.sptech.provider.ProdutoGetPorNomeCaseInsensitiveProvider;
import school.sptech.provider.ProdutoGetPorNomeProvider;
import school.sptech.provider.ProdutoGetValorTotalProvider;
import school.sptech.provider.ProdutoNomeExistsCaseInsensitiveProvider;
import school.sptech.provider.ProdutoNomeExistsProvider;
import school.sptech.provider.ProdutoProvider;
import school.sptech.provider.ProdutoQuantidadePorCategoriaProvider;
import school.sptech.provider.ProdutoRemoverPorNomeCaseInsensitiveProvider;
import school.sptech.provider.ProdutoRemoverPorNomeProvider;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@DisplayName("Carrinho")
public class CarrinhoTest {

  @Nested
  @DisplayName("1. Atributos")
  class AtributosTests {

    @Test
    @DisplayName("Validar Atributos")
    void cenario1() {
      Class<Carrinho> clazz = Carrinho.class;

      Assertions.assertAll(
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredField("cliente")),
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredField("produtos"))
      );
    }
  }

  @Nested
  @DisplayName("2. Método")
  class MetodosTests {

    @Test
    @DisplayName("Validar Métodos")
    void validarMetodos() {
      Class<Carrinho> clazz = Carrinho.class;

      Assertions.assertAll(
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredMethod("getQuantidade")),
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredMethod("adicionar", Produto.class)),
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredMethod("existsPorNome", String.class)),
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredMethod("getQuantidadePorCategoria", String.class)),
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredMethod("limpar")),
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredMethod("removerPorNome", String.class)),
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredMethod("getPorNome", String.class)),
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredMethod("getValorTotal"))
      );
    }
  }

  @Nested
  @DisplayName("3. Encapsulamento")
  class EncapsulamentoTests {

    @Test
    @DisplayName("Atributos Privados")
    void cenario1() {
      Class<Carrinho> clazz = Carrinho.class;
      Field[] campos = clazz.getDeclaredFields();

      Stream<Executable> validacoes = Arrays.stream(campos)
          .map((campo) -> () -> Assertions.assertTrue(Modifier.isPrivate(campo.getModifiers())));

      Assertions.assertAll(validacoes);
    }

    @Test
    @DisplayName("Métodos públicos")
    void cenario2() throws ReflectiveOperationException {
      Class<Carrinho> clazz = Carrinho.class;

      Method getQuantidade = clazz.getDeclaredMethod("getQuantidade");
      Method adicionar = clazz.getDeclaredMethod("adicionar", Produto.class);
      Method existsPorNome = clazz.getDeclaredMethod("existsPorNome", String.class);
      Method getQuantidadePorCategoria = clazz.getDeclaredMethod("getQuantidadePorCategoria", String.class);
      Method limpar = clazz.getDeclaredMethod("limpar");
      Method removerPorNome = clazz.getDeclaredMethod("removerPorNome", String.class);
      Method getPorNome = clazz.getDeclaredMethod("getPorNome", String.class);
      Method getPorTotal = clazz.getDeclaredMethod("getValorTotal");

      Assertions.assertAll(
          () -> Assertions.assertTrue(Modifier.isPublic(getQuantidade.getModifiers())),
          () -> Assertions.assertTrue(Modifier.isPublic(adicionar.getModifiers())),
          () -> Assertions.assertTrue(Modifier.isPublic(existsPorNome.getModifiers())),
          () -> Assertions.assertTrue(Modifier.isPublic(getQuantidadePorCategoria.getModifiers())),
          () -> Assertions.assertTrue(Modifier.isPublic(limpar.getModifiers())),
          () -> Assertions.assertTrue(Modifier.isPublic(removerPorNome.getModifiers())),
          () -> Assertions.assertTrue(Modifier.isPublic(getPorNome.getModifiers())),
          () -> Assertions.assertTrue(Modifier.isPublic(getPorTotal.getModifiers()))
      );
    }
  }

  @Nested
  @DisplayName("4. Método - getQuantidade")
  class MetodoGetQuantidadeTests {

    Map<String, Field> campos() throws ReflectiveOperationException {
      Class<Carrinho> clazz = Carrinho.class;

      Field cliente = clazz.getDeclaredField("cliente");
      cliente.trySetAccessible();

      Field produtos = clazz.getDeclaredField("produtos");
      produtos.trySetAccessible();

      return Map.of("cliente", cliente, "produtos", produtos);
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 2, 3, 5, 8, 13, 21 })
    @DisplayName("Cenário 1")
    void cenario1(int qtd) throws ReflectiveOperationException {
      Class<Carrinho> cCarrinho = Carrinho.class;
      Method metodo = cCarrinho.getDeclaredMethod("getQuantidade");

      Object obj = new Carrinho();

      // Case
      campos().get("cliente").set(obj, "Gustavo");

      ArrayList<Produto> produtos = new ArrayList<>();

      for (int i = 0; i < qtd; i++) {
        produtos.add(new Produto());
      }

      campos().get("produtos").set(obj, produtos);

      // When
      Object resposta = metodo.invoke(obj);

      // Then
      Assertions.assertAll(
          () -> Assertions.assertEquals(qtd, resposta)
      );
    }
  }

  @Nested
  @DisplayName("5. Método - adicionar")
  class MetodoAdicionarTests {

    Map<String, Field> campos() throws ReflectiveOperationException {
      Class<Carrinho> clazz = Carrinho.class;

      Field cliente = clazz.getDeclaredField("cliente");
      cliente.trySetAccessible();

      Field produtos = clazz.getDeclaredField("produtos");
      produtos.trySetAccessible();

      return Map.of("cliente", cliente, "produtos", produtos);
    }

    @ParameterizedTest
    @ArgumentsSource(ProdutoProvider.class)
    @DisplayName("Cenário 1")
    void cenario1(List<Produto> produtos) throws ReflectiveOperationException {
      Class<Carrinho> cCarrinho = Carrinho.class;
      Method metodo = cCarrinho.getDeclaredMethod("adicionar", Produto.class);

      Object obj = new Carrinho();

      // Case
      campos().get("cliente").set(obj, "Vitor");
      campos().get("produtos").set(obj, new ArrayList<>());

      // When
      for (Object produto : produtos) {
        metodo.invoke(obj, produto);
      }

      // Then
      Assertions.assertAll(
          () -> Assertions.assertIterableEquals(produtos, (List<?>) campos().get("produtos").get(obj))
      );
    }
  }

  @Nested
  @DisplayName("6. Método - existsPorNome")
  class MetodoExistsPorNomeTests {

    Map<String, Field> campos() throws ReflectiveOperationException {
      Class<Carrinho> clazz = Carrinho.class;

      Field cliente = clazz.getDeclaredField("cliente");
      cliente.trySetAccessible();

      Field produtos = clazz.getDeclaredField("produtos");
      produtos.trySetAccessible();

      return Map.of("cliente", cliente, "produtos", produtos);
    }

    @ParameterizedTest
    @ArgumentsSource(ProdutoNomeExistsProvider.class)
    @DisplayName("Cenário 1")
    void cenario1(List<Produto> produtos, String nome, boolean resultado) throws ReflectiveOperationException {
      Class<Carrinho> cCarrinho = Carrinho.class;
      Method metodo = cCarrinho.getDeclaredMethod("existsPorNome", String.class);

      Object obj = new Carrinho();

      // Case
      campos().get("cliente").set(obj, "Vitor");
      campos().get("produtos").set(obj, produtos);

      // When
      Object resposta = metodo.invoke(obj, nome);

      // Then
      Assertions.assertAll(
          () -> Assertions.assertEquals(resultado, resposta)
      );
    }

    @ParameterizedTest
    @ArgumentsSource(ProdutoNomeExistsCaseInsensitiveProvider.class)
    @DisplayName("Cenário 2 - Case Insensitive")
    void cenario2(List<Produto> produtos, String nome, boolean resultado) throws ReflectiveOperationException {
      Class<Carrinho> cCarrinho = Carrinho.class;
      Method metodo = cCarrinho.getDeclaredMethod("existsPorNome", String.class);

      Object obj = new Carrinho();

      // Case
      campos().get("cliente").set(obj, "Vitor");
      campos().get("produtos").set(obj, produtos);

      // When
      Object resposta = metodo.invoke(obj, nome);

      // Then
      Assertions.assertAll(
          () -> Assertions.assertEquals(resultado, resposta)
      );
    }
  }

  @Nested
  @DisplayName("7. Método - getQuantidadePorCategoria")
  class MetodoGetQuantidadePorCategoriaTests {

    Map<String, Field> campos() throws ReflectiveOperationException {
      Class<Carrinho> clazz = Carrinho.class;

      Field cliente = clazz.getDeclaredField("cliente");
      cliente.trySetAccessible();

      Field produtos = clazz.getDeclaredField("produtos");
      produtos.trySetAccessible();

      return Map.of("cliente", cliente, "produtos", produtos);
    }

    @ParameterizedTest
    @ArgumentsSource(ProdutoQuantidadePorCategoriaProvider.class)
    @DisplayName("Cenário 1")
    void cenario1(List<Produto> produtos, String categoria, int resultado) throws ReflectiveOperationException {
      Class<Carrinho> cCarrinho = Carrinho.class;
      Method metodo = cCarrinho.getDeclaredMethod("getQuantidadePorCategoria", String.class);

      Object obj = new Carrinho();

      // Case
      campos().get("cliente").set(obj, "Vitor");
      campos().get("produtos").set(obj, produtos);

      // When
      Object resposta = metodo.invoke(obj, categoria);

      // Then
      Assertions.assertAll(
          () -> Assertions.assertEquals(resultado, resposta)
      );
    }

    @ParameterizedTest
    @ArgumentsSource(ProdutoQuantidadePorCategoriaProvider.class)
    @DisplayName("Cenário 2 - Case Insensitive")
    void cenario2(List<Produto> produtos, String categoria, int resultado) throws ReflectiveOperationException {
      Class<Carrinho> cCarrinho = Carrinho.class;
      Method metodo = cCarrinho.getDeclaredMethod("getQuantidadePorCategoria", String.class);

      Object obj = new Carrinho();

      // Case
      campos().get("cliente").set(obj, "Vitor");
      campos().get("produtos").set(obj, produtos);

      // When
      Object resposta = metodo.invoke(obj, categoria);

      // Then
      Assertions.assertAll(
          () -> Assertions.assertEquals(resultado, resposta)
      );
    }
  }

  @Nested
  @DisplayName("8. Método - limpar")
  class MetodoLimparTests {

    Map<String, Field> campos() throws ReflectiveOperationException {
      Class<Carrinho> clazz = Carrinho.class;

      Field cliente = clazz.getDeclaredField("cliente");
      cliente.trySetAccessible();

      Field produtos = clazz.getDeclaredField("produtos");
      produtos.trySetAccessible();

      return Map.of("cliente", cliente, "produtos", produtos);
    }

    @ParameterizedTest
    @ArgumentsSource(ProdutoProvider.class)
    @DisplayName("Cenário 1")
    void cenario1(List<Produto> produtos) throws ReflectiveOperationException {
      Class<Carrinho> cCarrinho = Carrinho.class;
      Method metodo = cCarrinho.getDeclaredMethod("limpar");

      Object obj = new Carrinho();

      // Case
      campos().get("cliente").set(obj, "Vitor");
      campos().get("produtos").set(obj, new ArrayList<>(produtos));

      // When
      metodo.invoke(obj);

      // Then
      Assertions.assertAll(
          () -> Assertions.assertIterableEquals(List.of(), (List<?>) campos().get("produtos").get(obj))
      );
    }
  }

  @Nested
  @DisplayName("9. Método - removerPorNome")
  class MetodoRemoverPorNomeTests {

    Map<String, Field> campos() throws ReflectiveOperationException {
      Class<Carrinho> clazz = Carrinho.class;

      Field cliente = clazz.getDeclaredField("cliente");
      cliente.trySetAccessible();

      Field produtos = clazz.getDeclaredField("produtos");
      produtos.trySetAccessible();

      return Map.of("cliente", cliente, "produtos", produtos);
    }

    @ParameterizedTest
    @ArgumentsSource(ProdutoRemoverPorNomeProvider.class)
    @DisplayName("Cenário 1")
    void cenario1(List<Produto> produtos, List<String> nomes, List<Produto> resultado) throws ReflectiveOperationException {
      Class<Carrinho> cCarrinho = Carrinho.class;
      Method metodo = cCarrinho.getDeclaredMethod("removerPorNome", String.class);

      Object obj = new Carrinho();

      // Case
      campos().get("cliente").set(obj, "Paulo");
      campos().get("produtos").set(obj, new ArrayList<>(produtos));

      // When
      for (String nome : nomes) {
        metodo.invoke(obj, nome);
      }

      // Then
      Assertions.assertAll(
          () -> Assertions.assertIterableEquals(resultado, (List<?>) campos().get("produtos").get(obj))
      );
    }

    @ParameterizedTest
    @ArgumentsSource(ProdutoRemoverPorNomeCaseInsensitiveProvider.class)
    @DisplayName("Cenário 2 - Case Insensitive")
    void cenario2(List<Produto> produtos, List<String> nomes, List<Produto> resultado) throws ReflectiveOperationException {
      Class<Carrinho> cCarrinho = Carrinho.class;
      Method metodo = cCarrinho.getDeclaredMethod("removerPorNome", String.class);

      Object obj = new Carrinho();

      // Case
      campos().get("cliente").set(obj, "Paulo");
      campos().get("produtos").set(obj, new ArrayList<>(produtos));

      // When
      for (String nome : nomes) {
        metodo.invoke(obj, nome);
      }

      // Then
      Assertions.assertAll(
          () -> Assertions.assertIterableEquals(resultado, (List<?>) campos().get("produtos").get(obj))
      );
    }
  }

  @Nested
  @DisplayName("10. Método - getPorNome")
  class MetodoGetPorNomeTests {

    Map<String, Field> campos() throws ReflectiveOperationException {
      Class<Carrinho> clazz = Carrinho.class;

      Field cliente = clazz.getDeclaredField("cliente");
      cliente.trySetAccessible();

      Field produtos = clazz.getDeclaredField("produtos");
      produtos.trySetAccessible();

      return Map.of("cliente", cliente, "produtos", produtos);
    }

    @ParameterizedTest
    @ArgumentsSource(ProdutoGetPorNomeProvider.class)
    @DisplayName("Cenário 1")
    void cenario1(List<Produto> produtos, String nome, Produto esperado) throws ReflectiveOperationException {
      Class<Carrinho> cCarrinho = Carrinho.class;
      Method metodo = cCarrinho.getDeclaredMethod("getPorNome", String.class);

      Object obj = new Carrinho();

      // Case
      campos().get("cliente").set(obj, "Paulo");
      campos().get("produtos").set(obj, new ArrayList<>(produtos));

      // When
      Object resposta = metodo.invoke(obj, nome);

      // Then
      Assertions.assertAll(
          () -> Assertions.assertEquals(esperado, resposta)
      );
    }

    @ParameterizedTest
    @ArgumentsSource(ProdutoGetPorNomeCaseInsensitiveProvider.class)
    @DisplayName("Cenário 2 - Case Insensitive")
    void cenario2(List<Produto> produtos, String nome, Produto esperado) throws ReflectiveOperationException {
      Class<Carrinho> cCarrinho = Carrinho.class;
      Method metodo = cCarrinho.getDeclaredMethod("getPorNome", String.class);

      Object obj = new Carrinho();

      // Case
      campos().get("cliente").set(obj, "Paulo");
      campos().get("produtos").set(obj, new ArrayList<>(produtos));

      // When
      Object resposta = metodo.invoke(obj, nome);

      // Then
      Assertions.assertAll(
          () -> Assertions.assertEquals(esperado, resposta)
      );
    }
  }

  @Nested
  @DisplayName("11. Método - getValorTotal")
  class MetodoGetValorTotalTests {

    Map<String, Field> campos() throws ReflectiveOperationException {
      Class<Carrinho> clazz = Carrinho.class;

      Field cliente = clazz.getDeclaredField("cliente");
      cliente.trySetAccessible();

      Field produtos = clazz.getDeclaredField("produtos");
      produtos.trySetAccessible();

      return Map.of("cliente", cliente, "produtos", produtos);
    }

    @ParameterizedTest
    @ArgumentsSource(ProdutoGetValorTotalProvider.class)
    @DisplayName("Cenário 1")
    void cenario1(List<Produto> produtos, double total) throws ReflectiveOperationException {
      Class<Carrinho> cCarrinho = Carrinho.class;
      Method metodo = cCarrinho.getDeclaredMethod("getValorTotal");

      Object obj = new Carrinho();

      // Case
      campos().get("cliente").set(obj, "Paulo");
      campos().get("produtos").set(obj, new ArrayList<>(produtos));

      // When
      Object resposta = metodo.invoke(obj);

      // Then
      Assertions.assertAll(
          () -> Assertions.assertEquals(total, (Double) resposta, 0.01)
      );
    }
  }
}
