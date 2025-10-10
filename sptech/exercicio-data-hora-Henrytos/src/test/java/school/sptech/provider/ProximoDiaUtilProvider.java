package school.sptech.provider;

import java.time.LocalDate;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class ProximoDiaUtilProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
              // Já é dia útil (segunda a sexta) → retorna a mesma data
              Arguments.of(LocalDate.of(2025, 9, 1), "2025-09-01"), // segunda-feira
              Arguments.of(LocalDate.of(2025, 9, 2), "2025-09-02"), // terça-feira

              // Sábado → próximo dia útil é segunda-feira
              Arguments.of(LocalDate.of(2025, 9, 6), "2025-09-08"), // sábado
              Arguments.of(LocalDate.of(2025, 9, 13), "2025-09-15"), // sábado

              // Domingo → próximo dia útil é segunda-feira
              Arguments.of(LocalDate.of(2025, 9, 7), "2025-09-08"), // domingo
              Arguments.of(LocalDate.of(2025, 9, 14), "2025-09-15"), // domingo

              // Sexta-feira → dia útil, deve retornar a mesma data
              Arguments.of(LocalDate.of(2025, 9, 5), "2025-09-05") // sexta-feira
        );
    }
}
