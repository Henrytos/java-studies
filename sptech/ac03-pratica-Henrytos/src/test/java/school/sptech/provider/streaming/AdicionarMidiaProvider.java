package school.sptech.provider.streaming;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.EpisodioFactory;
import school.sptech.factory.MusicaFactory;
import school.sptech.factory.StreamingFactory;

import java.util.List;
import java.util.stream.Stream;

public class AdicionarMidiaProvider implements ArgumentsProvider {

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

        Object s1 = StreamingFactory.build("StreamX", List.of());
        Object s2 = StreamingFactory.build("PlayNow", List.of(e1, m1));
        Object s3 = StreamingFactory.build("MediaFlix", List.of(e3, e4, m2, m3));
        Object s4 = StreamingFactory.build("TuneStream", List.of(m4, m5, m6));
        Object s5 = StreamingFactory.build("PodMusic", List.of(e2, e5, m7, m8));
        Object s6 = StreamingFactory.build("EmptyStream", List.of());
        Object s7 = StreamingFactory.build("FullStream", List.of(e1, e2, e3, e4, e5, m1, m2, m3, m4, m5, m6));
        Object s8 = StreamingFactory.build("SingleEpiStream", List.of(e1));
        Object s9 = StreamingFactory.build("SingleMusicStream", List.of(m1));
        Object s10 = StreamingFactory.build("MixedStream", List.of(e1, m1, e2, m2));

        return Stream.of(
                Arguments.of(s1, e1, List.of(e1)),
                Arguments.of(s2, m2, List.of(e1, m1, m2)),
                Arguments.of(s3, e5, List.of(e3, e4, m2, m3, e5)),
                Arguments.of(s4, m1, List.of(m4, m5, m6, m1)),
                Arguments.of(s5, e3, List.of(e2, e5, m7, m8, e3)),
                Arguments.of(s6, m3, List.of(m3)),
                Arguments.of(s7, e4, List.of(e1, e2, e3, e4, e5, m1, m2, m3, m4, m5, m6, e4)),
                Arguments.of(s8, m4, List.of(e1, m4)),
                Arguments.of(s9, e2, List.of(m1, e2)),
                Arguments.of(s10, m5, List.of(e1, m1, e2, m2, m5))
        );
    }
}
