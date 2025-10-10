package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.Musica;
import school.sptech.fixture.MusicaFixture;

import java.util.List;
import java.util.stream.Stream;

public class FindByNomeLikeProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of("teste", List.of()),
                Arguments.of("we", MusicaFixture.ids(4, 5)),
                Arguments.of("oo", MusicaFixture.ids(9, 14, 32, 37)),
                Arguments.of("In", MusicaFixture.ids(9, 14, 15, 20, 22, 25, 31, 32, 33, 34, 37)),
                Arguments.of("You", MusicaFixture.ids(4, 7))
        );
    }
}
