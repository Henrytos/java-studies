package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.fixture.MusicaFixture;

import java.util.List;
import java.util.stream.Stream;

public class FindByArtistaProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of("", List.of()),
                Arguments.of("Pink Floyd", List.of()),
                Arguments.of("Michael Jackson", MusicaFixture.ids(11, 12, 13, 14, 15)),
                Arguments.of("Queen", MusicaFixture.ids(1, 2, 3, 4, 5)),
                Arguments.of("The Beatles", MusicaFixture.ids(16, 17, 18, 19, 20)),
                Arguments.of("Red Hot Chili Peppers", MusicaFixture.ids(41, 42, 43, 44, 45))
        );
    }
}
