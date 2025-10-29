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

public class BuscarPorAreaMaiorQue20Provider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    Object d1 = QuadradoFactory.build("Amarelo", 2, 1.0); // 1
    Object d2 = QuadradoFactory.build("Azul", 3, 2.0); // 4
    Object d3 = QuadradoFactory.build("Vermelho", 1, 1.50); // 2.25
    Object d4 = QuadradoFactory.build("Verde", 4, 2.50); // 6.25
    Object d5 = QuadradoFactory.build("Preto", 5, 3.00); // 9

    Object d6 = RetanguloFactory.build("Amarelo", 2, 1.0, 5.0); // 5
    Object d7 = RetanguloFactory.build("Azul", 3, 2.0, 10.0); // 20
    Object d8 = RetanguloFactory.build("Vermelho", 1, 1.5, 7.5); // 11.25
    Object d9 = RetanguloFactory.build("Verde", 4, 2.5, 12.5); // 31.25
    Object d10 = RetanguloFactory.build("Preto", 5, 3.0, 15.0); // 45

    Object d11 = TrianguloFactory.build("Amarelo", 2, 1.0, 5.0); // 2.5
    Object d12 = TrianguloFactory.build("Azul", 3, 2.0, 10.0); // 10
    Object d13 = TrianguloFactory.build("Vermelho", 1, 1.5, 7.5); // 5.625
    Object d14 = TrianguloFactory.build("Verde", 4, 2.5, 12.5); // 15.625
    Object d15 = TrianguloFactory.build("Preto", 5, 3.0, 15.0); // 22.5

    Object d16 = CirculoFactory.build("Amarelo", 2, 1.0); // 3.14
    Object d17 = CirculoFactory.build("Azul", 3, 2.0);   // 12.56
    Object d18 = CirculoFactory.build("Vermelho", 1, 1.5); // 7.065
    Object d19 = CirculoFactory.build("Verde", 4, 2.5); // 19.635
    Object d20 = CirculoFactory.build("Preto", 5, 3.0); // 28.27

    return Stream.of(
        Arguments.of(List.of(d1, d9, d15, d20, d10), List.of(d9, d15, d20, d10)),
        Arguments.of(List.of(d2, d7, d12, d17), List.of()),
        Arguments.of(List.of(d3, d8, d13, d18), List.of()),
        Arguments.of(List.of(d4, d9, d14, d19), List.of(d9)),
        Arguments.of(List.of(d5, d10, d15, d20), List.of(d10, d15, d20))
    );
  }
}
