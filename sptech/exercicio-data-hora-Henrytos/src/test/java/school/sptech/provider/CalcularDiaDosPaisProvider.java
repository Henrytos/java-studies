package school.sptech.provider;

import java.time.LocalDate;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class CalcularDiaDosPaisProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(2023, LocalDate.of(2023, 8, 13)),
                Arguments.of(2024, LocalDate.of(2024, 8, 11)),
                Arguments.of(2025, LocalDate.of(2025, 8, 10)),
                Arguments.of(2026, LocalDate.of(2026, 8, 9)),
                Arguments.of(2027, LocalDate.of(2027, 8, 8)),
                Arguments.of(2028, LocalDate.of(2028, 8, 13)),
                Arguments.of(2029, LocalDate.of(2029, 8, 12))
        );
    }
}
