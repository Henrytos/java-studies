package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class VerificarMaioridadeProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
          throws Exception {
        return Stream.of(
              Arguments.of(-1, false),
              Arguments.of(5, false),
              Arguments.of(17, false),
              Arguments.of(18, true),
              Arguments.of(19, true),
              Arguments.of(20, true),
              Arguments.of(100, true)
        );
    }
}
