package school.sptech.provider.desenvolvedorweb;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class CalcularSalarioProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
    return Stream.of(
        Arguments.of("Bianca", 32, 68.7, "React", "Django", "PostgreSQL", 12, 5798.4),
        Arguments.of("Enzo", 47, 65.2, "Vue", "Express", "MySQL", 18, 8464.4),
        Arguments.of("Mariana", 34, 52.8, "Angular", "Rails", "MongoDB", 15, 6295.2),
        Arguments.of("Gustavo", 45, 59.5, "Svelte", "Flask", "Oracle", 13, 6577.5),
        Arguments.of("Sofia", 40, 61.3, "Ember", "Laravel", "SQL Server", 16, 7252.0),
        Arguments.of("Leonardo", 50, 44.7, "Next.js", ".NET Core", "Redis", 10, 5235.0),
        Arguments.of("Valentina", 30, 67.4, "React", "Spring Boot", "Cassandra", 19, 7722.0),
        Arguments.of("Joaquim", 37, 52.9, "Vue", "Node.js", "MySQL", 14, 6157.3),
        Arguments.of("Alice", 42, 46.5, "React", "Phoenix", "PostgreSQL", 11, 5253.0),
        Arguments.of("Rafael", 39, 68.1, "Svelte", "Django", "MariaDB", 17, 7755.9)
    );
  }
}
