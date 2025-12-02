package school.sptech.provider.playlist;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class ConstructorProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    return Stream.of(
        Arguments.of("Minha Playlist"),
        Arguments.of("Top Hits 2024"),
        Arguments.of("Rock Clássico"),
        Arguments.of("Pop Internacional"),
        Arguments.of("Jazz e Blues")
    );
  }
}
