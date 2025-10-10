package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class CalcularMediaProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(10.0, 20.0, 30.0, 20.0),
                Arguments.of(-10.0, -20.0, -30.0, -20.0),
                Arguments.of(10.0, 20.0, -10.0, 6.67),
                Arguments.of(-10.0, -20.0, 10.0, -6.67),
                Arguments.of(1.0, 2.0, 3.0,2.0),
                Arguments.of(0.0, 0.0, 10.0, 3.33)
        );
    }
}
