package school.sptech.provider.circulo;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class CalcularAreaProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    return Stream.of(
        Arguments.of("Amarelo", 2, 4.0, 50.2654),
        Arguments.of("Azul", 3, 5.0, 78.5398),
        Arguments.of("Vermelho", 4, 6.0, 113.0973),
        Arguments.of("Verde", 5, 7.0, 153.9380),
        Arguments.of("Preto", 6, 8.0, 201.0619),
        Arguments.of("Branco", 7, 9.0, 254.4690),
        Arguments.of("Rosa", 8, 10.0, 314.1593),
        Arguments.of("Roxo", 9, 11.0, 380.1327),
        Arguments.of("Laranja", 10, 12.0, 452.3893),
        Arguments.of("Cinza", 11, 13.0, 530.9292)
    );
  }
}
