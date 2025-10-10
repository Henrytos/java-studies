package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.fixture.MusicaFixture;

import java.util.stream.Stream;

public class FindByDuracaoGreaterThanProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(0, MusicaFixture.all()),
                Arguments.of(300, MusicaFixture.ids(1, 6, 12, 15, 16, 21, 22, 26, 28, 29, 31, 35, 38, 42)),
                Arguments.of(350, MusicaFixture.ids(1, 12, 16, 21, 26, 28, 31, 35, 38)),
                Arguments.of(400, MusicaFixture.ids(16, 26, 28, 35, 38)),
                Arguments.of(200, MusicaFixture.ids(1, 2, 3, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 19, 21, 22, 23, 24, 25, 26, 27, 28, 29, 31, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45))
        );
    }
}
