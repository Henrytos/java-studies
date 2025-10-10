package school.sptech;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.provider.CalcularDiaDosPaisProvider;
import school.sptech.provider.CalcularIdadeProvider;
import school.sptech.provider.FormatarDataHoraProvider;
import school.sptech.provider.GerarReunioesSemanaisProvider;
import school.sptech.provider.IsFinalDeSemanaProvider;
import school.sptech.provider.IsPassadoProvider;
import school.sptech.provider.ProximoDiaUtilProvider;

@DisplayName("Exercício - Data e Hora")
class ExercicioDataHoraTest {

    @Nested
    @DisplayName("Método - isPassado")
    class MetodoIsPassado {

        @ParameterizedTest
        @DisplayName("Deve retornar se a data já passou")
        @ArgumentsSource(IsPassadoProvider.class)
        void testisPassado(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim,
              Boolean expected)
              throws ReflectiveOperationException {

            Class<ExercicioDataHora> clazz = ExercicioDataHora.class;
            Method method = clazz.getDeclaredMethod("isPassado", LocalDateTime.class,
                  LocalDateTime.class);
            method.trySetAccessible();
            ExercicioDataHora obj = clazz.getConstructor().newInstance();
            Object result = method.invoke(obj, dataHoraInicio, dataHoraFim);

            Assertions.assertEquals(expected, result);
        }
    }

    @Nested
    @DisplayName("Método - calcularIdade")
    class MetodoCalcularIdade {

        @ParameterizedTest
        @DisplayName("Deve calcular a idade corretamente")
        @ArgumentsSource(CalcularIdadeProvider.class)
        void testCalcularIdade(String aniversario, String atual, Integer expected)
              throws ReflectiveOperationException {

            LocalDate dataAniversario = LocalDate.parse(aniversario);
            LocalDate dataAtual = LocalDate.parse(atual);

            Class<ExercicioDataHora> clazz = ExercicioDataHora.class;
            Method method = clazz.getDeclaredMethod("calcularIdade", LocalDate.class,
                  LocalDate.class);
            method.trySetAccessible();
            ExercicioDataHora obj = clazz.getConstructor().newInstance();
            Object result = method.invoke(obj, dataAniversario, dataAtual);

            Assertions.assertEquals(expected, result);
        }
    }

    @Nested
    @DisplayName("Método - isFinalDeSemana")
    class MetodoIsFinalDeSemana {

        @ParameterizedTest
        @DisplayName("Deve identificar corretamente finais de semana")
        @ArgumentsSource(IsFinalDeSemanaProvider.class)
        void testisFinalDeSemana(LocalDate data, Boolean expected)
              throws ReflectiveOperationException {

            Class<ExercicioDataHora> clazz = ExercicioDataHora.class;
            Method method = clazz.getDeclaredMethod("isFinalDeSemana", LocalDate.class);
            method.trySetAccessible();
            ExercicioDataHora obj = clazz.getConstructor().newInstance();
            Object result = method.invoke(obj, data);

            Assertions.assertEquals(expected, result);
        }
    }

    @Nested
    @DisplayName("Método - proximoDiaUtil")
    class MetodoProximoDiaUtil {

        @ParameterizedTest
        @DisplayName("Deve retornar o próximo dia útil")
        @ArgumentsSource(ProximoDiaUtilProvider.class)
        void testProximoDiaUtil(LocalDate data, LocalDate expected)
              throws ReflectiveOperationException {

            Class<ExercicioDataHora> clazz = ExercicioDataHora.class;
            Method method = clazz.getDeclaredMethod("proximoDiaUtil", LocalDate.class);
            method.trySetAccessible();
            ExercicioDataHora obj = clazz.getConstructor().newInstance();
            Object result = method.invoke(obj, data);

            Assertions.assertEquals(expected, result);
        }
    }

    @Nested
    @DisplayName("Método - formatarDataHora")
    class MetodoFormatarDataHora {

        @ParameterizedTest
        @DisplayName("Deve formatar data e hora com base no enunciado")
        @ArgumentsSource(FormatarDataHoraProvider.class)
        void testFormatarDataHora(LocalDateTime dataHora, String expected)
              throws ReflectiveOperationException {

            Class<ExercicioDataHora> clazz = ExercicioDataHora.class;
            Method method = clazz.getDeclaredMethod("formatarDataHora", LocalDateTime.class);
            method.trySetAccessible();
            ExercicioDataHora obj = clazz.getConstructor().newInstance();
            Object result = method.invoke(obj, dataHora);

            Assertions.assertEquals(expected, result);
        }
    }

    @Nested
    @DisplayName("Método - gerarReunioesSemanais")
    class MetodoGerarReunioesSemanais {

        @ParameterizedTest
        @DisplayName("Deve gerar datas semanais corretamente")
        @ArgumentsSource(GerarReunioesSemanaisProvider.class)
        void testGerarReunioesSemanais(LocalDate dataComeco, LocalDate dataFim,
              List<Integer> diasDaSemana,
              List<LocalDateTime> expected)
              throws ReflectiveOperationException {

            Class<ExercicioDataHora> clazz = ExercicioDataHora.class;
            Method method = clazz.getDeclaredMethod("gerarReunioesSemanais", LocalDate.class,
                  LocalDate.class, List.class);
            method.trySetAccessible();
            ExercicioDataHora obj = clazz.getConstructor().newInstance();
            Object result = method.invoke(obj, dataComeco, dataFim, diasDaSemana);

            Assertions.assertIterableEquals(expected, (List<?>) result);
        }
    }

    @Nested
    @DisplayName("Metodo - calcularDiaDosPais")
    class MetodoCalcularDiaDosPais {

        @ParameterizedTest
        @DisplayName("Deve calcular o dia dos pais corretamente")
        @ArgumentsSource(CalcularDiaDosPaisProvider.class)
        void testDiaDosPais(Integer ano, LocalDate expected)
              throws ReflectiveOperationException {

            Class<ExercicioDataHora> clazz = ExercicioDataHora.class;
            Method method = clazz.getDeclaredMethod("calcularDiaDosPais", Integer.class);
            method.trySetAccessible();
            ExercicioDataHora obj = clazz.getConstructor().newInstance();
            Object result = method.invoke(obj, ano);

            Assertions.assertEquals(expected, result);
        }
    }
}
