package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.Musica;

import java.util.stream.Stream;

public class FindByIdProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(-1, null),
                Arguments.of(1, new Musica(1, "Bohemian Rhapsody", "Queen", "A Night at the Opera", 354)),
                Arguments.of(19, new Musica(19, "Come Together", "The Beatles", "Abbey Road", 259)),
                Arguments.of(27, new Musica(27, "Black Dog", "Led Zeppelin", "Led Zeppelin IV", 296)),
                Arguments.of(30, new Musica(30, "Immigrant Song", "Led Zeppelin", "Led Zeppelin III", 146)),
                Arguments.of(27, new Musica(27, "Black Dog", "Led Zeppelin", "Led Zeppelin IV", 296)),
                Arguments.of(32, new Musica(32, "Knockin on Heavens Door", "Bob Dylan", "Pat Garrett & Billy the Kid", 163)),
                Arguments.of(36, new Musica(36, "Wonderwall", "Oasis", "(Whats the Story) Morning Glory?", 259)),
                Arguments.of(42, new Musica(42, "Californication", "Red Hot Chili Peppers", "Californication", 322)),
                Arguments.of(99, null)
        );
    }
}
