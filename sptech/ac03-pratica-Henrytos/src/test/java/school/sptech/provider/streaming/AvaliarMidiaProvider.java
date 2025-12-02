package school.sptech.provider.streaming;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.mockito.Mockito;
import school.sptech.factory.EpisodioFactory;
import school.sptech.factory.MusicaFactory;
import school.sptech.factory.StreamingFactory;

import java.util.List;
import java.util.stream.Stream;

public class AvaliarMidiaProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {

    Object e1 = EpisodioFactory.build("Episodio 1 - Introdução à Tecnologia", 30.0, "Podcast de Tecnologia", List.of());
    Object e2 = EpisodioFactory.build("Episodio 2 - Inovações Tecnológicas", 45.0, "Podcast de Tecnologia", List.of());
    Object e3 = EpisodioFactory.build("Episodio 1 - Alimentação Saudável", 25.0, "Podcast de Saúde", List.of());
    Object e4 = EpisodioFactory.build("Episodio 2 - Exercícios Físicos", 40.0, "Podcast de Saúde", List.of());
    Object e5 = EpisodioFactory.build("Episodio 3 - Saúde Mental", 35.0, "Podcast de Saúde", List.of());

    Object m1 = MusicaFactory.build("Time", 4.0, "Pink Floyd", "The Dark Side of the Moon", "ROCK");
    Object m2 = MusicaFactory.build("Blinding Lights", 3.0, "The Weeknd", "After Hours", "POP");
    Object m3 = MusicaFactory.build("Bohemian Rhapsody", 6.0, "Queen", "A Night at the Opera", "ROCK");
    Object m4 = MusicaFactory.build("Stairway to Heaven", 8.0, "Led Zeppelin", "Led Zeppelin IV", "ROCK");
    Object m5 = MusicaFactory.build("Get Lucky", 6.0, "Daft Punk", "Random Access Memories", "ELETRONICA");
    Object m6 = MusicaFactory.build("Earth Wind and Fire", 5.0, "September", "The Best of Earth Wind and Fire", "FUNK");
    Object m7 = MusicaFactory.build("Borboletas", 4.0, "Victor e Leo", "Borboletas", "SERTANEJO");
    Object m8 = MusicaFactory.build("Evidências", 4.0, "Chitãozinho & Xororó", "Evidências", "SERTANEJO");

    Object spy1 = Mockito.spy(e1);
    Object spy2 = Mockito.spy(e2);
    Object spy3 = Mockito.spy(e3);
    Object spy4 = Mockito.spy(e4);
    Object spy5 = Mockito.spy(e5);

    Object s1 = StreamingFactory.build("StreamX", List.of(spy1, e2, m1));
    Object s2 = StreamingFactory.build("PlayNow", List.of(spy2, m1));
    Object s3 = StreamingFactory.build("MediaFlix", List.of(e3, spy4, m2, m3));
    Object s4 = StreamingFactory.build("TuneStream", List.of(m4, m5, m6, spy3));
    Object s5 = StreamingFactory.build("PodMusic", List.of(e2, spy5, m7, m8));

    return Stream.of(
            Arguments.of(s1, "Episodio 1 - Introdução à Tecnologia", 9, spy1),
            Arguments.of(s2, "Episodio 2 - Inovações Tecnológicas", 8, spy2),
            Arguments.of(s3, "Episodio 2 - Exercícios Físicos", 3, spy4),
            Arguments.of(s4, "Episodio 1 - Alimentação Saudável", 7, spy3),
            Arguments.of(s5, "Episodio 3 - Saúde Mental", 9, spy5)
    );
  }
}
