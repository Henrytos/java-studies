package school.sptech.provider.desenvolvedor;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class CalcularSalarioProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
    return Stream.of(
        Arguments.of("Paulo", 32, 58.7, 1878.4),
        Arguments.of("Lívia", 47, 67.3, 3163.1),
        Arguments.of("Elisa", 34, 45.2, 1536.8),
        Arguments.of("Yasmin", 45, 49.8, 2241.0),
        Arguments.of("Tiago", 40, 64.4, 2576.0),
        Arguments.of("Raul", 50, 52.5, 2625.0),
        Arguments.of("Ian", 30, 43.7, 1311.0),
        Arguments.of("Gustavo", 37, 61.0, 2257.0),
        Arguments.of("Leonardo", 42, 46.1, 1936.2),
        Arguments.of("Rafael", 39, 55.4, 2160.6)
    );
  }
}
