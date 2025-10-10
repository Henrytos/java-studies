package school.sptech;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.provider.CalcularFatorialProvider;
import school.sptech.provider.CalcularMediaProvider;
import school.sptech.provider.CalcularPotenciaProvider;
import school.sptech.provider.CalcularTrocoEmBalaProvider;
import school.sptech.provider.MaiorNumeroProvider;
import school.sptech.provider.VerificarMaioridadeProvider;
import school.sptech.provider.VerificarPalindromoProvider;
import school.sptech.provider.VerificarPrimoProvider;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Exercício 1 - Métodos")
class ExercicioMetodosTest {

  @Nested
  @DisplayName("calcularMedia()")
  class CalcularMediaTest {

    @ParameterizedTest
    @DisplayName("Deve calcular a média de 3 valores")
    @ArgumentsSource(CalcularMediaProvider.class)
    void testCalcularMedia(Double valor1, Double valor2, Double valor3, Double expected) throws ReflectiveOperationException {

      Class<ExercicioMetodos> clazz = ExercicioMetodos.class;
      Method method = clazz.getDeclaredMethod("calcularMedia", Double.class, Double.class, Double.class);

      method.setAccessible(true);

      ExercicioMetodos obj = new ExercicioMetodos();
      Object result = method.invoke(obj, valor1, valor2, valor3);

      assertEquals(expected, (Double) result, 0.01);
    }
  }

  @Nested
  @DisplayName("maiorNumero()")
  class MaiorNumeroTest {

    @ParameterizedTest
    @DisplayName("Deve retornar o maior número entre 3 valores")
    @ArgumentsSource(MaiorNumeroProvider.class)
    void testMaiorNumero(Integer valor1, Integer valor2, Integer valor3, Integer expected) throws ReflectiveOperationException {

      Class<ExercicioMetodos> clazz = ExercicioMetodos.class;
      Method method = clazz.getDeclaredMethod("maiorNumero", Integer.class, Integer.class, Integer.class);

      method.setAccessible(true);

      ExercicioMetodos obj = new ExercicioMetodos();
      Object result = method.invoke(obj, valor1, valor2, valor3);

      assertEquals(expected, (Integer) result);
    }
  }

  @Nested
  @DisplayName("calcularFatorial()")
  class CalcularFatorialTest {

    @ParameterizedTest
    @DisplayName("Deve calcular o fatorial de um número")
    @ArgumentsSource(CalcularFatorialProvider.class)
    void testCalcularFatorial(Integer valor, Integer expected) throws ReflectiveOperationException {

      Class<ExercicioMetodos> clazz = ExercicioMetodos.class;
      Method method = clazz.getDeclaredMethod("calcularFatorial", Integer.class);

      method.setAccessible(true);

      ExercicioMetodos obj = new ExercicioMetodos();
      Object result = method.invoke(obj, valor);

      assertEquals(expected, (Integer) result);
    }
  }

  @Nested
  @DisplayName("verificarPrimo()")
  class VerificarPrimoTest {

    @ParameterizedTest
    @DisplayName("Deve verificar se um número é primo")
    @ArgumentsSource(VerificarPrimoProvider.class)
    void testVerificarPrimo(Integer valor, Boolean expected) throws ReflectiveOperationException {

      Class<ExercicioMetodos> clazz = ExercicioMetodos.class;
      Method method = clazz.getDeclaredMethod("verificarPrimo", Integer.class);

      method.setAccessible(true);

      ExercicioMetodos obj = new ExercicioMetodos();
      Object result = method.invoke(obj, valor);

      assertEquals(expected, result);
    }
  }

  @Nested
  @DisplayName("calcularPotencia()")
  class CalcularPotenciaTest {

    @ParameterizedTest
    @DisplayName("Deve calcular a potência de um número")
    @ArgumentsSource(CalcularPotenciaProvider.class)
    void testCalcularPotencia(Integer base, Integer expoente, Integer expected) throws ReflectiveOperationException {

      Class<ExercicioMetodos> clazz = ExercicioMetodos.class;
      Method method = clazz.getDeclaredMethod("calcularPotencia", Integer.class, Integer.class);

      method.setAccessible(true);

      ExercicioMetodos obj = new ExercicioMetodos();
      Object result = method.invoke(obj, base, expoente);

      assertEquals(expected, (Integer) result);
    }
  }

  @Nested
  @DisplayName("verificarMaioridade()")
  class VerificarMaioridadeTest {

    @ParameterizedTest
    @DisplayName("Deve verificar se uma pessoa é maior de idade")
    @ArgumentsSource(VerificarMaioridadeProvider.class)
    void testVerificarMaioridade(Integer idade, Boolean expected) throws ReflectiveOperationException {

      Class<ExercicioMetodos> clazz = ExercicioMetodos.class;
      Method method = clazz.getDeclaredMethod("verificarMaioridade", Integer.class);

      method.setAccessible(true);

      ExercicioMetodos obj = new ExercicioMetodos();
      Object result = method.invoke(obj, idade);

      assertEquals(expected, result);
    }
  }

  @Nested
  @DisplayName("calcularTrocoEmBalas()")
  class CalcularTrocoEmBalasTest {

    @ParameterizedTest
    @DisplayName("Deve calcular o troco em balas")
    @ArgumentsSource(CalcularTrocoEmBalaProvider.class)
    void testCalcularTrocoEmBalas(Double valorCompra, Double valorRecebido, Integer expected) throws ReflectiveOperationException {

      Class<ExercicioMetodos> clazz = ExercicioMetodos.class;
      Method method = clazz.getDeclaredMethod("calcularTrocoEmBalas", Double.class, Double.class);

      method.setAccessible(true);

      ExercicioMetodos obj = new ExercicioMetodos();
      Object result = method.invoke(obj, valorCompra, valorRecebido);

      assertEquals(expected, (Integer) result);
    }
  }

  @Nested
  @DisplayName("verificarPalindromo()")
  class VerificarPalindromoTest {

    @ParameterizedTest
    @DisplayName("Deve verificar se uma palavra é um palíndromo")
    @ArgumentsSource(VerificarPalindromoProvider.class)
    void testVerificarPalindromo(String palavra, Boolean expected) throws ReflectiveOperationException {

      Class<ExercicioMetodos> clazz = ExercicioMetodos.class;
      Method method = clazz.getDeclaredMethod("verificarPalindromo", String.class);

      method.setAccessible(true);

      ExercicioMetodos obj = new ExercicioMetodos();
      Object result = method.invoke(obj, palavra);

      assertEquals(expected, result);
    }
  }
}
