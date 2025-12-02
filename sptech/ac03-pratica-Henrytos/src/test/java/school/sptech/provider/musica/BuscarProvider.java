package school.sptech.provider.musica;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.MusicaFactory;

import java.util.stream.Stream;

public class BuscarProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {

        Object m1 = MusicaFactory.build("Time", 4.0, "Pink Floyd", "The Dark Side of the Moon", "ROCK");
        Object m2 = MusicaFactory.build("Blinding Lights", 3.0, "The Weeknd", "After Hours", "POP");
        Object m3 = MusicaFactory.build("Bohemian Rhapsody", 6.0, "Queen", "A Night at the Opera", "ROCK");
        Object m4 = MusicaFactory.build("Stairway to Heaven", 8.0, "Led Zeppelin", "Led Zeppelin IV", "ROCK");
        Object m5 = MusicaFactory.build("Get Lucky", 6.0, "Daft Punk", "Random Access Memories", "ELETRONICA");
        Object m6 = MusicaFactory.build("Earth Wind and Fire", 5.0, "September", "The Best of Earth Wind and Fire", "FUNK");
        Object m7 = MusicaFactory.build("Borboletas", 4.0, "Victor e Leo", "Borboletas", "SERTANEJO");
        Object m8 = MusicaFactory.build("Evidências", 4.0, "Chitãozinho & Xororó", "Evidências", "SERTANEJO");

        return Stream.of(
                Arguments.of(m1, "Pink", true),
                Arguments.of(m2, "weeknd", true),
                Arguments.of(m3, "Bohemian", true),
                Arguments.of(m4, "heaven", true),
                Arguments.of(m5, "x", false),
                Arguments.of(m6, "Miles", false),
                Arguments.of(m7, "Victor", true),
                Arguments.of(m8, "evidências", true),
                Arguments.of(m1, "Pop", false),
                Arguments.of(m5, "borboletas", false),
                Arguments.of(m7, "Rock", false),
                Arguments.of(m4, "Led", true),
                Arguments.of(m2, "After", true),
                Arguments.of(m3, "A Night at the Opera", true),
                Arguments.of(m6, "Funk", true),
                Arguments.of(m8, "Chitãozinho", true)
        );
    }
}
