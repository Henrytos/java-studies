package school.sptech.provider;

import java.time.LocalDateTime;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class FormatarDataHoraProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
              Arguments.of(LocalDateTime.of(2025, 1, 1, 0, 0, 0, 123_000_000),
                    "01/01/2025 12:00:00 AM (nanosegundos: 123)"),
              Arguments.of(LocalDateTime.of(2025, 1, 1, 12, 0, 0, 456_000_000),
                    "01/01/2025 12:00:00 PM (nanosegundos: 456)"),
              Arguments.of(LocalDateTime.of(2025, 6, 15, 9, 30, 15, 789_000_000),
                    "06/15/2025 09:30:15 AM (nanosegundos: 789)"),
              Arguments.of(LocalDateTime.of(2025, 6, 15, 15, 45, 45, 12_000_000),
                    "06/15/2025 03:45:45 PM (nanosegundos: 012)"),
              Arguments.of(LocalDateTime.of(2025, 12, 31, 23, 59, 59, 999_000_000),
                    "12/31/2025 11:59:59 PM (nanosegundos: 999)"),
              Arguments.of(LocalDateTime.of(2025, 2, 28, 6, 5, 5, 1_000_000),
                    "02/28/2025 06:05:05 AM (nanosegundos: 001)"),
              Arguments.of(LocalDateTime.of(2025, 11, 30, 21, 15, 30, 250_000_000),
                    "11/30/2025 09:15:30 PM (nanosegundos: 250)")
        );
    }
}
