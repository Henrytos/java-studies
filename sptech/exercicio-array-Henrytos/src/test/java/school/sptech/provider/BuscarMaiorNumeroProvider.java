package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class BuscarMaiorNumeroProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(new Integer[] { 10, 20, 30, 40, 50 }, 50),
                Arguments.of(new Integer[] { 5, 15, 25, 35, 45 }, 45),
                Arguments.of(new Integer[] { -10, -20, -30, -40, -50 }, -10),
                Arguments.of(new Integer[] { 0, 0, 0, 0, 0 }, 0),
                Arguments.of(new Integer[] { 100, 200, 300, 400, 500 }, 500),
                Arguments.of(new Integer[] { -10, -15, -5, -20, -25 }, -5),
                Arguments.of(new Integer[] { 8, 16, 24, 32, 40 }, 40),
                Arguments.of(new Integer[] { -3, 3, -6, 6, -9, 9 }, 9),
                Arguments.of(new Integer[] { 7, 14, 21, 28, 35 }, 35),
                Arguments.of(new Integer[] { 1, 2, 3, 4, 5 }, 5)
        );
    }
}
