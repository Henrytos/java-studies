package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.fixture.MusicaFixture;

import java.util.List;
import java.util.stream.Stream;

public class FindByAlbumProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of("", List.of()),
                Arguments.of("The Dark Side of the Moon", List.of()),
                Arguments.of("One of These Nights", MusicaFixture.ids(25)),
                Arguments.of("Abbey Road", MusicaFixture.ids(19, 20)),
                Arguments.of("Californication", MusicaFixture.ids(42, 43, 44)),
                Arguments.of("Hotel California", MusicaFixture.ids(21, 22))
        );
    }
}
