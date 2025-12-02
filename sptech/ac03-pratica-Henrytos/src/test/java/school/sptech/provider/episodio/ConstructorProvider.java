package school.sptech.provider.episodio;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class ConstructorProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {

    return Stream.of(
        Arguments.of("Episodio 1", 30.0, "Podcast A"),
        Arguments.of("Episodio 2", 45.5, "Podcast B"),
        Arguments.of("Episodio 3", 60.0, "Podcast C"),
        Arguments.of("Episodio 4", 15.0, "Podcast D"),
        Arguments.of("Episodio 5", 25.5, "Podcast E")
    );
  }
}
