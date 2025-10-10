package school.sptech.provider.desenvolvedorweb;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class IsFullstackProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    return Stream.of(
        Arguments.of("Bianca", 32, 68.7, "React", null, "PostgreSQL", 12, false),
        Arguments.of("Enzo", 47, 65.2, "Vue", "Express", "MySQL", 18, true),
        Arguments.of("Mariana", 34, 52.8, "Angular", "Rails", "MongoDB", 15, true),
        Arguments.of("Gustavo", 45, 59.5, "Svelte", "Flask", null, 13, false),
        Arguments.of("Sofia", 40, 61.3, null, "Laravel", "SQL Server", 16, false),
        Arguments.of("Leonardo", 50, 44.7, "Next.js", ".NET Core", "Redis", 10, true),
        Arguments.of("Valentina", 30, 67.4, "React", null, null, 19, false),
        Arguments.of("Joaquim", 37, 52.9, null, "Node.js", "MySQL", 14, false),
        Arguments.of("Alice", 42, 46.5, "React", "Spring Boot", "PostgreSQL", 11, true),
        Arguments.of("Rafael", 39, 68.1, "Svelte", "Django", "MariaDB", 17, true)
    );
  }
}
