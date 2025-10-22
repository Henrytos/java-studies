package school.sptech.provider.contacorrente;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.ContaCorrenteFactory;

import java.util.stream.Stream;

public class SacarProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    return Stream.of(
        Arguments.of(ContaCorrenteFactory.build("123", "João", 200.0), 100.0, 100.0),
        Arguments.of(ContaCorrenteFactory.build("456", "Maria", 150.0), 0.0, 150.0),
        Arguments.of(ContaCorrenteFactory.build("789", "Carlos", 300.0), -50.0, 300.0),
        Arguments.of(ContaCorrenteFactory.build("101", "Ana", 100.0), 200.0, 100.0),
        Arguments.of(ContaCorrenteFactory.build("202", "Pedro", 0.0), 50.0, 0.0),
        Arguments.of(ContaCorrenteFactory.build("303", "Clara", 50.0), 50.0, 0.0)
    );
  }
}
