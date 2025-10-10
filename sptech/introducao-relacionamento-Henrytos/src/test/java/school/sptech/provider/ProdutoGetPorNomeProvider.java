package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.Produto;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ProdutoGetPorNomeProvider implements ArgumentsProvider {

  private Map<String, Field> campos() throws ReflectiveOperationException {
    Class<Produto> clazz = Produto.class;

    Field nome = clazz.getDeclaredField("nome");
    nome.trySetAccessible();

    Field categoria = clazz.getDeclaredField("categoria");
    categoria.trySetAccessible();

    Field preco = clazz.getDeclaredField("preco");
    preco.trySetAccessible();

    return Map.of("nome", nome, "categoria", categoria, "preco", preco);
  }

  private Object novoProduto(String nome, String categoria, Double preco) throws ReflectiveOperationException {
    Object produto = new Produto();
    campos().get("nome").set(produto, nome);
    campos().get("categoria").set(produto, categoria);
    campos().get("preco").set(produto, preco);

    return produto;
  }

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    Object p1 = this.novoProduto("Smartphone XYZ", "Eletrônicos", 1500.00);
    Object p2 = this.novoProduto("Tênis de Corrida Speed", "Vestuário", 299.99);
    Object p3 = this.novoProduto("Cafeteira Expresso Plus", "Eletrodomésticos", 489.90);
    Object p4 = this.novoProduto("Fone de Ouvido Bluetooth", "Acessórios", 259.90);
    Object p5 = this.novoProduto("Notebook Gamer Alpha", "Informática", 5800.00);
    Object p6 = this.novoProduto("Mochila Montanha 60L", "Esporte e Lazer", 350.00);
    Object p7 = this.novoProduto("Perfume Elegance 50ml", "Beleza e Perfumaria", 210.00);
    Object p8 = this.novoProduto("Relógio SmartTech", "Acessórios", 1200.00);
    Object p9 = this.novoProduto("Cadeira Gamer Confort", "Móveis", 899.99);
    Object p10 = this.novoProduto("Livro - O Mundo de Sofia", "Livros", 59.90);

    return Stream.of(
        Arguments.of(List.of(p7, p2, p4), "Fone de Ouvido Bluetooth", p4),
        Arguments.of(List.of(p3, p8, p1, p10), "Smartphone XYZ", p1),
        Arguments.of(List.of(p9, p5), "Smartphone XYZ", null),
        Arguments.of(List.of(p2, p7, p3, p1, p4, p6), "Livro - O Mundo de Sofia", null),
        Arguments.of(List.of(p8), "Relógio SmartTech", p8)
    );
  }
}
