package school.sptech;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.provider.CalcularSalarioVendedorComissaoProvider;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

@DisplayName("Vendedor Comissão")
public class VendedorComissaoTests {

  @Nested
  @DisplayName("1. Atributos")
  class AtributosTests {

    @Test
    @DisplayName("Validar Atributos")
    void cenario1() {
      Class<VendedorComissao> clazz = VendedorComissao.class;

      Assertions.assertAll(
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredField("codigo")),
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredField("nome")),
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredField("vendas")),
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredField("taxa"))
      );
    }
  }

  @Nested
  @DisplayName("2. Métodos")
  class MetodosTests {

    @Test
    @DisplayName("Validar métodos")
    void validarMetodos() {
      Class<VendedorComissao> clazz = VendedorComissao.class;

      Assertions.assertAll(
          () -> Assertions.assertDoesNotThrow(() ->
              clazz.getDeclaredMethod("calcularSalario"))
      );
    }
  }

  @Nested
  @DisplayName("3. Encapsulamento")
  class EncapsulamentoTests {

    @Test
    @DisplayName("Atributos Privados")
    void cenario1() {
      Class<VendedorComissao> clazz = VendedorComissao.class;
      Field[] campos = clazz.getDeclaredFields();

      Stream<Executable> validacoes = Arrays.stream(campos)
          .map((campo) -> () -> Assertions.assertTrue(Modifier.isPrivate(campo.getModifiers())));

      Assertions.assertAll(validacoes);
    }

    @Test
    @DisplayName("Métodos públicos")
    void cenario2() throws ReflectiveOperationException {
      Class<VendedorComissao> clazz = VendedorComissao.class;
      Method cobrarPassagem = clazz.getDeclaredMethod("calcularSalario");

      Assertions.assertAll(
          () -> Assertions.assertTrue(Modifier.isPublic(cobrarPassagem.getModifiers()))
      );
    }

    @Test
    @DisplayName("Atributos devem possuir getters e setters")
    void cenario3() {
      Class<VendedorComissao> clazz = VendedorComissao.class;
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

  @Nested
  @DisplayName("4. Método - calcularSalario")
  class CalcularSalarioTests {

    Map<String, Field> campos() throws ReflectiveOperationException {
      Class<VendedorComissao> clazz = VendedorComissao.class;

      Field codigo = clazz.getDeclaredField("codigo");
      codigo.trySetAccessible();

      Field nome = clazz.getDeclaredField("nome");
      nome.trySetAccessible();

      Field vendas = clazz.getDeclaredField("vendas");
      vendas.trySetAccessible();

      Field taxa = clazz.getDeclaredField("taxa");
      taxa.trySetAccessible();

      return Map.of("codigo", codigo, "nome", nome, "vendas", vendas, "taxa", taxa);
    }

    @ParameterizedTest
    @ArgumentsSource(CalcularSalarioVendedorComissaoProvider.class)
    @DisplayName("Cenário 1")
    void cenario1(double vendas, double taxa, double salario) throws ReflectiveOperationException {
      Class<VendedorComissao> cVendedorComissao = VendedorComissao.class;
      Method metodo = cVendedorComissao.getDeclaredMethod("calcularSalario");

      Object obj = new VendedorComissao();

      // Case
      campos().get("codigo").set(obj, 1);
      campos().get("nome").set(obj, "Paulo");
      campos().get("vendas").set(obj, vendas);
      campos().get("taxa").set(obj, taxa);

      // When
      Object retorno = metodo.invoke(obj);

      // Then
      Assertions.assertAll(
          () -> Assertions.assertEquals(salario, (Double) retorno, 0.01)
      );
    }
  }
}
