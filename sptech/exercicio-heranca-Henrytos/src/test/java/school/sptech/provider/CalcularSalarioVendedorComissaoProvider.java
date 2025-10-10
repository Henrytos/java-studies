package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class CalcularSalarioVendedorComissaoProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    return Stream.of(
        Arguments.of(2000.0, 0.75, 1500.0),
        Arguments.of(1500.0, 0.2, 300.0),
        Arguments.of(500.0, 0.1, 50.0),
        Arguments.of(750.0, 0.33, 247.5),
        Arguments.of(2500.0, 0.4, 1000.0)
    );
  }
}
