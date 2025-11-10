package school.sptech.provider;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class ConverterParaHexadecimalProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
              // 🔹 Casos básicos
              Arguments.of(0, "0"),
              Arguments.of(1, "1"),
              Arguments.of(9, "9"),
              Arguments.of(10, "A"),
              Arguments.of(11, "B"),
              Arguments.of(12, "C"),
              Arguments.of(13, "D"),
              Arguments.of(14, "E"),
              Arguments.of(15, "F"),

              // 🔹 Limite e múltiplos de 16
              Arguments.of(16, "10"),   // início da base 16
              Arguments.of(17, "11"),
              Arguments.of(31, "1F"),
              Arguments.of(32, "20"),
              Arguments.of(48, "30"),
              Arguments.of(63, "3F"),
              Arguments.of(64, "40"),
              Arguments.of(255, "FF"),

              // 🔹 Valores médios
              Arguments.of(256, "100"),
              Arguments.of(257, "101"),
              Arguments.of(438, "1B6"),
              Arguments.of(512, "200"),
              Arguments.of(1023, "3FF"),
              Arguments.of(1024, "400"),
              Arguments.of(4095, "FFF"),

              // 🔹 Valores altos (para testar várias casas)
              Arguments.of(4096, "1000"),
              Arguments.of(8191, "1FFF"),
              Arguments.of(8192, "2000"),
              Arguments.of(65535, "FFFF"),
              Arguments.of(1048576, "100000"), // 1 MB em bytes
              Arguments.of(16777215, "FFFFFF"), // máximo 24-bit color
              Arguments.of(305419896, "12345678") // padrão clássico de debug
        );
    }
}