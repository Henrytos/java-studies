package school.sptech;

import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import school.sptech.fixture.MusicaFixture;
import school.sptech.fixture.ProdutoFixture;
import school.sptech.fixture.VendasCidadeFixture;

@DisplayName("Leitura Excel")
class LeituraExcelTest {

    static {
        Locale.setDefault(Locale.of("pt", "BR"));
    }

    private static void assertObjEquals(Object expected, Object actual, int index) {
        Assertions.assertTrue(EqualsHelper.equals(actual, expected),
              "\nIndex %d. \nActual: %s. \nExpected: %s".formatted(index, actual, expected));
    }

    private static void assertObjEquals(Object expected, Object actual) {
        Assertions.assertTrue(EqualsHelper.equals(actual, expected),
              "\nActual: %s. \nExpected: %s".formatted(actual, expected));
    }

    @Nested
    @DisplayName("Método - lerMusicas")
    class MetodoLerMusicasTest {

        @ParameterizedTest
        @MethodSource("lerMusicasSource")
        @DisplayName("Deve retornar as musicas do excel")
        void lerMusicasTest(String nomeArquivo, List<Musica> musicasExpected)
              throws ReflectiveOperationException {
            var clazz = LeituraExcel.class;
            var method = clazz.getDeclaredMethod("lerMusicas", String.class);
            var obj = clazz.getDeclaredConstructor().newInstance();
            var result = (List<Musica>) method.invoke(obj, nomeArquivo);

            for (int i = 0; i < result.size(); i++) {
                Musica actual = result.get(i);
                Musica expected = musicasExpected.get(i);
                assertObjEquals(expected, actual, i);
            }
        }

        static Stream<Arguments> lerMusicasSource() {
            return Stream.of(
                  Arguments.of("src/test/resources/musicas.xlsx", MusicaFixture.getMusicas()),
                  Arguments.of("src/test/resources/musicas2.xlsx", MusicaFixture.getMusicasV2())
            );
        }
    }

    @Nested
    @DisplayName("Método - produtoMaisCaro")
    class MetodoProdutoMaisCaroTest {

        @ParameterizedTest
        @MethodSource("produtoMaisCaroSource")
        @DisplayName("Deve retornar o produto mais caro do excel")
        void produtoMaisCaroTest(String nomeArquivo, Produto produtoExpected)
              throws ReflectiveOperationException {
            var clazz = LeituraExcel.class;
            var method = clazz.getDeclaredMethod("produtoMaisCaro", String.class);
            var obj = clazz.getDeclaredConstructor().newInstance();
            var result = method.invoke(obj, nomeArquivo);
            assertObjEquals(produtoExpected, result);
        }

        static Stream<Arguments> produtoMaisCaroSource() {
            return Stream.of(
                  Arguments.of("src/test/resources/produtos.xlsx", ProdutoFixture.getProduto()),
                  Arguments.of("src/test/resources/produtos2.xlsx", ProdutoFixture.getProduto2())
            );
        }
    }

    @Nested
    @DisplayName("Método - faturamentoTotal")
    class MetodoFaturamentoTotalTest {

        @ParameterizedTest
        @MethodSource("faturamentoTotalSource")
        @DisplayName("Deve retornar o faturamento total das vendas")
        void faturamentoTotalTest(String nomeArquivo, Double valorExpected)
              throws ReflectiveOperationException {
            var clazz = LeituraExcel.class;
            var method = clazz.getDeclaredMethod("faturamentoTotal", String.class);
            var obj = clazz.getDeclaredConstructor().newInstance();
            var result = (Double) method.invoke(obj, nomeArquivo);
            Assertions.assertEquals(valorExpected, result, 0.01);
        }

        static Stream<Arguments> faturamentoTotalSource() {
            return Stream.of(
                  Arguments.of("src/test/resources/vendas.xlsx", 10028.11),
                  Arguments.of("src/test/resources/vendas2.xlsx", 17004.86)
            );
        }
    }

    @Nested
    @DisplayName("Método - faturamentoPorCidade")
    class MetodoFaturamentoPorCidadeTest {

        @ParameterizedTest
        @MethodSource("faturamentoPorCidadeSource")
        @DisplayName("Deve retornar o faturamento total das vendas por cidade")
        void faturamentoPorCidade(String nomeArquivo, List<VendasCidade> valorExpected)
              throws ReflectiveOperationException {
            var clazz = LeituraExcel.class;
            var method = clazz.getDeclaredMethod("faturamentoPorCidade", String.class);
            var obj = clazz.getDeclaredConstructor().newInstance();
            var result = (List<VendasCidade>) method.invoke(obj, nomeArquivo);

            if (result == null) {
                Assertions.fail("Deve retornar uma lista.");
            }
            if (result.size() != valorExpected.size()) {
                Assertions.fail("Deve retornar uma lista com %d objetos. Atual: %d"
                      .formatted(valorExpected.size(), result.size()));
            }
            for (int i = 0; i < result.size(); i++) {
                VendasCidade actual = result.get(i);
                VendasCidade expected = valorExpected.get(i);
                assertObjEquals(expected, actual, i);
            }
        }

        static Stream<Arguments> faturamentoPorCidadeSource() {
            return Stream.of(
                  Arguments.of("src/test/resources/vendas.xlsx", VendasCidadeFixture.getVendasCidade()),
                  Arguments.of("src/test/resources/vendas2.xlsx", VendasCidadeFixture.getVendasCidade2())
            );
        }
    }
}
