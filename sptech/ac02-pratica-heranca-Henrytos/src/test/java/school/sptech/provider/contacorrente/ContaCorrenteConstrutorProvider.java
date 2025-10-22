package school.sptech.provider.contacorrente;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class ContaCorrenteConstrutorProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    return Stream.of(
        Arguments.of("123", "João"),
        Arguments.of("456", "Maria"),
        Arguments.of("789", "Carlos"),
        Arguments.of("101", "Ana"),
        Arguments.of("202", "Pedro")
    );
  }
}
