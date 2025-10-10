package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class VerificarPalindromoProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
          throws Exception {
        return Stream.of(
              Arguments.of("ovo", true),
              Arguments.of("ana", true),
              Arguments.of("Ana", true),
              Arguments.of("arara", true),
              Arguments.of("reviver", true),
              Arguments.of("A torre da derrota", true),
              Arguments.of("Anotaram a data da maratona", true),
              Arguments.of("socorram me subi no onibus em marrocos", true),
              Arguments.of("Java, a melhor linguagem", false),
              Arguments.of("socorram subi no onibus", false),
              Arguments.of("SPTech", false),
              Arguments.of("dentista", false)
        );
    }
}
