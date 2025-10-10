package school.sptech.provider.celular;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class CalcularPrecoFinalProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    return Stream.of(
        Arguments.of("Celular A", 1000.0, 0.5, 12, "Marca X", 1600.0),
        Arguments.of("Celular B", 800.0, 0.4, 6, "Marca Y", 1100.0),
        Arguments.of("Celular C", 500.0, 0.3, 0, "Marca Z", 500.0),
        Arguments.of("Celular D", 1200.0, 0.6, 24, "Marca W", 2400.0),
        Arguments.of("Celular E", 1500.0, 0.7, 18, "Marca V", 2400.0)
    );
  }
}
