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

public class AdicionarMusicaNaPlaylistProvider implements ArgumentsProvider {

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

    Object spy1_1 = Mockito.spy(p1);

    Object spy1_2 = Mockito.spy(p1);
    Object spy2_2 = Mockito.spy(p2);
    Object spy3_2 = Mockito.spy(p3);

    Object spy3_3 = Mockito.spy(p3);
    Object spy4_3 = Mockito.spy(p4);

    Object spy5_4 = Mockito.spy(p5);

    Object spy1_5 = Mockito.spy(p1);
    Object spy3_5 = Mockito.spy(p3);
    Object spy4_5 = Mockito.spy(p4);
    Object spy5_5 = Mockito.spy(p5);

    Object s1 = StreamingFactory.build("StreamX", List.of(), List.of(spy1_1));
    Object s2 = StreamingFactory.build("PlayNow", List.of(e1, m1), List.of(spy1_2, spy3_2, spy2_2));
    Object s3 = StreamingFactory.build("MediaFlix", List.of(e3, e4, m2, m3), List.of(spy3_3, spy4_3));
    Object s4 = StreamingFactory.build("TuneStream", List.of(m4, m5, m6), List.of(spy5_4));
    Object s5 = StreamingFactory.build("PodMusic", List.of(e2, e5, m7, m8), List.of(spy5_5, spy1_5, spy3_5, spy4_5));

    return Stream.of(
        Arguments.of(s1, m3, "Playlist Rock", spy1_1),
        Arguments.of(s2, m7, "Playlist Sertanejo", spy3_2),
        Arguments.of(s3, m5, "Playlist Vazia", spy4_3),
        Arguments.of(s4, m2, "Playlist Eletrônica", spy5_4),
        Arguments.of(s5, m4, "Playlist Vazia", spy4_5)
    );
  }
}
