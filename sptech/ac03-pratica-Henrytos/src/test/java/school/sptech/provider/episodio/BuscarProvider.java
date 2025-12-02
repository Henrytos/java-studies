package school.sptech.provider.episodio;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.EpisodioFactory;

import java.util.stream.Stream;

public class BuscarProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {

        Object e1 = EpisodioFactory.build("Episodio 1 - Introdução à Tecnologia", 30.0, "Podcast de Tecnologia");
        Object e2 = EpisodioFactory.build("Episodio 2 - Inovações Tecnológicas", 45.0, "Podcast de Tecnologia");
        Object e3 = EpisodioFactory.build("Episodio 1 - Alimentação Saudável", 25.0, "Podcast de Saúde");
        Object e4 = EpisodioFactory.build("Episodio 2 - Exercícios Físicos", 40.0, "Podcast de Saúde");
        Object e5 = EpisodioFactory.build("Episodio 3 - Saúde Mental", 35.0, "Podcast de Saúde");

        return Stream.of(
                Arguments.of(e1, "Tecnologia", true),
                Arguments.of(e2, "inovações tecnológicas", true),
                Arguments.of(e3, "Podcast de Saúde", true),
                Arguments.of(e4, "saúde", true),
                Arguments.of(e5, "mental", true),
                Arguments.of(e1, "Saúde", false),
                Arguments.of(e3, "tecnologia", false),
                Arguments.of(e4, "y", false),
                Arguments.of(e5, "x", false),
                Arguments.of(e2, "Podcast de Saúde", false),
                Arguments.of(e1, "10", false),
                Arguments.of(e3, "1", true),
                Arguments.of(e4, "cios", true),
                Arguments.of(e5, "Tec", false),
                Arguments.of(e2, "Inova", true)
        );
    }
}
