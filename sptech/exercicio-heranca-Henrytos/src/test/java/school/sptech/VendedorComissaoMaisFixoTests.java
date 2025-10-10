package school.sptech;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.provider.CalcularSalarioVendedorComissaoMaisFixoProvider;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

@DisplayName("Vendedor Comissão Mais Fixo")
public class VendedorComissaoMaisFixoTests {

  @Nested
  @DisplayName("1. Atributos")
  class AtributosTests {

    @Test
    @DisplayName("Validar Atributos")
    void cenario1() {
      Class<VendedorComissaoMaisFixo> clazz = VendedorComissaoMaisFixo.class;

      Assertions.assertAll(
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredField("salarioFixo"))
      );
    }
  }

  @Nested
  @DisplayName("2. Métodos")
  class MetodosTests {

    @Test
    @DisplayName("Validar métodos")
    void validarMetodos() {
      Class<VendedorComissaoMaisFixo> clazz = VendedorComissaoMaisFixo.class;

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
      Class<VendedorComissaoMaisFixo> clazz = VendedorComissaoMaisFixo.class;
      Field[] campos = clazz.getDeclaredFields();

      Stream<Executable> validacoes = Arrays.stream(campos)
          .map((campo) -> () -> Assertions.assertTrue(Modifier.isPrivate(campo.getModifiers())));

      Assertions.assertAll(validacoes);
    }

    @Test
    @DisplayName("Métodos públicos")
    void cenario2() throws ReflectiveOperationException {
      Class<VendedorComissaoMaisFixo> clazz = VendedorComissaoMaisFixo.class;
      Method cobrarPassagem = clazz.getDeclaredMethod("calcularSalario");

      Assertions.assertAll(
          () -> Assertions.assertTrue(Modifier.isPublic(cobrarPassagem.getModifiers()))
      );
    }

    @Test
    @DisplayName("Atributos devem possuir getters e setters")
    void cenario3() {
      Class<VendedorComissaoMaisFixo> clazz = VendedorComissaoMaisFixo.class;
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
  @DisplayName("4. Herança")
  class HerancaTests {

    @Test
    void cenario1() {
      Assertions.assertTrue(VendedorComissao.class.isAssignableFrom(VendedorComissaoMaisFixo.class));
    }
  }

  @Nested
  @DisplayName("5. Método - calcularSalario")
  class CalcularSalarioTests {

    Map<String, Field> campos() throws ReflectiveOperationException {
      Class<VendedorComissaoMaisFixo> clazz = VendedorComissaoMaisFixo.class;
      Class<VendedorComissao> superClazz = VendedorComissao.class;

      Field codigo = superClazz.getDeclaredField("codigo");
      codigo.trySetAccessible();

      Field nome = superClazz.getDeclaredField("nome");
      nome.trySetAccessible();

      Field vendas = superClazz.getDeclaredField("vendas");
      vendas.trySetAccessible();

      Field taxa = superClazz.getDeclaredField("taxa");
      taxa.trySetAccessible();

      Field salarioFixo = clazz.getDeclaredField("salarioFixo");
      salarioFixo.trySetAccessible();

      return Map.of("codigo", codigo, "nome", nome, "vendas", vendas, "taxa", taxa, "salarioFixo", salarioFixo);
    }

    @ParameterizedTest
    @ArgumentsSource(CalcularSalarioVendedorComissaoMaisFixoProvider.class)
    @DisplayName("Cenário 1")
    void cenario1(double vendas, double taxa, double salarioFixo, double salario) throws ReflectiveOperationException {
      Class<VendedorComissaoMaisFixo> cVendedorComissaoMaisFixo = VendedorComissaoMaisFixo.class;
      Method metodo = cVendedorComissaoMaisFixo.getMethod("calcularSalario");

      Object obj = new VendedorComissaoMaisFixo();

      // Case
      campos().get("codigo").set(obj, 1);
      campos().get("nome").set(obj, "Paulo");
      campos().get("vendas").set(obj, vendas);
      campos().get("taxa").set(obj, taxa);
      campos().get("salarioFixo").set(obj, salarioFixo);

      // When
      Object retorno = metodo.invoke(obj);

      // Then
      Assertions.assertAll(
          () -> Assertions.assertEquals(salario, (Double) retorno, 0.01)
      );
    }
  }
}
