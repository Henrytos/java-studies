package school.sptech.provider.banco;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class BancoConstrutorProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    return Stream.of(
        Arguments.of("Banco do Brasil"),
        Arguments.of("Caixa Econômica Federal"),
        Arguments.of("Itaú Unibanco"),
        Arguments.of("Bradesco"),
        Arguments.of("Santander")
    );
  }
}
