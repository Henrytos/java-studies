package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class CalcularTrocoEmBalaProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(10.0,10.0,0),
                Arguments.of(10.0,12.0,8),
                Arguments.of(10.0,12.25,9),
                Arguments.of(10.0,10.24,0),
                Arguments.of(3.50,5.25,7),
                Arguments.of(0.25,0.50,1)
        );
    }
}
