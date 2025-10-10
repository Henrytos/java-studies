package school.sptech.provider.carrinho;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.CelularFactory;
import school.sptech.factory.ProdutoFactory;
import school.sptech.factory.RoupaFactory;

import java.util.List;
import java.util.stream.Stream;

public class AdicionarProdutoProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {

    Object p1 = ProdutoFactory.build("Produto A", 50.0, 0.5);
    Object p2 = ProdutoFactory.build("Produto B", 75.0, 1.0);
    Object p3 = ProdutoFactory.build("Produto C", 120.0, 1.5);
    Object p4 = ProdutoFactory.build("Produto D", 200.0, 2.0);
    Object p5 = ProdutoFactory.build("Produto E", 300.0, 2.5);

    Object c1 = CelularFactory.build("Celular A", 1000.0, 0.4, 12, "Marca X");
    Object c2 = CelularFactory.build("Celular B", 800.0, 0.5, 6, "Marca Y");
    Object c3 = CelularFactory.build("Celular C", 1200.0, 0.6, 24, "Marca Z");
    Object c4 = CelularFactory.build("Celular D", 1500.0, 0.7, 18, "Marca W");
    Object c5 = CelularFactory.build("Celular E", 500.0, 0.3, 0, "Marca V");

    Object r1 = RoupaFactory.build("Roupa A", 200.0, 1.0, "Vermelho", 42.0, "algodão");
    Object r2 = RoupaFactory.build("Roupa B", 300.0, 1.2, "Preto", 40.0, "couro");
    Object r3 = RoupaFactory.build("Roupa C", 150.0, 0.8, "Azul", 38.0, "jeans");
    Object r4 = RoupaFactory.build("Roupa D", 500.0, 1.5, "Marrom", 44.0, "couro");
    Object r5 = RoupaFactory.build("Roupa E", 100.0, 0.5, "Branco", 36.0, "seda");

    return Stream.of(
        Arguments.of(1, List.of(p1), c1, List.of(p1, c1)),
        Arguments.of(2, List.of(p2, p3), c2, List.of(p2, p3, c2)),
        Arguments.of(3, List.of(p4, c3), r1, List.of(p4, c3, r1)),
        Arguments.of(4, List.of(c4, r2), r3, List.of(c4, r2, r3)),
        Arguments.of(5, List.of(r4, r5), p5, List.of(r4, r5, p5))
    );
  }
}
