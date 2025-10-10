package school.sptech.provider;

import java.time.LocalDate;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class IsFinalDeSemanaProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
              // Dias da semana (segunda a sexta) → false
              Arguments.of(LocalDate.of(2025, 9, 1), false), // segunda-feira
              Arguments.of(LocalDate.of(2025, 9, 2), false), // terça-feira
              Arguments.of(LocalDate.of(2025, 9, 3), false), // quarta-feira
              Arguments.of(LocalDate.of(2025, 9, 4), false), // quinta-feira
              Arguments.of(LocalDate.of(2025, 9, 5), false), // sexta-feira

              // Finais de semana → true
              Arguments.of(LocalDate.of(2025, 9, 6), true),  // sábado
              Arguments.of(LocalDate.of(2025, 9, 7), true)   // domingo
        );
    }
}
