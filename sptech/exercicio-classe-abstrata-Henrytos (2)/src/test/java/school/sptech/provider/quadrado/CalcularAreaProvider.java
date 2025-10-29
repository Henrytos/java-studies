package school.sptech.provider.quadrado;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class CalcularAreaProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    return Stream.of(
        Arguments.of("Amarelo", 2, 4.0, 16.0),
        Arguments.of("Azul", 3, 5.0, 25.0),
        Arguments.of("Vermelho", 4, 6.0, 36.0),
        Arguments.of("Verde", 5, 7.0, 49.0),
        Arguments.of("Preto", 6, 8.0, 64.0),
        Arguments.of("Branco", 7, 9.0, 81.0),
        Arguments.of("Rosa", 8, 10.0, 100.0)
    );
  }
}
