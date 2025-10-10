package school.sptech.provider;

import java.time.LocalDateTime;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class IsPassadoProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
              // dataHoraInicio antes de dataHoraFim (true)
              Arguments.of(LocalDateTime.of(2025, 9, 1, 10, 0), LocalDateTime.of(2025, 9, 1, 12, 0), true),
              Arguments.of(LocalDateTime.of(2025, 8, 31, 23, 59), LocalDateTime.of(2025, 9, 1, 0, 0), true),
              Arguments.of(LocalDateTime.of(2025, 1, 1, 0, 0), LocalDateTime.of(2025, 12, 31, 23, 59), true),

              // dataHoraInicio igual a dataHoraFim (false)
              Arguments.of(LocalDateTime.of(2025, 9, 1, 12, 0), LocalDateTime.of(2025, 9, 1, 12, 0), false),

              // dataHoraInicio depois de dataHoraFim (false)
              Arguments.of(LocalDateTime.of(2025, 9, 1, 13, 0), LocalDateTime.of(2025, 9, 1, 12, 0), false),
              Arguments.of(LocalDateTime.of(2025, 12, 31, 23, 59), LocalDateTime.of(2025, 1, 1, 0, 0), false),
              Arguments.of(LocalDateTime.of(2025, 9, 2, 0, 0), LocalDateTime.of(2025, 9, 1, 23, 59), false)
        );
    }
}
