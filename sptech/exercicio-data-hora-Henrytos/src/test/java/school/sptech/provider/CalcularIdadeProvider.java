package school.sptech.provider;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class CalcularIdadeProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
          throws Exception {
        return Stream.of(
              // Aniversário já passou este ano
              Arguments.of("2000-01-01", "2025-09-01", 25),
              Arguments.of("1990-06-15", "2025-09-01", 35),

              // Aniversário ainda não aconteceu este ano
              Arguments.of("2000-12-31", "2025-09-01", 24),
              Arguments.of("1985-10-10", "2025-09-01", 39),

              // Aniversário hoje
              Arguments.of("2000-09-01", "2025-09-01", 25),

              // Nascidos no mesmo dia e mês do ano atual
              Arguments.of("2005-09-01", "2025-09-01", 20)
        );
    }
}
