package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class SomarProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(new Integer[] { 8, 72, 9, 4, 29 }, 122),
                Arguments.of(new Integer[] { 15, 23, 7, 42, 18 }, 105),
                Arguments.of(new Integer[] { 5, 10, 20, 30, 35 }, 100),
                Arguments.of(new Integer[] { 99, 1, 50, 25, 75 }, 250),
                Arguments.of(new Integer[] { 3, 8, 12, 17, 21 }, 61),
                Arguments.of(new Integer[] { 100, 200, 300, 400, 500 }, 1500),
                Arguments.of(new Integer[] { 45, 55, 65, 75, 85 }, 325),
                Arguments.of(new Integer[] { 2, 4, 6, 8, 10 }, 30),
                Arguments.of(new Integer[] { 11, 22, 33, 44, 55 }, 165),
                Arguments.of(new Integer[] { 7, 14, 21, 28, 35 }, 105),
                Arguments.of(new Integer[] { 1, 3, 5, 7, 9, 11 }, 36),
                Arguments.of(new Integer[] { -1, -2, -3, -4, -5 }, -15),
                Arguments.of(new Integer[] { -100, 50, -25, 75, -50 }, -50),
                Arguments.of(new Integer[] { -30, -20, -10, 0, 10, 20, 30 }, 0),
                Arguments.of(new Integer[] { }, 0)
        );
    }
}
