package school.sptech.provider.banco;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.ContaCorrenteFactory;
import school.sptech.factory.ContaCorrentePlusFactory;

import java.util.List;
import java.util.stream.Stream;

public class BuscarContasPlusProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        Object c1 = ContaCorrentePlusFactory.build("007", "Mario", 0.0, 0);
        Object c2 = ContaCorrentePlusFactory.build("005", "Carlos", 0.0, 0);
        Object c3 = ContaCorrentePlusFactory.build("001", "Ana", 0.0, 0);
        Object c4 = ContaCorrentePlusFactory.build("002", "Beatriz", 0.0, 0);
        Object c5 = ContaCorrenteFactory.build("003", "João", 0.0);
        Object c6 = ContaCorrenteFactory.build("004", "Lucia", 0.0);
        Object c7 = ContaCorrenteFactory.build("006", "Fernando", 0.0);
        Object c8 = ContaCorrenteFactory.build("008", "Patrícia", 0.0);
        Object c9 = ContaCorrenteFactory.build("009", "Gabriel", 0.0);
        Object c10 = ContaCorrenteFactory.build("023", "Renata", 0.0);

        return Stream.of(
                Arguments.of(List.of(c1, c2, c3), List.of(c1, c2, c3)),
                Arguments.of(List.of(c1, c2, c3, c4, c5), List.of(c1, c2, c3, c4)),
                Arguments.of(List.of(c6, c7, c8, c9, c10), List.of()),
                Arguments.of(List.of(c1, c10, c2, c9, c4), List.of(c1, c2, c4)),
                Arguments.of(List.of(), List.of())
        );
    }
}
