package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.Produto;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ProdutoQuantidadePorCategoriaProvider implements ArgumentsProvider {

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
    Object p11 = this.novoProduto("Tablet SuperView 8", "Eletrônicos", 1200.00);
    Object p12 = this.novoProduto("Jaqueta Corta Vento", "Vestuário", 199.99);
    Object p13 = this.novoProduto("Liquidificador PowerMix", "Eletrodomésticos", 289.90);
    Object p14 = this.novoProduto("Caixa de Som Portátil", "Acessórios", 349.90);
    Object p15 = this.novoProduto("PC Desktop Office Pro", "Informática", 3200.00);
    Object p16 = this.novoProduto("Barraca Iglu 4 Pessoas", "Esporte e Lazer", 450.00);
    Object p17 = this.novoProduto("Batom Matte Luxo", "Beleza e Perfumaria", 45.00);
    Object p18 = this.novoProduto("Pulseira de Atividade", "Acessórios", 599.99);
    Object p19 = this.novoProduto("Escrivaninha Studio", "Móveis", 759.99);
    Object p20 = this.novoProduto("Livro - A Arte da Guerra", "Livros", 29.90);
    Object p21 = this.novoProduto("Smartwatch HealthPro", "Eletrônicos", 899.00);
    Object p22 = this.novoProduto("Caixa de Som WiFi", "Eletrônicos", 479.00);
    Object p23 = this.novoProduto("Drone Explorer Vision", "Eletrônicos", 1500.00);
    Object p24 = this.novoProduto("Sofá Retrátil Luxo", "Móveis", 2500.00);
    Object p25 = this.novoProduto("Estante de Livros", "Móveis", 899.99);

    List<Object> amostra1 = List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20);
    List<Object> amostra2 = List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10);
    List<Object> amostra3 = List.of(p1, p2, p3, p4, p5, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20);
    List<Object> amostra4 = List.of(p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25);

    return Stream.of(
        Arguments.of(amostra1, "Eletrônicos", 2),
        Arguments.of(amostra2, "Eletrônicos", 1),
        Arguments.of(amostra1, "Acessórios", 4),
        Arguments.of(amostra2, "Acessórios", 2),
        Arguments.of(amostra3, "Fotografia", 0),
        Arguments.of(amostra3, "Vestuário", 2),
        Arguments.of(amostra4, "Eletrônicos", 4),
        Arguments.of(amostra4, "Móveis", 3)
    );
  }
}
