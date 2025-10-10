package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class SomarDoisProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(new Integer[] { 2, 7, 11, 15 }, 9, new Integer[] { 0, 1 }),
                Arguments.of(new Integer[] { 3, 2, 4 }, 6, new Integer[] { 1, 2 }),
                Arguments.of(new Integer[] { 2, 7, 11, 15 }, 9, new Integer[] { 0, 1 }),
                Arguments.of(new Integer[] { 1, 5, 3, 10 }, 8, new Integer[] { 1, 2 }),
                Arguments.of(new Integer[] { 10, 20, 30, 40, 55 }, 70, new Integer[] { 2, 3 }),
                Arguments.of(new Integer[] { 6, 4, 5, 10, 12 }, 11, new Integer[] { 0, 2 }),
                Arguments.of(new Integer[] { 9, 2, 4 }, 13, new Integer[] { 0, 2 })
        );
    }
}
