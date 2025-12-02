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

public class BuscarPorMediaMaiorProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {

        Object e1 = EpisodioFactory.build("Episodio 1 - Introdução à Tecnologia", 30.0, "Podcast de Tecnologia", List.of()); // 0.0
        Object e2 = EpisodioFactory.build("Episodio 2 - Inovações Tecnológicas", 45.0, "Podcast de Tecnologia", List.of(10, 8, 7)); // 8.33
        Object e3 = EpisodioFactory.build("Episodio 1 - Alimentação Saudável", 25.0, "Podcast de Saúde", List.of(9, 9, 8)); // 8.67
        Object e4 = EpisodioFactory.build("Episodio 2 - Exercícios Físicos", 40.0, "Podcast de Saúde", List.of(6, 7)); // 6.5
        Object e5 = EpisodioFactory.build("Episodio 3 - Saúde Mental", 35.0, "Podcast de Saúde", List.of(10, 10, 9, 8)); // 9.25
        Object e6 = EpisodioFactory.build("Episodio 4 - Saúde e Bem-estar", 50.0, "Podcast de Saúde", List.of(5)); // 5.0

        Object m1 = MusicaFactory.build("Time", 4.0, "Pink Floyd", "The Dark Side of the Moon", "ROCK");
        Object m2 = MusicaFactory.build("Blinding Lights", 3.0, "The Weeknd", "After Hours", "POP");
        Object m3 = MusicaFactory.build("Bohemian Rhapsody", 6.0, "Queen", "A Night at the Opera", "ROCK");
        Object m4 = MusicaFactory.build("Stairway to Heaven", 8.0, "Led Zeppelin", "Led Zeppelin IV", "ROCK");
        Object m5 = MusicaFactory.build("Get Lucky", 6.0, "Daft Punk", "Random Access Memories", "ELETRONICA");
        Object m6 = MusicaFactory.build("Earth Wind and Fire", 5.0, "September", "The Best of Earth Wind and Fire", "FUNK");
        Object m7 = MusicaFactory.build("Borboletas", 4.0, "Victor e Leo", "Borboletas", "SERTANEJO");
        Object m8 = MusicaFactory.build("Evidências", 4.0, "Chitãozinho & Xororó", "Evidências", "SERTANEJO");

        Object p1 = PlaylistFactory.build("Playlist Rock", List.of(m1, m3, m4), List.of());   // 0.0
        Object p2 = PlaylistFactory.build("Playlist Pop", List.of(m2, m5), List.of(9, 8, 10)); // 9.0
        Object p3 = PlaylistFactory.build("Playlist Sertanejo", List.of(m7, m8), List.of(7, 6)); // 6.5
        Object p4 = PlaylistFactory.build("Playlist Vazia", List.of(), List.of(10, 9, 8, 7, 6)); // 8.0
        Object p5 = PlaylistFactory.build("Playlist Eletrônica", List.of(m5), List.of(5)); // 5.0

        Object s1 = StreamingFactory.build("Streaming A", List.of(e1, e2, e3), List.of(p1, p2));
        Object s2 = StreamingFactory.build("Streaming B", List.of(e4, e5, e6), List.of(p3, p4, p5));
        Object s3 = StreamingFactory.build("Streaming C", List.of(), List.of());
        Object s4 = StreamingFactory.build("Streaming D", List.of(e1, e4), List.of(p1, p3));
        Object s5 = StreamingFactory.build("Streaming E", List.of(e2, e5), List.of(p2, p4));

        return Stream.of(
                Arguments.of(s1, 8.0, List.of(e2, e3, p2)),
                Arguments.of(s2, 7.0, List.of(e5, p4)),
                Arguments.of(s3, 5.0, List.of()),
                Arguments.of(s4, 6.0, List.of(e4, p3)),
                Arguments.of(s5, 8.0, List.of(e2, e5, p2))
        );
    }
}
