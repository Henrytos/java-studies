package school.sptech.providers;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class RemoveElementoProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
              Arguments.of(new Integer[]{10, 20, 30, null, null}, 5, 20,
                    new Integer[]{10, 30, null, null, null}, true, 2),
              Arguments.of(new Integer[]{5, 15, 25, null}, 4, 5, new Integer[]{15, 25, null, null},
                    true, 2),
              Arguments.of(new Integer[]{7, 14, 21}, 3, 21, new Integer[]{7, 14, null}, true, 2),
              Arguments.of(new Integer[]{1, 2, 3}, 3, 99, new Integer[]{1, 2, 3}, false, 3)
        );
    }
}
