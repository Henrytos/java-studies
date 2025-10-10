package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.fixture.MusicaFixture;

import java.util.List;
import java.util.stream.Stream;

public class FindByAlbumAndNomeLikeProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of("The Dark Side of the Moon", "Money", List.of()),
                Arguments.of("News of the World", "we", MusicaFixture.ids(4, 5)),
                Arguments.of("Nevermind", "oo", MusicaFixture.ids(9)),
                Arguments.of("Bad", "In", MusicaFixture.ids(14, 15)),
                Arguments.of("Californication", "You", List.of())
        );
    }
}
