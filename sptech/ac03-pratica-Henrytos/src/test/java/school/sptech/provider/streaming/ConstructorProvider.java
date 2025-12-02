package school.sptech.provider.streaming;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class ConstructorProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
            Arguments.of("SPFy"),
            Arguments.of("Spotflix"),
            Arguments.of("StreamTech"),
            Arguments.of("MusiTech"),
            Arguments.of("TechFlix")
        );
    }
}
