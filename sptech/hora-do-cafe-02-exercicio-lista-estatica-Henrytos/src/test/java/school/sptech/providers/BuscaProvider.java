package school.sptech.providers;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class BuscaProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
              Arguments.of(new Integer[]{10, 20, 30, null, null}, 5, 20, 1),
              Arguments.of(new Integer[]{5, 15, 25, null}, 4, 5, 0),
              Arguments.of(new Integer[]{7, 14, 21}, 3, 21, 2),
              Arguments.of(new Integer[]{1, 2, 3}, 3, 99, -1),
              Arguments.of(new Integer[]{null, null, null}, 3, 1, -1)
        );
    }
}
