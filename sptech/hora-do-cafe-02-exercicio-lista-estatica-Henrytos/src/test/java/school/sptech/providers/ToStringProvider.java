package school.sptech.providers;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class ToStringProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
              Arguments.of(new Integer[]{null, null, null}, 3, "[]"),
              Arguments.of(new Integer[]{5, null, null}, 3, "[5]"),
              Arguments.of(new Integer[]{1, 2, 3, null, null}, 5, "[1, 2, 3]"),
              Arguments.of(new Integer[]{10, 20, null, null}, 4, "[10, 20]"),
              Arguments.of(new Integer[]{7, 14, 21, 28}, 4, "[7, 14, 21, 28]")
        );
    }
}
