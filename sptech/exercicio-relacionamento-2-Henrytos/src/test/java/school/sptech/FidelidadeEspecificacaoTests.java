package school.sptech;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import school.sptech.auxiliar.Metodo;
import school.sptech.utils.ContaCorrenteUtils;
import school.sptech.utils.OperacaoBancariaUtils;

import java.util.List;

@DisplayName("Fidelidade a especificação")
public class FidelidadeEspecificacaoTests {

    @Nested
    @DisplayName("OperacaoBancaria")
    class OperacaoTests {

        @Nested
        @DisplayName("Todos os atributos de OperacaoBancaria devem respeitar os nomes especificados")
        class TodosAtributosOperacaoComoEspecificadosTests {

            @Test
            @DisplayName("Cenário Único")
            void test1() {

                List<String> atributos = List.of(
                        OperacaoBancariaUtils.ESPECIFICACAO_ATRIBUTO_VALOR,
                        OperacaoBancariaUtils.ESPECIFICACAO_ATRIBUTO_CATEGORIA,
                        OperacaoBancariaUtils.ESPECIFICACAO_ATRIBUTO_DESCRICAO
                );

                Class<Operacao> clazz = Operacao.class;

                for (String atributo : atributos) {

                    Assertions.assertDoesNotThrow(() -> {
                        clazz.getDeclaredField(atributo);
                    });
                }
            }
        }
    }

    @Nested
    @DisplayName("ContaCorrente")
    class ContaCorrenteTests {

        @Nested
        @DisplayName("Todos os atributos de ContaCorrente devem respeitar os nomes especificados")
        class TodosAtributosContaCorrenteComoEspecificadosTests {

            @Test
            @DisplayName("Cenário Único")
            void test1() {

                List<String> atributos = List.of(
                        ContaCorrenteUtils.ESPECIFICACAO_ATRIBUTO_TITULAR,
                        ContaCorrenteUtils.ESPECIFICACAO_ATRIBUTO_NUMERO,
                        ContaCorrenteUtils.ESPECIFICACAO_ATRIBUTO_AGENCIA,
                        ContaCorrenteUtils.ESPECIFICACAO_ATRIBUTO_OPERACOES
                );

                Class<ContaCorrente> clazz = ContaCorrente.class;

                for (String atributo : atributos) {

                    Assertions.assertDoesNotThrow(() -> {
                        clazz.getDeclaredField(atributo);
                    });
                }
            }
        }

        @Nested
        @DisplayName("Todos os métodos de ContaCorrente devem respeitar os nomes e parâmetros especificados")
        class TodosMetodosContaCorrenteComoEspecificadosTests {

            @Test
            @DisplayName("Cenário Único")
            void test1() {

                List<Metodo> metodos = List.of(
                        ContaCorrenteUtils.ESPECIFICACAO_METODO_ADICIONAR_OPERACAO,
                        ContaCorrenteUtils.ESPECIFICACAO_METODO_BUSCAR_OPERACOES_POR_CATEGORIA,
                        ContaCorrenteUtils.ESPECIFICACAO_METODO_BUSCAR_OPERACOES_POR_VALOR,
                        ContaCorrenteUtils.ESPECIFICACAO_METODO_BUSCAR_OPERACOES_SAIDA,
                        ContaCorrenteUtils.ESPECIFICACAO_METODO_BUSCAR_OPERACOES_ENTRADA,
                        ContaCorrenteUtils.ESPECIFICACAO_METODO_BUSCAR_OPERACOES_POR_DESCRICAO,
                        ContaCorrenteUtils.ESPECIFICACAO_METODO_BUSCAR_MAIOR_VALOR,
                        ContaCorrenteUtils.ESPECIFICAO_METODO_BUSCAR_MENOR_VALOR,
                        ContaCorrenteUtils.ESPECIFICACAO_METODO_OBTER_SALDO
                );

                Class<ContaCorrente> clazz = ContaCorrente.class;

                for (Metodo metodo : metodos) {

                    Assertions.assertDoesNotThrow(() -> {
                        clazz.getDeclaredMethod(metodo.getNome(), metodo.getArgs());
                    });
                }
            }
        }
    }
}
