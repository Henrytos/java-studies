package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class CalcularPotenciaProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(1, 1, 1),
                Arguments.of(5, 0, 1),
                Arguments.of(5, 1, 5),
                Arguments.of(2, 3, 8),
                Arguments.of(3, 4, 81),
                Arguments.of(10, 3, 1000),
                Arguments.of(2, 10, 1024),
                Arguments.of(100, 2, 10000),
                Arguments.of(4, 4, 256),
                Arguments.of(0,1,0)
        );
    }
}
