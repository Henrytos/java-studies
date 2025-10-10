package school.sptech.provider;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class GerarReunioesSemanaisProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {

        return Stream.of(
              // 1. Apenas 1 dia no intervalo (segunda-feira)
              Arguments.of(
                    LocalDate.of(2025, 9, 1),
                    LocalDate.of(2025, 9, 1),
                    List.of(1),
                    List.of(LocalDate.of(2025, 9, 1))
              ),

              // 2. Intervalo pequeno com terças e quintas
              Arguments.of(
                    LocalDate.of(2025, 9, 1),
                    LocalDate.of(2025, 9, 15),
                    List.of(2, 4),
                    List.of(
                          LocalDate.of(2025, 9, 2),
                          LocalDate.of(2025, 9, 4),
                          LocalDate.of(2025, 9, 9),
                          LocalDate.of(2025, 9, 11)
                    )
              ),

              // 3. Intervalo de 10 dias pegando domingos
              Arguments.of(
                    LocalDate.of(2025, 9, 1),
                    LocalDate.of(2025, 9, 10),
                    List.of(7),
                    List.of(LocalDate.of(2025, 9, 7))
              ),

              // 4. Segundas e domingos em 10 dias
              Arguments.of(
                    LocalDate.of(2025, 9, 1),
                    LocalDate.of(2025, 9, 10),
                    List.of(1, 7),
                    List.of(
                          LocalDate.of(2025, 9, 1),
                          LocalDate.of(2025, 9, 7),
                          LocalDate.of(2025, 9, 8)
                    )
              ),

              // 5. Nenhum dia bate (escolhendo sábado, mas só pegando dias de semana)
              Arguments.of(
                    LocalDate.of(2025, 9, 1),
                    LocalDate.of(2025, 9, 4),
                    List.of(6),
                    List.of()
              ),

              // 6. Intervalo de 1 mês inteiro, apenas quartas
              Arguments.of(
                    LocalDate.of(2025, 9, 1),
                    LocalDate.of(2025, 9, 30),
                    List.of(3),
                    List.of(
                          LocalDate.of(2025, 9, 3),
                          LocalDate.of(2025, 9, 10),
                          LocalDate.of(2025, 9, 17),
                          LocalDate.of(2025, 9, 24)
                    )
              ),

              // 7. Intervalo cruzando dois meses, todas as sextas
              Arguments.of(
                    LocalDate.of(2025, 8, 25),
                    LocalDate.of(2025, 9, 10),
                    List.of(5),
                    List.of(
                          LocalDate.of(2025, 8, 29),
                          LocalDate.of(2025, 9, 5)
                    )
              )
        );
    }
}
