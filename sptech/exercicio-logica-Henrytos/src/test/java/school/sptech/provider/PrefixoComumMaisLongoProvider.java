package school.sptech.provider;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class PrefixoComumMaisLongoProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(
          org.junit.jupiter.api.extension.ExtensionContext context) {
        return Stream.of(
              // Caso básico com prefixo comum
              Arguments.of(new String[]{"flower", "flow", "flight"}, "fl"),

              // Nenhum prefixo comum
              Arguments.of(new String[]{"dog", "racecar", "car"}, ""),

              // Prefixo longo
              Arguments.of(new String[]{"interspecies", "interstellar", "interstate"}, "inters"),

              // Prefixo é exatamente uma palavra
              Arguments.of(new String[]{"apple", "app", "application"}, "app"),

              // Todas as palavras são iguais
              Arguments.of(new String[]{"test", "test", "test"}, "test"),

              // Apenas uma palavra
              Arguments.of(new String[]{"alone"}, "alone"),

              // Uma palavra vazia no meio
              Arguments.of(new String[]{"prefix", "", "prelude"}, ""),

              // Mistura de palavras com apenas uma letra em comum
              Arguments.of(new String[]{"a", "ab", "abc"}, "a"),

              // Nenhuma letra em comum
              Arguments.of(new String[]{"x", "y", "z"}, ""),

              // Palavras com tamanhos diferentes, mas mesmo prefixo
              Arguments.of(new String[]{"re", "read", "reader", "reading"}, "re"),

              // Prefixo comum de apenas uma letra
              Arguments.of(new String[]{"sun", "sand", "sad"}, "s"),

              // Palavras com caracteres repetidos
              Arguments.of(new String[]{"aaaa", "aa", "aaa"}, "aa"),

              // Palavras que divergem logo após um prefixo curto
              Arguments.of(new String[]{"transmit", "transport", "transition"}, "trans"),

              // Palavras com números misturados
              Arguments.of(new String[]{"code123", "code456", "code789"}, "code"),

              // Caso totalmente vazio
              Arguments.of(new String[]{}, "")
        );
    }

}