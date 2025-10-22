package school.sptech.provider.contacorrenteplus;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.ContaCorrentePlusFactory;

import java.util.stream.Stream;

public class TrocarPontosProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    return Stream.of(
        Arguments.of(ContaCorrentePlusFactory.build("123", "João", 0.0, 0), 0.0, 0),
        Arguments.of(ContaCorrentePlusFactory.build("456", "Maria", 0.0, 50), 0.0, 50),
        Arguments.of(ContaCorrentePlusFactory.build("789", "Carlos", 0.0, 100), 5.0, 0),
        Arguments.of(ContaCorrentePlusFactory.build("101", "Ana", 200.0, 250), 212.5, 0),
        Arguments.of(ContaCorrentePlusFactory.build("202", "Pedro", 500.0, 999), 549.95, 0),
        Arguments.of(ContaCorrentePlusFactory.build("303", "Luiza", 1000.0, 1000), 1050.0, 0),
        Arguments.of(ContaCorrentePlusFactory.build("404", "Rafael", 1500.0, 5000), 1750.0, 0),
        Arguments.of(ContaCorrentePlusFactory.build("505", "Beatriz", 2000.0, 10000), 2500.0, 0),
        Arguments.of(ContaCorrentePlusFactory.build("606", "Felipe", 3000.0, 15000), 3750.0, 0)
    );
  }
}
