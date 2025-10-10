package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class CalcularSalarioVendedorComissaoMaisFixoProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    return Stream.of(
        Arguments.of(1000, 0.5, 1000.0, 1500.0),
        Arguments.of(2000, 0.25, 500.0, 1000.0),
        Arguments.of(1500, 0.1, 200.0, 350.0),
        Arguments.of(800, 0.75, 400.0, 1000.0),
        Arguments.of(500, 0.2, 1000.0, 1100.0)
    );
  }
}
