package school.sptech.provider.contacorrente;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.ContaCorrenteFactory;

import java.util.stream.Stream;

public class DepositarProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {

    return Stream.of(
        Arguments.of(ContaCorrenteFactory.build("123", "João", 0.0), 100.0, 100.0),
        Arguments.of(ContaCorrenteFactory.build("456", "Maria", 0.0), 0.0, 0.0),
        Arguments.of(ContaCorrenteFactory.build("789", "Carlos", 0.0), -50.0, 0.0),
        Arguments.of(ContaCorrenteFactory.build("101", "Ana", 100.0), 200.0, 300.0),
        Arguments.of(ContaCorrenteFactory.build("202", "Pedro", 0.0), null, 0.0)
    );
  }
}
