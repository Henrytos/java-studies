package school.sptech.provider.playlist;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.MusicaFactory;
import school.sptech.factory.PlaylistFactory;

import java.util.List;
import java.util.stream.Stream;

public class CalcularMediaProvider implements ArgumentsProvider {

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

    Object p1 = PlaylistFactory.build("Playlist Rock", List.of(m1, m3, m4), List.of());
    Object p2 = PlaylistFactory.build("Playlist Pop", List.of(m2, m5), List.of(9, 8, 10));
    Object p3 = PlaylistFactory.build("Playlist Sertanejo", List.of(m7, m8), List.of(7, 6));
    Object p4 = PlaylistFactory.build("Playlist Vazia", List.of(), List.of(10, 9, 8, 7, 6));
    Object p5 = PlaylistFactory.build("Playlist Eletrônica", List.of(m5), List.of(5));

    return Stream.of(
        Arguments.of(p1, 0.0),
        Arguments.of(p2, 9.0),
        Arguments.of(p3, 6.5),
        Arguments.of(p4, 8.0),
        Arguments.of(p5, 5.0)
    );
  }
}
