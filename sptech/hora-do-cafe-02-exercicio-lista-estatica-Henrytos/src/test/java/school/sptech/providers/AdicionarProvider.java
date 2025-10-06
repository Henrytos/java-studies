package school.sptech.providers;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class AdicionarProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
              Arguments.of(new Integer[]{1, 2, 3, null, null}, 5, 4,
                    new Integer[]{1, 2, 3, 4, null}, 4),
              Arguments.of(new Integer[]{10, 20, null, null}, 4, 30,
                    new Integer[]{10, 20, 30, null}, 3),
              Arguments.of(new Integer[]{null, null, null}, 3, 5, new Integer[]{5, null, null}, 1),
              Arguments.of(new Integer[]{1, 2, 3, 4, 5}, 5, 6, new Integer[]{1, 2, 3, 4, 5}, 5)
        );
    }
}