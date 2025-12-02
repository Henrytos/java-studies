package school.sptech.provider.streaming;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.mockito.Mockito;
import school.sptech.factory.EpisodioFactory;
import school.sptech.factory.MusicaFactory;
import school.sptech.factory.PlaylistFactory;
import school.sptech.factory.StreamingFactory;

import java.util.List;
import java.util.stream.Stream;

public class AvaliarPlaylistProvider implements ArgumentsProvider {

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
    Object p6 = PlaylistFactory.build("Playlist Saúde", List.of(m5, m6), List.of());
    Object p7 = PlaylistFactory.build("Playlist Tecnologia", List.of(m1, m2), List.of());
    Object p8 = PlaylistFactory.build("Playlist Fitness", List.of(m3, m4), List.of());

    Object spy1 = Mockito.spy(p1);
    Object spy2 = Mockito.spy(p2);
    Object spy3 = Mockito.spy(p3);
    Object spy4 = Mockito.spy(p4);
    Object spy5 = Mockito.spy(p5);
    Object spy6 = Mockito.spy(p6);
    Object spy7 = Mockito.spy(p7);
    Object spy8 = Mockito.spy(p8);

    Object s1 = StreamingFactory.build("StreamX", List.of(), List.of(spy1, p2));
    Object s2 = StreamingFactory.build("PlayNow", List.of(), List.of(p3, p4, spy2));
    Object s3 = StreamingFactory.build("MediaFlix", List.of(), List.of(p5, spy4, p6));
    Object s4 = StreamingFactory.build("TuneStream", List.of(), List.of(p7, p8, spy3));
    Object s5 = StreamingFactory.build("PodMusic", List.of(), List.of(spy5, p1, p2));
    Object s6 = StreamingFactory.build("HealthTunes", List.of(), List.of(p3, spy6));
    Object s7 = StreamingFactory.build("TechBeats", List.of(), List.of(spy7, p4));
    Object s8 = StreamingFactory.build("FitMix", List.of(), List.of(p5, spy8));

    return Stream.of(
            Arguments.of(s1, "Playlist Rock", 9, spy1),
            Arguments.of(s2, "Playlist Pop", 8, spy2),
            Arguments.of(s3, "Playlist Vazia", 7, spy4),
            Arguments.of(s4, "Playlist Sertanejo", 9, spy3),
            Arguments.of(s5, "Playlist Eletrônica", 10, spy5),
            Arguments.of(s6, "Playlist Saúde", 8, spy6),
            Arguments.of(s7, "Playlist Tecnologia", 9, spy7),
            Arguments.of(s8, "Playlist Fitness", 7, spy8)
    );
  }
}
