package school.sptech.provider.retangulo;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class CalcularAreaProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    return Stream.of(
        Arguments.of("Azul", 3, 5.0, 8.0, 40.0),
        Arguments.of("Vermelho", 4, 6.0, 9.0, 54.0),
        Arguments.of("Verde", 5, 7.0, 10.0, 70.0),
        Arguments.of("Preto", 6, 8.0, 11.0, 88.0),
        Arguments.of("Branco", 7, 9.0, 12.0, 108.0),
        Arguments.of("Rosa", 8, 10.0, 13.0, 130.0)
    );
  }
}
