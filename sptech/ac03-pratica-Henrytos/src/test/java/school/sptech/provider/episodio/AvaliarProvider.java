package school.sptech.provider.episodio;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.EpisodioFactory;

import java.util.List;
import java.util.stream.Stream;

public class AvaliarProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {

    Object e1 = EpisodioFactory.build("Episodio 1 - Introdução à Tecnologia", 30.0, "Podcast de Tecnologia", List.of());
    Object e2 = EpisodioFactory.build("Episodio 2 - Inovações Tecnológicas", 45.0, "Podcast de Tecnologia", List.of(10, 8, 7));
    Object e3 = EpisodioFactory.build("Episodio 1 - Alimentação Saudável", 25.0, "Podcast de Saúde", List.of(9, 9, 8));
    Object e4 = EpisodioFactory.build("Episodio 2 - Exercícios Físicos", 40.0, "Podcast de Saúde", List.of(6, 7));
    Object e5 = EpisodioFactory.build("Episodio 3 - Saúde Mental", 35.0, "Podcast de Saúde", List.of(10, 10, 9, 8));
    Object e6 = EpisodioFactory.build("Episodio 4 - Saúde e Bem-estar", 50.0, "Podcast de Saúde", List.of(5));

    return Stream.of(
        Arguments.of(e1, 9, List.of(9)),
        Arguments.of(e2, 6, List.of(10, 8, 7, 6)),
        Arguments.of(e3, 10, List.of(9, 9, 8, 10)),
        Arguments.of(e4, 5, List.of(6, 7, 5)),
        Arguments.of(e5, 7, List.of(10, 10, 9, 8, 7)),
        Arguments.of(e6, 11, List.of(5))
    );
  }
}
