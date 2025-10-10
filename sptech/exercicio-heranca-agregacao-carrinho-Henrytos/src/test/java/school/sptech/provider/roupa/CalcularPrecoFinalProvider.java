package school.sptech.provider.roupa;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class CalcularPrecoFinalProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    return Stream.of(
        Arguments.of("Roupa A", 200.0, 1.0, "Vermelho", 42.0, "algodão", 200.0),
        Arguments.of("Roupa B", 300.0, 1.2, "Preto", 40.0, "couro", 255.0),
        Arguments.of("Roupa C", 150.0, 0.8, "Azul", 38.0, "jeans", 150.0),
        Arguments.of("Roupa D", 500.0, 1.5, "Marrom", 44.0, "couro", 425.0),
        Arguments.of("Roupa E", 100.0, 0.5, "Branco", 36.0, "seda", 100.0)
    );
  }
}
