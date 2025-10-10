package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class MesclarProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    return Stream.of(
        Arguments.of(new Integer[] {  }, new Integer[] {  }, new Integer[] {  }),
        Arguments.of(new Integer[] { 1, 2, 5, 8 }, new Integer[] { 3, 4, 6 }, new Integer[] { 1, 2, 3, 4, 5, 6, 8 }),
        Arguments.of(new Integer[] { 10, 20, 30 }, new Integer[] { 15, 25, 35 }, new Integer[] { 10, 15, 20, 25, 30, 35 }),
        Arguments.of(new Integer[] { -5, 0, 5 }, new Integer[] { -10, 10 }, new Integer[] { -10, -5, 0, 5, 10 }),
        Arguments.of(new Integer[] { 1, 3, 5, 7, 9 }, new Integer[] { 2, 4, 6, 8, 10 }, new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }),
        Arguments.of(new Integer[] { 100, 200, 300 }, new Integer[] { 150, 250, 350 }, new Integer[] { 100, 150, 200, 250, 300, 350 }),
        Arguments.of(new Integer[] { 0, 2, 4, 6 }, new Integer[] { 1, 3, 5, 7 }, new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7 }),
        Arguments.of(new Integer[] { -3, -1, 1, 3 }, new Integer[] { -2, 0, 2 }, new Integer[] { -3, -2, -1, 0, 1, 2, 3 }),
        Arguments.of(new Integer[] { 5 }, new Integer[] { 10 }, new Integer[] { 5, 10 }),
        Arguments.of(new Integer[] { 1, 4, 7 }, new Integer[] { 2, 5, 8, 11 }, new Integer[] { 1, 2, 4, 5, 7, 8, 11 })
    );
  }
}
