package school.sptech.provider.streaming;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.EpisodioFactory;
import school.sptech.factory.MusicaFactory;
import school.sptech.factory.PlaylistFactory;
import school.sptech.factory.StreamingFactory;

import java.util.List;
import java.util.stream.Stream;

public class AdicionarPlaylistProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {

    Object e1 = EpisodioFactory.build("Episodio 1 - Introdução à Tecnologia", 30.0, "Podcast de Tecnologia");
    Object e2 = EpisodioFactory.build("Episodio 2 - Inovações Tecnológicas", 45.0, "Podcast de Tecnologia");
    Object e3 = EpisodioFactory.build("Episodio 1 - Alimentação Saudável", 25.0, "Podcast de Saúde");
    Object e4 = EpisodioFactory.build("Episodio 2 - Exercícios Físicos", 40.0, "Podcast de Saúde");
    Object e5 = EpisodioFactory.build("Episodio 3 - Saúde Mental", 35.0, "Podcast de Saúde");

    Object m1 = MusicaFactory.build("Time", 4.0, "Pink Floyd", "The Dark Side of the Moon", "ROCK");
    Object m2 = MusicaFactory.build("Blinding Lights", 3.0, "The Weeknd", "After Hours", "POP");
    Object m3 = MusicaFactory.build("Bohemian Rhapsody", 6.0, "Queen", "A Night at the Opera", "ROCK");
    Object m4 = MusicaFactory.build("Stairway to Heaven", 8.0, "Led Zeppelin", "Led Zeppelin IV", "ROCK");
    Object m5 = MusicaFactory.build("Get Lucky", 6.0, "Daft Punk", "Random Access Memories", "ELETRONICA");
    Object m6 = MusicaFactory.build("Earth Wind and Fire", 5.0, "September", "The Best of Earth Wind and Fire", "FUNK");
    Object m7 = MusicaFactory.build("Borboletas", 4.0, "Victor e Leo", "Borboletas", "SERTANEJO");
    Object m8 = MusicaFactory.build("Evidências", 4.0, "Chitãozinho & Xororó", "Evidências", "SERTANEJO");

    Object p1 = PlaylistFactory.build("Playlist Rock", List.of(m1, m3, m4), List.of());
    Object p2 = PlaylistFactory.build("Playlist Pop", List.of(m2, m5), List.of());
    Object p3 = PlaylistFactory.build("Playlist Sertanejo", List.of(m7, m8), List.of());
    Object p4 = PlaylistFactory.build("Playlist Vazia", List.of(), List.of());
    Object p5 = PlaylistFactory.build("Playlist Eletrônica", List.of(m5), List.of());

    Object s1 = StreamingFactory.build("StreamX", List.of(), List.of());
    Object s2 = StreamingFactory.build("PlayNow", List.of(e1, m1), List.of(p1, p2));
    Object s3 = StreamingFactory.build("MediaFlix", List.of(e3, e4, m2, m3), List.of(p3, p4));
    Object s4 = StreamingFactory.build("TuneStream", List.of(m4, m5, m6), List.of(p5));
    Object s5 = StreamingFactory.build("PodMusic", List.of(e2, e5, m7, m8), List.of(p5, p1, p3, p4));

    return Stream.of(
        Arguments.of(s1, "Playlist Rock", List.of(p1)),
        Arguments.of(s2, "Playlist Sertanejo", List.of(p1, p2, p3)),
        Arguments.of(s3, "Playlist Eletrônica", List.of(p3, p4, p5)),
        Arguments.of(s4, "Playlist Pop", List.of(p5, p2)),
        Arguments.of(s5, "Playlist Vazia", List.of(p5, p1, p3, p4, p4))
    );
  }
}
