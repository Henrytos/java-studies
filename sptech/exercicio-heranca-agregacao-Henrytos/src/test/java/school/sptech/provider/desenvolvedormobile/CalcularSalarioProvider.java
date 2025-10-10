package school.sptech.provider.desenvolvedormobile;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class CalcularSalarioProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    return Stream.of(
        Arguments.of("Bianca", 32, 68.7, "Android", "Kotlin", 12, 4598.4),
        Arguments.of("Enzo", 47, 65.2, "iOS", "Swift", 18, 6664.4),
        Arguments.of("Mariana", 34, 52.8, "Android", "Java", 15, 4795.2),
        Arguments.of("Gustavo", 45, 59.5, "iOS", "Swift", 13, 5277.5),
        Arguments.of("Sofia", 40, 61.3, "Android", "Kotlin", 16, 5652.0),
        Arguments.of("Leonardo", 50, 44.7, "iOS", "Swift", 10, 4235.0),
        Arguments.of("Valentina", 30, 67.4, "Android", "Java", 19, 5822.0),
        Arguments.of("Joaquim", 37, 52.9, "iOS", "Swift", 14, 4757.3),
        Arguments.of("Alice", 42, 46.5, "Android", "Kotlin", 11, 4153.0),
        Arguments.of("Rafael", 39, 68.1, "iOS", "Swift", 17, 6055.9)
    );
  }
}
