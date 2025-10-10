package school.sptech.ex1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

@DisplayName("Atividade 1 - Confeitaria")
public class BoloTests {

  @Nested
  @DisplayName("Atributos")
  class AtributosTests {

    @Test
    @DisplayName("Validar atributos")
    void validarAtributos() {
      Class<Bolo> clazz = Bolo.class;

      Assertions.assertAll(
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredField("sabor")),
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredField("valor")),
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredField("quantidadeVendida")),
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredField("quantidadeEmEstoque"))
      );
    }
  }

  @Nested
  @DisplayName("Métodos")
  class MetodosTests {

    @Test
    @DisplayName("Validar métodos")
    void validarMetodos() {
      Class<Bolo> clazz = Bolo.class;

      Assertions.assertAll(
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredMethod("venderBolo", Integer.class)),
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredMethod("aumentarEstoque", Integer.class)),
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredMethod("quantidadeDisponivel")),
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredMethod("totalVendido"))
      );
    }
  }

  @Nested
  @DisplayName("Método - venderBolo")
  class MetodoVenderBoloTests {

    Map<String, Field> campos() throws ReflectiveOperationException {
      Class<Bolo> clazz = Bolo.class;

      return Map.of(
          "sabor", clazz.getDeclaredField("sabor"),
          "valor", clazz.getDeclaredField("valor"),
          "quantidadeVendida", clazz.getDeclaredField("quantidadeVendida"),
          "quantidadeEmEstoque", clazz.getDeclaredField("quantidadeEmEstoque")
      );
    }

    @Test
    @DisplayName("Cenário 1 - Valor disponível")
    void cenario1() throws ReflectiveOperationException {
      Class<Bolo> clazz = Bolo.class;

      Method metodo = clazz.getDeclaredMethod("venderBolo", Integer.class);

      Object obj = new Bolo();

      // Case
      campos().get("sabor").set(obj, "Chocolate");
      campos().get("valor").set(obj, 35.0);
      campos().get("quantidadeVendida").set(obj, 10);
      campos().get("quantidadeEmEstoque").set(obj, 60);

      // When
      metodo.invoke(obj, 10);

      // Then
      Assertions.assertEquals(20, campos().get("quantidadeVendida").get(obj));
    }

    @Test
    @DisplayName("Cenário 2 - Valor disponível")
    void cenario2() throws ReflectiveOperationException {
      Class<Bolo> clazz = Bolo.class;

      Method metodo = clazz.getDeclaredMethod("venderBolo", Integer.class);

      Object obj = new Bolo();

      // Case
      campos().get("sabor").set(obj, "Chocolate");
      campos().get("valor").set(obj, 35.0);
      campos().get("quantidadeVendida").set(obj, 15);
      campos().get("quantidadeEmEstoque").set(obj, 40);

      // When
      metodo.invoke(obj, 25);

      // Then
      Assertions.assertEquals(40, campos().get("quantidadeVendida").get(obj));
    }

    @Test
    @DisplayName("Cenário 3 - Valor indisponível")
    void cenario3() throws ReflectiveOperationException {
      Class<Bolo> clazz = Bolo.class;

      Method metodo = clazz.getDeclaredMethod("venderBolo", Integer.class);

      Object obj = new Bolo();

      // Case
      campos().get("sabor").set(obj, "Chocolate");
      campos().get("valor").set(obj, 35.0);
      campos().get("quantidadeVendida").set(obj, 0);
      campos().get("quantidadeEmEstoque").set(obj, 40);

      // When
      metodo.invoke(obj, 60);

      // Then
      Assertions.assertEquals(0, campos().get("quantidadeVendida").get(obj));
    }

    @Test
    @DisplayName("Cenário 4 - Valor indisponível")
    void cenario4() throws ReflectiveOperationException {
      Class<Bolo> clazz = Bolo.class;

      Method metodo = clazz.getDeclaredMethod("venderBolo", Integer.class);

      Object obj = new Bolo();

      // Case
      campos().get("sabor").set(obj, "Chocolate");
      campos().get("valor").set(obj, 35.0);
      campos().get("quantidadeVendida").set(obj, 90);
      campos().get("quantidadeEmEstoque").set(obj, 100);

      // When
      metodo.invoke(obj, 11);

      // Then
      Assertions.assertEquals(101, campos().get("quantidadeVendida").get(obj));
    }

    @Test
    @DisplayName("Cenário 5 - Valor inválido")
    void cenario5() throws ReflectiveOperationException {
      Class<Bolo> clazz = Bolo.class;

      Method metodo = clazz.getDeclaredMethod("venderBolo", Integer.class);

      Object obj = new Bolo();

      // Case
      campos().get("sabor").set(obj, "Chocolate");
      campos().get("valor").set(obj, 35.0);
      campos().get("quantidadeVendida").set(obj, 30);
      campos().get("quantidadeEmEstoque").set(obj, 100);

      // When
      metodo.invoke(obj, -1);

      // Then
      Assertions.assertEquals(30, campos().get("quantidadeVendida").get(obj));
    }

    @Test
    @DisplayName("Cenário 6 - Valor inválido")
    void cenario6() throws ReflectiveOperationException {
      Class<Bolo> clazz = Bolo.class;

      Method metodo = clazz.getDeclaredMethod("venderBolo", Integer.class);

      Object obj = new Bolo();

      // Case
      campos().get("sabor").set(obj, "Chocolate");
      campos().get("valor").set(obj, 35.0);
      campos().get("quantidadeVendida").set(obj, 0);
      campos().get("quantidadeEmEstoque").set(obj, 200);

      // When
      metodo.invoke(obj, -99);

      // Then
      Assertions.assertEquals(0, campos().get("quantidadeVendida").get(obj));
    }
  }

  @Nested
  @DisplayName("Método - aumentarEstoque")
  class MetodoAumentarEstoqueTests {

    Map<String, Field> campos() throws ReflectiveOperationException {
      Class<Bolo> clazz = Bolo.class;

      return Map.of(
          "sabor", clazz.getDeclaredField("sabor"),
          "valor", clazz.getDeclaredField("valor"),
          "quantidadeVendida", clazz.getDeclaredField("quantidadeVendida"),
          "quantidadeEmEstoque", clazz.getDeclaredField("quantidadeEmEstoque")
      );
    }

    @Test
    @DisplayName("Cenário 1 - Valor válido")
    void cenario1() throws ReflectiveOperationException {
      Class<Bolo> clazz = Bolo.class;

      Method metodo = clazz.getDeclaredMethod("aumentarEstoque", Integer.class);

      Object obj = new Bolo();

      // Case
      campos().get("sabor").set(obj, "Morango");
      campos().get("valor").set(obj, 55.0);
      campos().get("quantidadeVendida").set(obj, 10);
      campos().get("quantidadeEmEstoque").set(obj, 10);

      // When
      metodo.invoke(obj, 10);

      // Then
      Assertions.assertEquals(20, campos().get("quantidadeEmEstoque").get(obj));
    }

    @Test
    @DisplayName("Cenário 2 - Valor válido")
    void cenario2() throws ReflectiveOperationException {
      Class<Bolo> clazz = Bolo.class;

      Method metodo = clazz.getDeclaredMethod("aumentarEstoque", Integer.class);

      Object obj = new Bolo();

      // Case
      campos().get("sabor").set(obj, "Morango");
      campos().get("valor").set(obj, 55.0);
      campos().get("quantidadeVendida").set(obj, 2);
      campos().get("quantidadeEmEstoque").set(obj, 20);

      // When
      metodo.invoke(obj, 24);

      // Then
      Assertions.assertEquals(44, campos().get("quantidadeEmEstoque").get(obj));
    }

    @Test
    @DisplayName("Cenário 3 - Valor inválido")
    void cenario3() throws ReflectiveOperationException {
      Class<Bolo> clazz = Bolo.class;

      Method metodo = clazz.getDeclaredMethod("aumentarEstoque", Integer.class);

      Object obj = new Bolo();

      // Case
      campos().get("sabor").set(obj, "Morango");
      campos().get("valor").set(obj, 55.0);
      campos().get("quantidadeVendida").set(obj, 2);
      campos().get("quantidadeEmEstoque").set(obj, 20);

      // When
      metodo.invoke(obj, -9);

      // Then
      Assertions.assertEquals(20, campos().get("quantidadeEmEstoque").get(obj));
    }
  }

  @Nested
  @DisplayName("Método - quantidadeDisponivel")
  class MetodoQuantidadeDisponivelTests {

    Map<String, Field> campos() throws ReflectiveOperationException {
      Class<Bolo> clazz = Bolo.class;

      return Map.of(
          "sabor", clazz.getDeclaredField("sabor"),
          "valor", clazz.getDeclaredField("valor"),
          "quantidadeVendida", clazz.getDeclaredField("quantidadeVendida"),
          "quantidadeEmEstoque", clazz.getDeclaredField("quantidadeEmEstoque")
      );
    }

    @Test
    @DisplayName("Cenário 1")
    void cenario1() throws ReflectiveOperationException {

      Class<Bolo> clazz = Bolo.class;

      Method metodo = clazz.getDeclaredMethod("quantidadeDisponivel");

      Object obj = new Bolo();

      // Case
      campos().get("sabor").set(obj, "Limão");
      campos().get("valor").set(obj, 45.0);
      campos().get("quantidadeVendida").set(obj, 5);
      campos().get("quantidadeEmEstoque").set(obj, 20);

      // When
      Object resposta = metodo.invoke(obj);

      // Then
      Assertions.assertEquals(20, resposta);
    }

    @Test
    @DisplayName("Cenário 2")
    void cenario2() throws ReflectiveOperationException {

      Class<Bolo> clazz = Bolo.class;

      Method metodo = clazz.getDeclaredMethod("quantidadeDisponivel");

      Object obj = new Bolo();

      // Case
      campos().get("sabor").set(obj, "Limão");
      campos().get("valor").set(obj, 45.0);
      campos().get("quantidadeVendida").set(obj, 25);
      campos().get("quantidadeEmEstoque").set(obj, 25);

      // When
      Object resposta = metodo.invoke(obj);

      // Then
      Assertions.assertEquals(25, resposta);
    }

    @Test
    @DisplayName("Cenário 3")
    void cenario3() throws ReflectiveOperationException {

      Class<Bolo> clazz = Bolo.class;

      Method metodo = clazz.getDeclaredMethod("quantidadeDisponivel");

      Object obj = new Bolo();

      // Case
      campos().get("sabor").set(obj, "Limão");
      campos().get("valor").set(obj, 45.0);
      campos().get("quantidadeVendida").set(obj, 35);
      campos().get("quantidadeEmEstoque").set(obj, 87);

      // When
      Object resposta = metodo.invoke(obj);

      // Then
      Assertions.assertEquals(87, resposta);
    }

    @Test
    @DisplayName("Cenário 4")
    void cenario4() throws ReflectiveOperationException {

      Class<Bolo> clazz = Bolo.class;

      Method metodo = clazz.getDeclaredMethod("quantidadeDisponivel");

      Object obj = new Bolo();

      // Case
      campos().get("sabor").set(obj, "Limão");
      campos().get("valor").set(obj, 45.0);
      campos().get("quantidadeVendida").set(obj, 2);
      campos().get("quantidadeEmEstoque").set(obj, 4);

      // When
      Object resposta = metodo.invoke(obj);

      // Then
      Assertions.assertEquals(4, resposta);
    }
  }

  @Nested
  @DisplayName("Método - totalVendido")
  class MetodoTotalVendidoTests {

    Map<String, Field> campos() throws ReflectiveOperationException {
      Class<Bolo> clazz = Bolo.class;

      return Map.of(
          "sabor", clazz.getDeclaredField("sabor"),
          "valor", clazz.getDeclaredField("valor"),
          "quantidadeVendida", clazz.getDeclaredField("quantidadeVendida"),
          "quantidadeEmEstoque", clazz.getDeclaredField("quantidadeEmEstoque")
      );
    }

    @Test
    @DisplayName("Cenário 1")
    void cenario1() throws ReflectiveOperationException {

      Class<Bolo> clazz = Bolo.class;

      Method metodo = clazz.getDeclaredMethod("totalVendido");

      Object obj = new Bolo();

      // Case
      campos().get("sabor").set(obj, "Cenoura");
      campos().get("valor").set(obj, 45.0);
      campos().get("quantidadeVendida").set(obj, 5);
      campos().get("quantidadeEmEstoque").set(obj, 20);

      // When
      Object resposta = metodo.invoke(obj);

      // Then
      Assertions.assertEquals(225.0, (Double) resposta, 0.01);
    }

    @Test
    @DisplayName("Cenário 2")
    void cenario2() throws ReflectiveOperationException {

      Class<Bolo> clazz = Bolo.class;

      Method metodo = clazz.getDeclaredMethod("totalVendido");

      Object obj = new Bolo();

      // Case
      campos().get("sabor").set(obj, "Cenoura");
      campos().get("valor").set(obj, 65.0);
      campos().get("quantidadeVendida").set(obj, 57);
      campos().get("quantidadeEmEstoque").set(obj, 90);

      // When
      Object resposta = metodo.invoke(obj);

      // Then
      Assertions.assertEquals(3705.0, (Double) resposta, 0.01);
    }

    @Test
    @DisplayName("Cenário 3")
    void cenario3() throws ReflectiveOperationException {

      Class<Bolo> clazz = Bolo.class;

      Method metodo = clazz.getDeclaredMethod("totalVendido");

      Object obj = new Bolo();

      // Case
      campos().get("sabor").set(obj, "Cenoura");
      campos().get("valor").set(obj, 22.99);
      campos().get("quantidadeVendida").set(obj, 15);
      campos().get("quantidadeEmEstoque").set(obj, 100);

      // When
      Object resposta = metodo.invoke(obj);

      // Then
      Assertions.assertEquals(344.85, (Double) resposta, 0.01);
    }

    @Test
    @DisplayName("Cenário 4")
    void cenario4() throws ReflectiveOperationException {

      Class<Bolo> clazz = Bolo.class;

      Method metodo = clazz.getDeclaredMethod("totalVendido");

      Object obj = new Bolo();

      // Case
      campos().get("sabor").set(obj, "Cenoura");
      campos().get("valor").set(obj, 49.90);
      campos().get("quantidadeVendida").set(obj, 26);
      campos().get("quantidadeEmEstoque").set(obj, 100);

      // When
      Object resposta = metodo.invoke(obj);

      // Then
      Assertions.assertEquals(1297.4, (Double) resposta, 0.01);
    }
  }
}
