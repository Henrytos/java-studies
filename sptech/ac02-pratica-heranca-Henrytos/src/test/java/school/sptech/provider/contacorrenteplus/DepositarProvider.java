package school.sptech.provider.contacorrenteplus;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.ContaCorrentePlusFactory;

import java.util.stream.Stream;

public class DepositarProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(ContaCorrentePlusFactory.build("123", "João", 0.0, 0), 100.0, 100.0, 10),
                Arguments.of(ContaCorrentePlusFactory.build("456", "Maria", 0.0, 0), 0.0, 0.0, 0),
                Arguments.of(ContaCorrentePlusFactory.build("789", "Carlos", 0.0, 0), -50.0, 0.0, 0),
                Arguments.of(ContaCorrentePlusFactory.build("101", "Ana", 100.0, 0), 200.0, 300.0, 20),
                Arguments.of(ContaCorrentePlusFactory.build("202", "Pedro", 0.0, 0), null, 0.0, 0),
                Arguments.of(ContaCorrentePlusFactory.build("303", "Luiza", 0.0, 950), 100.0, 100.0, 960),
                Arguments.of(ContaCorrentePlusFactory.build("404", "Rafael", 0.0, 1000), 100.0, 100.0, 1020),
                Arguments.of(ContaCorrentePlusFactory.build("505", "Beatriz", 0.0, 9999), 100.0, 100.0, 10019),
                Arguments.of(ContaCorrentePlusFactory.build("606", "Felipe", 0.0, 10000), 100.0, 100.0, 10050),
                Arguments.of(ContaCorrentePlusFactory.build("707", "Sofia", 500.0, 5000), 250.0, 750.0, 5050),
                Arguments.of(ContaCorrentePlusFactory.build("808", "Gustavo", 200.0, 15000), 300.0, 500.0, 15150)
        );
    }
}
