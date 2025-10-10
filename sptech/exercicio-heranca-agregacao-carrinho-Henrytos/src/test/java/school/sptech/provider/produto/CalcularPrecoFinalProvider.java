package school.sptech.provider.produto;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class CalcularPrecoFinalProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    return Stream.of(
        Arguments.of("Produto A", 100.0, 1.5, 100.0),
        Arguments.of("Produto B", 200.0, 2.0, 200.0),
        Arguments.of("Produto C", 50.0, 0.5, 50.0),
        Arguments.of("Produto D", 0.0, 0.0, 0.0),
        Arguments.of("Produto E", 150.0, 3.0, 150.0)
    );
  }
}
