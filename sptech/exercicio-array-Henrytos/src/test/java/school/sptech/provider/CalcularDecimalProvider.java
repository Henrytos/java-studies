package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class CalcularDecimalProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(new Integer[] { 1, 0, 1, 0 }, 10),   // 1010 -> 10
                Arguments.of(new Integer[] { 1, 1, 1, 1 }, 15),   // 1111 -> 15
                Arguments.of(new Integer[] { 1, 0, 0, 1 }, 9),    // 1001 -> 9
                Arguments.of(new Integer[] { 0, 0, 0, 0 }, 0),    // 0000 -> 0
                Arguments.of(new Integer[] { 1, 0, 0, 0 }, 8),    // 1000 -> 8
                Arguments.of(new Integer[] { 1, 1, 0, 1 }, 13),   // 1101 -> 13
                Arguments.of(new Integer[] { 1, 0, 1, 1 }, 11),   // 1011 -> 11
                Arguments.of(new Integer[] { 0, 1, 1, 0 }, 6),    // 0110 -> 6
                Arguments.of(new Integer[] { 1, 1, 0, 0 }, 12),   // 1100 -> 12
                Arguments.of(new Integer[] { 0, 1, 0, 1 }, 5),     // 0101 -> 5
                Arguments.of(new Integer[] { 1, 0, 1, 0, 1, 0 }, 42),   // 101010 -> 42
                Arguments.of(new Integer[] { 1, 1, 0, 0, 1, 0 }, 50),   // 110010 -> 50
                Arguments.of(new Integer[] { 0, 1, 0, 1, 1, 1 }, 23),   // 010111 -> 23
                Arguments.of(new Integer[] { 1, 1, 1, 1, 1, 1 }, 63),   // 111111 -> 63
                Arguments.of(new Integer[] { 1, 0, 0, 0, 0, 0 }, 32),   // 100000 -> 32
                Arguments.of(new Integer[] { 1, 0, 1, 0, 1, 1, 0, 1 }, 173), // 10101101 -> 173
                Arguments.of(new Integer[] { 1, 1, 0, 0, 1, 1, 0, 0 }, 204), // 11001100 -> 204
                Arguments.of(new Integer[] { 0, 1, 1, 0, 1, 0, 1, 0 }, 106), // 01101010 -> 106
                Arguments.of(new Integer[] { 1, 1, 1, 1, 0, 0, 0, 0 }, 240), // 11110000 -> 240
                Arguments.of(new Integer[] { 0, 0, 0, 1, 1, 1, 1, 1 }, 31)   // 00011111 -> 31
        );
    }
}
