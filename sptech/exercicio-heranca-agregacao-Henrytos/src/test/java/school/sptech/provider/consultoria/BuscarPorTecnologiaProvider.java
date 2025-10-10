package school.sptech.provider.consultoria;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.DesenvolvedorMobileFactory;
import school.sptech.factory.DesenvolvedorWebFactory;

import java.util.List;
import java.util.stream.Stream;

import static school.sptech.factory.DesenvolvedorFactory.build;

public class BuscarPorTecnologiaProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        Object d1 = build("Paulo", 32, 58.7);
        Object d2 = build("Lívia", 47, 67.3);
        Object d3 = build("Elisa", 34, 45.2);
        Object d4 = build("Yasmin", 45, 49.8);
        Object d5 = build("Tiago", 40, 64.4);

        Object d6 = DesenvolvedorWebFactory.build("Bianca", 32, 68.7, "React", "Django", "PostgreSQL", 12);
        Object d7 = DesenvolvedorWebFactory.build("Enzo", 47, 65.2, "Vue", "Django", "MySQL", 18);
        Object d8 = DesenvolvedorWebFactory.build("Mariana", 34, 52.8, "Angular", "Rails", "MongoDB", 15);
        Object d9 = DesenvolvedorWebFactory.build("Gustavo", 45, 59.5, "Svelte", "Flask", "Oracle", 13);
        Object d10 = DesenvolvedorWebFactory.build("Sofia", 40, 61.3, "Ember", "Laravel", "MySQL", 16);

        Object d11 = DesenvolvedorMobileFactory.build("Leonardo", 50, 44.7, "iOS", "Swift", 10);
        Object d12 = DesenvolvedorMobileFactory.build("Valentina", 30, 67.4, "Android", "Java", 19);
        Object d13 = DesenvolvedorMobileFactory.build("Joaquim", 37, 52.9, "iOS", "Swift", 14);
        Object d14 = DesenvolvedorMobileFactory.build("Alice", 42, 46.5, "Android", "Kotlin", 11);
        Object d15 = DesenvolvedorMobileFactory.build("Rafael", 39, 68.1, "iOS", "Swift", 17);

        return Stream.of(
                Arguments.of("Consultoria 1", 10, List.of(d1, d2, d3, d4, d5), "MySQL", List.of()),
                Arguments.of("Consultoria 2", 10, List.of(d6, d7, d8, d9, d10), "MySQL", List.of(d7, d10)),
                Arguments.of("Consultoria 3", 10, List.of(d11, d12, d13, d14, d15), "iOS", List.of(d11, d13, d15)),
                Arguments.of("Consultoria 4", 10, List.of(d1, d6, d7, d11, d12), "Django", List.of(d6, d7)),
                Arguments.of("Consultoria 5", 10, List.of(d5, d10, d15), "Spring Boot", List.of()),
                Arguments.of("Consultoria 5", 10, List.of(d8, d14, d7), "Angular", List.of(d8)));
    }
}
