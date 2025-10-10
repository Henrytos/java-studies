package school.sptech.ex3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

@DisplayName("Atividade 3 - Empresa")
public class FuncionarioTests {

  @Nested
  @DisplayName("Atributos")
  class AtributosTests {

    @Test
    @DisplayName("Validar atributos")
    void validarAtributos() {
      Class<Funcionario> clazz = Funcionario.class;

      Assertions.assertAll(
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredField("nome")),
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredField("cargo")),
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredField("salario"))
      );
    }
  }

  @Nested
  @DisplayName("Métodos")
  class MetodosTests {

    @Test
    @DisplayName("Validar métodos")
    void validarMetodos() {
      Class<Funcionario> clazz = Funcionario.class;

      Assertions.assertAll(
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredMethod("reajustarSalario", Integer.class)),
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredMethod("calcularValorHora")),
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredMethod("calcularHoraExtra", Integer.class, Integer.class))
      );
    }
  }

  @Nested
  @DisplayName("Método - reajustarSalario")
  class MetodoReajustarSalarioTests {

    Map<String, Field> campos() throws ReflectiveOperationException {
      Class<Funcionario> clazz = Funcionario.class;

      return Map.of(
          "nome", clazz.getDeclaredField("nome"),
          "cargo", clazz.getDeclaredField("cargo"),
          "salario", clazz.getDeclaredField("salario")
      );
    }

    @Test
    @DisplayName("Cenário - 1")
    void cenario1() throws ReflectiveOperationException {
      Class<Funcionario> clazz = Funcionario.class;

      Method metodo = clazz.getDeclaredMethod("reajustarSalario", Integer.class);

      Object obj = new Funcionario();

      // Case
      campos().get("nome").set(obj, "William");
      campos().get("cargo").set(obj, "Analista desenvolvedor");
      campos().get("salario").set(obj, 10_000.00);

      // When
      metodo.invoke(obj, 5);

      // Then
      Assertions.assertEquals(10_500.00, (Double) campos().get("salario").get(obj), 0.01);
    }

    @Test
    @DisplayName("Cenário - 2")
    void cenario2() throws ReflectiveOperationException {
      Class<Funcionario> clazz = Funcionario.class;

      Method metodo = clazz.getDeclaredMethod("reajustarSalario", Integer.class);

      Object obj = new Funcionario();

      // Case
      campos().get("nome").set(obj, "Diego");
      campos().get("cargo").set(obj, "Analista desenvolvedor");
      campos().get("salario").set(obj, 8_000.00);

      // When
      metodo.invoke(obj, 5);

      // Then
      Assertions.assertEquals(8_400.00, (Double) campos().get("salario").get(obj), 0.01);
    }

    @Test
    @DisplayName("Cenário - 3")
    void cenario3() throws ReflectiveOperationException {
      Class<Funcionario> clazz = Funcionario.class;

      Method metodo = clazz.getDeclaredMethod("reajustarSalario", Integer.class);

      Object obj = new Funcionario();

      // Case
      campos().get("nome").set(obj, "Giu");
      campos().get("cargo").set(obj, "Analista desenvolvedor");
      campos().get("salario").set(obj, 2_800.00);

      // When
      metodo.invoke(obj, 58);

      // Then
      Assertions.assertEquals(4_424.00, (Double) campos().get("salario").get(obj), 0.01);
    }
  }

  @Nested
  @DisplayName("Método - calcularValorHora")
  class MetodoCalcularValorHoraTests {

    Map<String, Field> campos() throws ReflectiveOperationException {
      Class<Funcionario> clazz = Funcionario.class;

      return Map.of(
          "nome", clazz.getDeclaredField("nome"),
          "cargo", clazz.getDeclaredField("cargo"),
          "salario", clazz.getDeclaredField("salario")
      );
    }

    @Test
    @DisplayName("Cenário - 1")
    void cenario1() throws ReflectiveOperationException {

      Class<Funcionario> clazz = Funcionario.class;

      Method metodo = clazz.getDeclaredMethod("calcularValorHora");

      Object obj = new Funcionario();

      // Case
      campos().get("nome").set(obj, "William");
      campos().get("cargo").set(obj, "Analista desenvolvedor");
      campos().get("salario").set(obj, 10_000.00);

      // When
      Object resposta = metodo.invoke(obj);

      // Then
      Assertions.assertEquals(45.454545455, (Double) resposta, 0.01);
    }

    @Test
    @DisplayName("Cenário - 2")
    void cenario2() throws ReflectiveOperationException {

      Class<Funcionario> clazz = Funcionario.class;

      Method metodo = clazz.getDeclaredMethod("calcularValorHora");

      Object obj = new Funcionario();

      // Case
      campos().get("nome").set(obj, "Diego");
      campos().get("cargo").set(obj, "Analista desenvolvedor");
      campos().get("salario").set(obj, 8_000.00);

      // When
      Object resposta = metodo.invoke(obj);

      // Then
      Assertions.assertEquals(36.363636364, (Double) resposta, 0.01);
    }

    @Test
    @DisplayName("Cenário - 3")
    void cenario3() throws ReflectiveOperationException {

      Class<Funcionario> clazz = Funcionario.class;

      Method metodo = clazz.getDeclaredMethod("calcularValorHora");

      Object obj = new Funcionario();

      // Case
      campos().get("nome").set(obj, "Rafael");
      campos().get("cargo").set(obj, "Analista desenvolvedor");
      campos().get("salario").set(obj, 2_800.00);

      // When
      Object resposta = metodo.invoke(obj);

      // Then
      Assertions.assertEquals(12.727272727, (Double) resposta, 0.01);
    }
  }

  @Nested
  @DisplayName("Método - calcularHoraExtra")
  class MetodoCalcularHoraExtraTests {

    Map<String, Field> campos() throws ReflectiveOperationException {
      Class<Funcionario> clazz = Funcionario.class;

      return Map.of(
          "nome", clazz.getDeclaredField("nome"),
          "cargo", clazz.getDeclaredField("cargo"),
          "salario", clazz.getDeclaredField("salario")
      );
    }

    @Test
    @DisplayName("Cenário - 1")
    void cenario1() throws ReflectiveOperationException {

      Class<Funcionario> clazz = Funcionario.class;

      Method metodo = clazz.getDeclaredMethod("calcularHoraExtra", Integer.class, Integer.class);

      Object obj = new Funcionario();

      // Case
      campos().get("nome").set(obj, "William");
      campos().get("cargo").set(obj, "Analista desenvolvedor");
      campos().get("salario").set(obj, 10_000.00);

      // When
      Object resposta = metodo.invoke(obj, 20, 15);

      // Then
      Assertions.assertEquals(1045.454545465, (Double) resposta, 0.01);
    }

    @Test
    @DisplayName("Cenário - 2")
    void cenario2() throws ReflectiveOperationException {

      Class<Funcionario> clazz = Funcionario.class;

      Method metodo = clazz.getDeclaredMethod("calcularHoraExtra", Integer.class, Integer.class);

      Object obj = new Funcionario();

      // Case
      campos().get("nome").set(obj, "Diego");
      campos().get("cargo").set(obj, "Analista desenvolvedor");
      campos().get("salario").set(obj, 8_000.00);

      // When
      Object resposta = metodo.invoke(obj, 30, 20);

      // Then
      Assertions.assertEquals(1309.090909091, (Double) resposta, 0.01);
    }

    @Test
    @DisplayName("Cenário - 3")
    void cenario3() throws ReflectiveOperationException {

      Class<Funcionario> clazz = Funcionario.class;

      Method metodo = clazz.getDeclaredMethod("calcularHoraExtra", Integer.class, Integer.class);

      Object obj = new Funcionario();

      // Case
      campos().get("nome").set(obj, "Rafael");
      campos().get("cargo").set(obj, "Analista desenvolvedor");
      campos().get("salario").set(obj, 2_800.00);

      // When
      Object resposta = metodo.invoke(obj, 5, 10);

      // Then
      Assertions.assertEquals(69.999999999, (Double) resposta, 0.01);
    }
  }
}
