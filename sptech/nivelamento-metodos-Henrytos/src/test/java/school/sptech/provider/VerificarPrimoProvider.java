package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class VerificarPrimoProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
          throws Exception {
        return Stream.of(
              Arguments.of(-1, false),
              Arguments.of(0, false),
              Arguments.of(1, false),
              Arguments.of(10, false),
              Arguments.of(25, false),
              Arguments.of(1000, false),
              Arguments.of(2, true),
              Arguments.of(3, true),
              Arguments.of(7, true),
              Arguments.of(11, true),
              Arguments.of(23, true),
              Arguments.of(47, true),
              Arguments.of(73, true)
        );
    }
}
