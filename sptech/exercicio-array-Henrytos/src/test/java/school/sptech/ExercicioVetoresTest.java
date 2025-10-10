package school.sptech;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.provider.*;

import java.lang.reflect.Method;

@DisplayName("Exercício 1 - Vetores")
class ExercicioVetoresTest {

  @Nested
  @DisplayName("somar()")
  class SomarTest {

    @ParameterizedTest
    @DisplayName("Deve somar os valores do vetor")
    @ArgumentsSource(SomarProvider.class)
    void testSomar(Integer[] vetor, Integer expected) throws ReflectiveOperationException {

      Class<ExercicioVetores> clazz = ExercicioVetores.class;
      Method method = clazz.getDeclaredMethod("somar", Integer[].class);

      method.setAccessible(true);

      ExercicioVetores obj = clazz.getConstructor().newInstance();
      Object result = method.invoke(obj, (Object) vetor);

      Assertions.assertEquals(expected, result);
    }
  }

  @Nested
  @DisplayName("calcularMedia()")
  class CalcularMediaTest {

    @ParameterizedTest
    @DisplayName("Deve calcular a média aritimética entre os valores do vetor")
    @ArgumentsSource(CalcularMediaProvider.class)
    void testCalcularMedia(Double[] vetor, Double expected) throws ReflectiveOperationException {

      Class<ExercicioVetores> clazz = ExercicioVetores.class;
      Method method = clazz.getDeclaredMethod("calcularMedia", Double[].class);

      method.setAccessible(true);

      ExercicioVetores obj = clazz.getConstructor().newInstance();
      Object result = method.invoke(obj, (Object) vetor);

      Assertions.assertEquals(expected, (Double) result, 0.01);
    }
  }

  @Nested
  @DisplayName("buscarMaiorNumero()")
  class MaiorNumeroTest {

    @ParameterizedTest
    @DisplayName("Deve buscar o maior número dentro do vetor")
    @ArgumentsSource(BuscarMaiorNumeroProvider.class)
    void testBuscarMaiorNumero(Integer[] vetor, Integer expected) throws ReflectiveOperationException {

      Class<ExercicioVetores> clazz = ExercicioVetores.class;
      Method method = clazz.getDeclaredMethod("buscarMaiorNumero", Integer[].class);

      method.setAccessible(true);

      ExercicioVetores obj = clazz.getConstructor().newInstance();
      Object result = method.invoke(obj, (Object) vetor);

      Assertions.assertEquals(expected, result);
    }
  }

  @Nested
  @DisplayName("calcularDecimal()")
  class CalcularDecimalTest {

    @ParameterizedTest
    @DisplayName("Deve calcular o valor em decimal a partir de um binário")
    @ArgumentsSource(CalcularDecimalProvider.class)
    void testCalcularDecimal(Integer[] vetor, Integer expected) throws ReflectiveOperationException {

      Class<ExercicioVetores> clazz = ExercicioVetores.class;
      Method method = clazz.getDeclaredMethod("calcularDecimal", Integer[].class);

      method.setAccessible(true);

      ExercicioVetores obj = clazz.getConstructor().newInstance();
      Object result = method.invoke(obj, (Object) vetor);

      Assertions.assertEquals(expected, result);
    }
  }

  @Nested
  @DisplayName("inverter()")
  class InverterTest {

    @ParameterizedTest
    @DisplayName("Deve interver os valores do vetor")
    @ArgumentsSource(InverterProvider.class)
    void testInverter(Character[] vetor, Character[] expected) throws ReflectiveOperationException {

      Class<ExercicioVetores> clazz = ExercicioVetores.class;
      Method method = clazz.getDeclaredMethod("inverter", Character[].class);

      method.setAccessible(true);

      ExercicioVetores obj = clazz.getConstructor().newInstance();
      Character[] result = (Character[]) method.invoke(obj, (Object) vetor);

      Assertions.assertArrayEquals(expected, result);
    }
  }

  @Nested
  @DisplayName("mesclar()")
  class MesclarTest {

    @ParameterizedTest
    @DisplayName("Deve mesclar os valores do vetor")
    @ArgumentsSource(MesclarProvider.class)
    void testInverter(Integer[] vetor1, Integer[] vetor2, Integer[] expected) throws ReflectiveOperationException {

      Class<ExercicioVetores> clazz = ExercicioVetores.class;
      Method method = clazz.getDeclaredMethod("mesclar", Integer[].class, Integer[].class);

      method.setAccessible(true);

      ExercicioVetores obj = clazz.getConstructor().newInstance();
      Integer[] result = (Integer[]) method.invoke(obj, (Object) vetor1, (Object) vetor2);

      Assertions.assertArrayEquals(expected, result);
    }
  }

  @Nested
  @DisplayName("somarDois()")
  class SomarDoisTest {

    @ParameterizedTest
    @DisplayName("Deve retornar os índices dos números que somados correspondem ao valor alvo")
    @ArgumentsSource(SomarDoisProvider.class)
    void testSomarDois(Integer[] vetor, Integer alvo, Integer[] expected) throws ReflectiveOperationException {

      Class<ExercicioVetores> clazz = ExercicioVetores.class;
      Method method = clazz.getDeclaredMethod("somarDois", Integer[].class, Integer.class);

      method.setAccessible(true);

      ExercicioVetores obj = clazz.getConstructor().newInstance();
      Integer[] result = (Integer[]) method.invoke(obj, vetor, alvo);

      Assertions.assertArrayEquals(expected, result);
    }
  }
}
