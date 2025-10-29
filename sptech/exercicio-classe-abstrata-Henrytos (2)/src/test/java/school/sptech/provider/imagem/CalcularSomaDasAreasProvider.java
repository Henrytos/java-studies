package school.sptech.provider.imagem;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.CirculoFactory;
import school.sptech.factory.QuadradoFactory;
import school.sptech.factory.RetanguloFactory;
import school.sptech.factory.TrianguloFactory;

import java.util.List;
import java.util.stream.Stream;

public class CalcularSomaDasAreasProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    Object d1 = QuadradoFactory.build("Amarelo", 2, 10.0); // 100
    Object d2 = QuadradoFactory.build("Azul", 3, 20.0); // 400
    Object d3 = QuadradoFactory.build("Vermelho", 1, 15.0); // 225
    Object d4 = QuadradoFactory.build("Verde", 4, 25.0); // 625
    Object d5 = QuadradoFactory.build("Preto", 5, 30.0); // 900

    Object d6 = RetanguloFactory.build("Amarelo", 2, 10.0, 5.0); // 50
    Object d7 = RetanguloFactory.build("Azul", 3, 20.0, 10.0); // 200
    Object d8 = RetanguloFactory.build("Vermelho", 1, 15.0, 7.5); // 112.5
    Object d9 = RetanguloFactory.build("Verde", 4, 25.0, 12.5); // 312.5
    Object d10 = RetanguloFactory.build("Preto", 5, 30.0, 15.0); // 450

    Object d11 = TrianguloFactory.build("Amarelo", 2, 10.0, 5.0); // 25
    Object d12 = TrianguloFactory.build("Azul", 3, 20.0, 10.0); // 100
    Object d13 = TrianguloFactory.build("Vermelho", 1, 15.0, 7.5); // 56.25
    Object d14 = TrianguloFactory.build("Verde", 4, 25.0, 12.5); // 156.25
    Object d15 = TrianguloFactory.build("Preto", 5, 30.0, 15.0); // 225

    Object d16 = CirculoFactory.build("Amarelo", 2, 10.0); // 125.6
    Object d17 = CirculoFactory.build("Azul", 3, 20.0);   // 314.0
    Object d18 = CirculoFactory.build("Vermelho", 1, 15.0); // 706.5
    Object d19 = CirculoFactory.build("Verde", 4, 25.0); // 1963.5
    Object d20 = CirculoFactory.build("Preto", 5, 30.0); // 2827.5

    return Stream.of(
        Arguments.of(List.of(d1, d2, d3, d4, d5), 2250.0),
        Arguments.of(List.of(d6, d7, d8, d9, d10), 1125.0),
        Arguments.of(List.of(d11, d12, d13, d14, d15), 562.5),
        Arguments.of(List.of(d16, d17, d18, d19, d20),7068.58),
        Arguments.of(List.of(d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16, d17, d18, d19, d20), 11006.08),
        Arguments.of(List.of(), 0.0),
        Arguments.of(List.of(d1), 100.0),
        Arguments.of(List.of(d6), 50.0)
    );
  }
}
