package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class InverterProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(new Character[] { 'a', 'b', 'c', 'd' }, new Character[] { 'd', 'c', 'b', 'a' }),
                Arguments.of(new Character[] { '1', '2', '3', '4', '5' }, new Character[] { '5', '4', '3', '2', '1' }),
                Arguments.of(new Character[] { 'x', 'y', 'z' }, new Character[] { 'z', 'y', 'x' }),
                Arguments.of(new Character[] { 'J', 'a', 'v', 'a' }, new Character[] { 'a', 'v', 'a', 'J' }),
                Arguments.of(new Character[] { '!', '@', '#', '$' }, new Character[] { '$', '#', '@', '!' }),
                Arguments.of(new Character[] { 'p', 'y', 't', 'h', 'o', 'n' }, new Character[] { 'n', 'o', 'h', 't', 'y', 'p' }),
                Arguments.of(new Character[] { '1', '0', '1', '0' }, new Character[] { '0', '1', '0', '1' }),
                Arguments.of(new Character[] { 'r', 'e', 'v', 'i', 'v', 'e', 'r' }, new Character[] { 'r', 'e', 'v', 'i', 'v', 'e', 'r' }),
                Arguments.of(new Character[] { 'A' }, new Character[] { 'A' })
        );
    }
}
