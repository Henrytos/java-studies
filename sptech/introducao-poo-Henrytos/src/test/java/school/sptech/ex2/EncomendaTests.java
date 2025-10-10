package school.sptech.ex2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

@DisplayName("Atividade 2 - Encomenda")
public class EncomendaTests {

  @Nested
  @DisplayName("Atributos")
  class AtributosTests {

    @Test
    @DisplayName("Validar atributos")
    void validarAtributos() {
      Class<Encomenda> clazz = Encomenda.class;

      Assertions.assertAll(
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredField("tamanho")),
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredField("enderecoRemetente")),
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredField("enderecoDestinatario")),
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredField("distancia")),
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredField("valorProduto"))
      );
    }
  }

  @Nested
  @DisplayName("Métodos")
  class MetodosTests {

    @Test
    @DisplayName("Validar métodos")
    void validarMetodos() {
      Class<Encomenda> clazz = Encomenda.class;

      Assertions.assertAll(
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredMethod("calcularFrete")),
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredMethod("aplicarCupomDeDesconto", Integer.class)),
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredMethod("valorTotalDaEncomenda"))
      );
    }
  }

  @Nested
  @DisplayName("Método - calcularFrete")
  class MetodoCalcularFreteTests {

    Map<String, Field> campos() throws ReflectiveOperationException {
      Class<Encomenda> clazz = Encomenda.class;

      return Map.of(
          "tamanho", clazz.getDeclaredField("tamanho"),
          "enderecoRemetente", clazz.getDeclaredField("enderecoRemetente"),
          "enderecoDestinatario", clazz.getDeclaredField("enderecoDestinatario"),
          "distancia", clazz.getDeclaredField("distancia"),
          "valorProduto", clazz.getDeclaredField("valorProduto")
      );
    }

    @Test
    @DisplayName("Cenário 1")
    void cenario1() throws ReflectiveOperationException {
      Class<Encomenda> clazz = Encomenda.class;

      Method metodo = clazz.getDeclaredMethod("calcularFrete");

      Object obj = new Encomenda();

      // Case
      campos().get("tamanho").set(obj, "M");
      campos().get("enderecoRemetente").set(obj, "Rua Santos da Glória, 18");
      campos().get("enderecoDestinatario").set(obj, "Av Dr. Pedro, 255");
      campos().get("distancia").set(obj, 35.0);
      campos().get("valorProduto").set(obj, 50.0);

      // When
      Object resposta = metodo.invoke(obj);

      // Then
      Assertions.assertEquals(4.5, (Double) resposta, 0.01);
    }



    @Test
    @DisplayName("Cenário 2")
    void cenario2() throws ReflectiveOperationException {
      Class<Encomenda> clazz = Encomenda.class;

      Method metodo = clazz.getDeclaredMethod("calcularFrete");

      Object obj = new Encomenda();

      // Case
      campos().get("tamanho").set(obj, "G");
      campos().get("enderecoRemetente").set(obj, "Rua Santos da Glória, 18");
      campos().get("enderecoDestinatario").set(obj, "Av Dr. Pedro, 255");
      campos().get("distancia").set(obj, 90.0);
      campos().get("valorProduto").set(obj, 100.0);

      // When
      Object resposta = metodo.invoke(obj);

      // Then
      Assertions.assertEquals(10.0, (Double) resposta, 0.01);
    }

    @Test
    @DisplayName("Cenário 3")
    void cenario3() throws ReflectiveOperationException {
      Class<Encomenda> clazz = Encomenda.class;

      Method metodo = clazz.getDeclaredMethod("calcularFrete");

      Object obj = new Encomenda();

      // Case
      campos().get("tamanho").set(obj, "P");
      campos().get("enderecoRemetente").set(obj, "Rua Santos da Glória, 18");
      campos().get("enderecoDestinatario").set(obj, "Av Dr. Pedro, 255");
      campos().get("distancia").set(obj, 330.0);
      campos().get("valorProduto").set(obj, 100.0);

      // When
      Object resposta = metodo.invoke(obj);

      // Then
      Assertions.assertEquals(8.0, (Double) resposta, 0.01);
    }
  }

  @Nested
  @DisplayName("Método - aplicarCupomDeDesconto")
  class MetodoAplicarCupomDeDescontoTests {

    Map<String, Field> campos() throws ReflectiveOperationException {
      Class<Encomenda> clazz = Encomenda.class;

      return Map.of(
          "tamanho", clazz.getDeclaredField("tamanho"),
          "enderecoRemetente", clazz.getDeclaredField("enderecoRemetente"),
          "enderecoDestinatario", clazz.getDeclaredField("enderecoDestinatario"),
          "distancia", clazz.getDeclaredField("distancia"),
          "valorProduto", clazz.getDeclaredField("valorProduto")
      );
    }

    @Test
    @DisplayName("Cenário 1")
    void cenario1() throws ReflectiveOperationException {
      Class<Encomenda> clazz = Encomenda.class;

      Method metodo = clazz.getDeclaredMethod("aplicarCupomDeDesconto", Integer.class);

      Object obj = new Encomenda();

      // Case
      campos().get("tamanho").set(obj, "P");
      campos().get("enderecoRemetente").set(obj, "Rua Santos da Glória, 18");
      campos().get("enderecoDestinatario").set(obj, "Av Dr. Pedro, 255");
      campos().get("distancia").set(obj, 330.0);
      campos().get("valorProduto").set(obj, 100.0);

      // When
      metodo.invoke(obj, 10);

      // Then
      Assertions.assertEquals(90.0, (Double) campos().get("valorProduto").get(obj), 0.01);
    }

    @Test
    @DisplayName("Cenário 2")
    void cenario2() throws ReflectiveOperationException {
      Class<Encomenda> clazz = Encomenda.class;

      Method metodo = clazz.getDeclaredMethod("aplicarCupomDeDesconto", Integer.class);

      Object obj = new Encomenda();

      // Case
      campos().get("tamanho").set(obj, "M");
      campos().get("enderecoRemetente").set(obj, "Rua Santos da Glória, 18");
      campos().get("enderecoDestinatario").set(obj, "Av Dr. Pedro, 255");
      campos().get("distancia").set(obj, 200.0);
      campos().get("valorProduto").set(obj, 50.0);

      // When
      metodo.invoke(obj, 15);

      // Then
      Assertions.assertEquals(42.5, (Double) campos().get("valorProduto").get(obj), 0.01);
    }

    @Test
    @DisplayName("Cenário 3")
    void cenario3() throws ReflectiveOperationException {
      Class<Encomenda> clazz = Encomenda.class;

      Method metodo = clazz.getDeclaredMethod("aplicarCupomDeDesconto", Integer.class);

      Object obj = new Encomenda();

      // Case
      campos().get("tamanho").set(obj, "G");
      campos().get("enderecoRemetente").set(obj, "Rua Santos da Glória, 18");
      campos().get("enderecoDestinatario").set(obj, "Av Dr. Pedro, 255");
      campos().get("distancia").set(obj, 330.0);
      campos().get("valorProduto").set(obj, 64.90);

      // When
      metodo.invoke(obj, 30);

      // Then
      Assertions.assertEquals(45.43, (Double) campos().get("valorProduto").get(obj), 0.01);
    }

    @Test
    @DisplayName("Cenário 4")
    void cenario4() throws ReflectiveOperationException {
      Class<Encomenda> clazz = Encomenda.class;

      Method metodo = clazz.getDeclaredMethod("aplicarCupomDeDesconto", Integer.class);

      Object obj = new Encomenda();

      // Case
      campos().get("tamanho").set(obj, "P");
      campos().get("enderecoRemetente").set(obj, "Rua Santos da Glória, 18");
      campos().get("enderecoDestinatario").set(obj, "Av Dr. Pedro, 255");
      campos().get("distancia").set(obj, 330.0);
      campos().get("valorProduto").set(obj, 4500.0);

      // When
      metodo.invoke(obj, 20);

      // Then
      Assertions.assertEquals(3600.0, (Double) campos().get("valorProduto").get(obj), 0.01);
    }
  }

  @Nested
  @DisplayName("Método - valorTotalDaEncomenda")
  class MetodoValorTotalDaEncomendaTests {

    Map<String, Field> campos() throws ReflectiveOperationException {
      Class<Encomenda> clazz = Encomenda.class;

      return Map.of(
          "tamanho", clazz.getDeclaredField("tamanho"),
          "enderecoRemetente", clazz.getDeclaredField("enderecoRemetente"),
          "enderecoDestinatario", clazz.getDeclaredField("enderecoDestinatario"),
          "distancia", clazz.getDeclaredField("distancia"),
          "valorProduto", clazz.getDeclaredField("valorProduto")
      );
    }

    @Test
    @DisplayName("Cenário 1")
    void cenario1() throws ReflectiveOperationException {
      Class<Encomenda> clazz = Encomenda.class;

      Method metodo = clazz.getDeclaredMethod("valorTotalDaEncomenda");

      Object obj = new Encomenda();

      // Case
      campos().get("tamanho").set(obj, "P");
      campos().get("enderecoRemetente").set(obj, "Rua Santos da Glória, 18");
      campos().get("enderecoDestinatario").set(obj, "Av Dr. Pedro, 255");
      campos().get("distancia").set(obj, 330.0);
      campos().get("valorProduto").set(obj, 100.0);

      // When
      Object resposta = metodo.invoke(obj);

      // Then
      Assertions.assertEquals(108.0, (Double) resposta, 0.01);
    }

    @Test
    @DisplayName("Cenário 2")
    void cenario2() throws ReflectiveOperationException {
      Class<Encomenda> clazz = Encomenda.class;

      Method metodo = clazz.getDeclaredMethod("valorTotalDaEncomenda");

      Object obj = new Encomenda();

      // Case
      campos().get("tamanho").set(obj, "M");
      campos().get("enderecoRemetente").set(obj, "Rua Santos da Glória, 18");
      campos().get("enderecoDestinatario").set(obj, "Av Dr. Pedro, 255");
      campos().get("distancia").set(obj, 35.0);
      campos().get("valorProduto").set(obj, 50.0);

      // When
      Object resposta = metodo.invoke(obj);

      // Then
      Assertions.assertEquals(54.5, (Double) resposta, 0.01);
    }
  }
}
