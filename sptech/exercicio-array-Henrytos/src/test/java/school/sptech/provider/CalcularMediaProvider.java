package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class CalcularMediaProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(new Double[] { 10.0, 20.0, 30.0, 40.0, 50.0 }, 30.0),
                Arguments.of(new Double[] { 5.0, 15.0, 25.0, 35.0, 45.0 }, 25.0),
                Arguments.of(new Double[] { -10.0, -20.0, -30.0, -40.0, -50.0 }, -30.0),
                Arguments.of(new Double[] { 0.0, 0.0, 0.0, 0.0, 0.0 }, 0.0),
                Arguments.of(new Double[] { 100.0, 200.0, 300.0, 400.0, 500.0 }, 300.0),
                Arguments.of(new Double[] { -5.0, -10.0, -15.0, -20.0, -25.0 }, -15.0),
                Arguments.of(new Double[] { 8.0, 16.0, 24.0, 32.0, 40.0 }, 24.0),
                Arguments.of(new Double[] { -3.0, 3.0, -6.0, 6.0, -9.0, 9.0 }, 0.0),
                Arguments.of(new Double[] { 7.0, 14.0, 21.0, 28.0, 35.0 }, 21.0),
                Arguments.of(new Double[] { 1.0, 2.0, 3.0, 4.0, 5.0 }, 3.0)
        );
    }
}
