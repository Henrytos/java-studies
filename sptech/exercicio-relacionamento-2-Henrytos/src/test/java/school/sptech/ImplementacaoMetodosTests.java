package school.sptech;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import school.sptech.factory.ContaCorrenteFactory;
import school.sptech.factory.OperacaoBancariaFactory;
import school.sptech.utils.ContaCorrenteUtils;
import school.sptech.utils.OperacaoBancariaUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Implementação dos métodos")
public class ImplementacaoMetodosTests {

    @Nested
    @DisplayName("1 - Adicionar operação")
    class AdicionarOperacaoTests {

        @Test
        @DisplayName("Cenário 1 - Adicionar operação com categoria, descrição e valor válidos")
        void test1() throws NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {

            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);
            campoOperacoes.setAccessible(true);

            Method adicionarOperacao = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_ADICIONAR_OPERACAO, String.class, String.class, Double.class);

            adicionarOperacao.invoke(contaCorrente, "Alimentacao", "Mercado x", 100.0);

            List<?> operacoes = (List<?>) campoOperacoes.get(contaCorrente);

            assertEquals(1, operacoes.size());
        }

        @Test
        @DisplayName("Cenário 2 - Adicionar operação com categoria inválida")
        void test2() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {
            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);
            campoOperacoes.setAccessible(true);

            Method adicionarOperacao = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_ADICIONAR_OPERACAO, String.class, String.class, Double.class);

            adicionarOperacao.invoke(contaCorrente, " ", "Mercado x", -100.0);

            List<?> operacoes = (List<?>) campoOperacoes.get(contaCorrente);

            assertEquals(0, operacoes.size());
        }

        @Test
        @DisplayName("Cenário 3 - Adicionar operação com descrição inválida")
        void test3() throws NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);
            campoOperacoes.setAccessible(true);

            Method adicionarOperacao = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_ADICIONAR_OPERACAO, String.class, String.class, Double.class);

            adicionarOperacao.invoke(contaCorrente, "Gasto fixo", " ", 100.0);

            List<?> operacoes = (List<?>) campoOperacoes.get(contaCorrente);

            assertEquals(0, operacoes.size());
        }

        @Test
        @DisplayName("Cenário 4 - Adicionar operação com valor inválido")
        void test4() throws NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);
            campoOperacoes.setAccessible(true);

            Method adicionarOperacao = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_ADICIONAR_OPERACAO, String.class, String.class, Double.class);

            adicionarOperacao.invoke(contaCorrente, "Gasto fixo", "Comércio do seu Jóse", null);

            List<?> operacoes = (List<?>) campoOperacoes.get(contaCorrente);

            assertEquals(0, operacoes.size());
        }

        @Test
        @DisplayName("Cenário 5 - Adicionar operação com categoria, descrição e valor inválidos")
        void test5() throws NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);
            campoOperacoes.setAccessible(true);

            Method adicionarOperacao = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_ADICIONAR_OPERACAO, String.class, String.class, Double.class);

            adicionarOperacao.invoke(contaCorrente, " ", "", null);

            List<?> operacoes = (List<?>) campoOperacoes.get(contaCorrente);

            assertEquals(0, operacoes.size());
        }

        @Test
        @DisplayName("Cenário 6 - Adicionar operação com categoria, descrição e valor nulos")
        void test6() throws NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);
            campoOperacoes.setAccessible(true);

            Method adicionarOperacao = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_ADICIONAR_OPERACAO, String.class, String.class, Double.class);

            adicionarOperacao.invoke(contaCorrente, null, null, null);

            List<?> operacoes = (List<?>) campoOperacoes.get(contaCorrente);

            assertEquals(0, operacoes.size());
        }

        @Test
        @DisplayName("Cenário 7 - Adicionar operação com valor zero")
        void test7() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {
            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);
            campoOperacoes.setAccessible(true);

            Method adicionarOperacao = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_ADICIONAR_OPERACAO, String.class, String.class, Double.class);

            adicionarOperacao.invoke(contaCorrente, "Alimentacao", "Comercio Dona Maria", 0.0);

            List<?> operacoes = (List<?>) campoOperacoes.get(contaCorrente);

            assertEquals(0, operacoes.size());
        }

    }

    @Nested
    @DisplayName("2 - Buscar operações por categoria")
    class BuscarOperacoesPorCategoriaTests {

        @Test
        @DisplayName("Cenário 1 - Buscar operações por categoria válida")
        void test1() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {

            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");

            Object operacao1 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Dona Maria", 17.50);
            Object operacao2 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Agua", 179.0);
            Object operacao3 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Seu João", 50.0);
            Object operacao4 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Energia", 234.0);

            List<Object> operacoes = List.of(operacao1, operacao2, operacao3, operacao4);

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);

            campoOperacoes.setAccessible(true);

            campoOperacoes.set(contaCorrente, operacoes);

            Method buscarOperacoesPorCategoria = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_BUSCAR_OPERACOES_POR_CATEGORIA, String.class);

            List<?> result = (List<?>) buscarOperacoesPorCategoria.invoke(contaCorrente, "Alimentacao");

            assertEquals(2, result.size());
        }

        @Test
        @DisplayName("Cenário 2 - Buscar operações por categoria inválida")
        void test2() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {

            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");

            Object operacao1 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Dona Maria", 17.50);
            Object operacao2 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Agua", 179.0);
            Object operacao3 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Seu João", 50.0);
            Object operacao4 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Energia", 234.0);
            Object operacao5 = OperacaoBancariaFactory.getInstance("Lazer", "Cinema", 50.0);

            List<Object> operacoes = List.of(operacao1, operacao2, operacao3, operacao4, operacao5);

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);

            campoOperacoes.setAccessible(true);

            campoOperacoes.set(contaCorrente, operacoes);

            Method buscarOperacoesPorCategoria = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_BUSCAR_OPERACOES_POR_CATEGORIA, String.class);

            List<?> result = (List<?>) buscarOperacoesPorCategoria.invoke(contaCorrente, " ");

            assertEquals(0, result.size());
        }

        @Test
        @DisplayName("Cenário 3 - Buscar operações por categoria nula")
        void test3() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {

            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");

            Object operacao1 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Dona Maria", 17.50);
            Object operacao2 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Agua", 179.0);
            Object operacao3 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Seu João", 50.0);
            Object operacao4 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Energia", 234.0);

            List<Object> operacoes = List.of(operacao1, operacao2, operacao3, operacao4);

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);

            campoOperacoes.setAccessible(true);

            campoOperacoes.set(contaCorrente, operacoes);

            Method buscarOperacoesPorCategoria = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_BUSCAR_OPERACOES_POR_CATEGORIA, String.class);

            List<?> result = (List<?>) buscarOperacoesPorCategoria.invoke(contaCorrente, (Object) null);

            assertEquals(0, result.size());
        }

        @Test
        @DisplayName("Cenário 4 - Buscar operações por categoria e não encontrar nenhuma")
        void test4() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {

            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");

            Object operacao1 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Dona Maria", 17.50);
            Object operacao2 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Agua", 179.0);
            Object operacao3 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Seu João", 50.0);
            Object operacao4 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Energia", 234.0);

            List<Object> operacoes = List.of(operacao1, operacao2, operacao3, operacao4);

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);

            campoOperacoes.setAccessible(true);

            campoOperacoes.set(contaCorrente, operacoes);

            Method buscarOperacoesPorCategoria = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_BUSCAR_OPERACOES_POR_CATEGORIA, String.class);

            List<?> result = (List<?>) buscarOperacoesPorCategoria.invoke(contaCorrente, "Lazer");

            assertEquals(0, result.size());
        }
    }

    @Nested
    @DisplayName("3 - Buscar operações por valor")
    class BuscarOperacoesPorValorTests {

        @Test
        @DisplayName("Cenário 1 - Buscar operações por valor válido")
        void test1() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {

            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");

            Object operacao1 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Dona Maria", 17.50);
            Object operacao2 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Agua", 179.0);
            Object operacao3 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Seu João", 50.0);
            Object operacao4 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Energia", 234.0);

            List<Object> operacoes = List.of(operacao1, operacao2, operacao3, operacao4);

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);

            campoOperacoes.setAccessible(true);

            campoOperacoes.set(contaCorrente, operacoes);

            Method buscarOperacoesPorValor = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_BUSCAR_OPERACOES_POR_VALOR, Double.class);

            List<?> result = (List<?>) buscarOperacoesPorValor.invoke(contaCorrente, 17.50);

            assertEquals(1, result.size());
        }

        @Test
        @DisplayName("Cenário 2 - Buscar operações por valor inválido")
        void test2() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {

            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");

            Object operacao1 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Dona Maria", 17.50);
            Object operacao2 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Agua", 179.0);
            Object operacao3 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Seu João", 50.0);
            Object operacao4 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Energia", 234.0);
            Object operacao5 = OperacaoBancariaFactory.getInstance("Lazer", "Cinema", 50.0);

            List<Object> operacoes = List.of(operacao1, operacao2, operacao3, operacao4, operacao5);

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);

            campoOperacoes.setAccessible(true);

            campoOperacoes.set(contaCorrente, operacoes);

            Method buscarOperacoesPorValor = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_BUSCAR_OPERACOES_POR_VALOR, Double.class);

            List<?> result = (List<?>) buscarOperacoesPorValor.invoke(contaCorrente, 0.0);

            assertEquals(0, result.size());
        }

        @Test
        @DisplayName("Cenário 3 - Buscar operações por valor nulo")
        void test3() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {

            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");

            Object operacao1 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Dona Maria", 17.50);
            Object operacao2 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Agua", 179.0);
            Object operacao3 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Seu João", 50.0);
            Object operacao4 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Energia", 234.0);
            Object operacao5 = OperacaoBancariaFactory.getInstance("Lazer", "Cinema", 50.0);

            List<Object> operacoes = List.of(operacao1, operacao2, operacao3, operacao4, operacao5);

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);

            campoOperacoes.setAccessible(true);

            campoOperacoes.set(contaCorrente, operacoes);

            Method buscarOperacoesPorValor = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_BUSCAR_OPERACOES_POR_VALOR, Double.class);

            List<?> result = (List<?>) buscarOperacoesPorValor.invoke(contaCorrente, (Object) null);

            assertEquals(0, result.size());
        }

        @Test
        @DisplayName("Cenário 4 - Buscar operações por valor e não encontrar nenhuma")
        void test4() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {

            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");

            Object operacao1 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Dona Maria", 17.50);
            Object operacao2 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Agua", 179.0);
            Object operacao3 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Seu João", 50.0);
            Object operacao4 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Energia", 234.0);
            Object operacao5 = OperacaoBancariaFactory.getInstance("Lazer", "Cinema", 50.0);

            List<Object> operacoes = List.of(operacao1, operacao2, operacao3, operacao4, operacao5);

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);

            campoOperacoes.setAccessible(true);

            campoOperacoes.set(contaCorrente, operacoes);

            Method buscarOperacoesPorValor = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_BUSCAR_OPERACOES_POR_VALOR, Double.class);

            List<?> result = (List<?>) buscarOperacoesPorValor.invoke(contaCorrente, 1000.0);

            assertEquals(0, result.size());
        }

        @Test
        @DisplayName("Cenário 5 - Buscar operações por valor e encontrar mais de uma")
        void test5() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {

            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");

            Object operacao1 = OperacaoBancariaFactory.getInstance("Lazer", "PSN", 17.50);
            Object operacao2 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Agua", 179.0);
            Object operacao3 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Seu João", 17.50);
            Object operacao4 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Energia", 234.0);
            Object operacao5 = OperacaoBancariaFactory.getInstance("Lazer", "Cinema", 50.0);

            List<Object> operacoes = List.of(operacao1, operacao2, operacao3, operacao4, operacao5);

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);

            campoOperacoes.setAccessible(true);

            campoOperacoes.set(contaCorrente, operacoes);

            Method buscarOperacoesPorValor = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_BUSCAR_OPERACOES_POR_VALOR, Double.class);

            List<?> result = (List<?>) buscarOperacoesPorValor.invoke(contaCorrente, 17.50);

            assertEquals(2, result.size());
        }
    }

    @Nested
    @DisplayName("4 - Buscar operações do tipo saida (valores negativos)")
    class BuscarOperacoesDoTipoSaidaTests {

        @Test
        @DisplayName("Cenário 1 - Buscar operações do tipo saida com operações válidas")
        void test1() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {

            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");

            Object operacao1 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Dona Maria", -17.50);
            Object operacao2 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Agua", -179.0);
            Object operacao3 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Seu João", -50.0);
            Object operacao4 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Energia", -234.0);
            Object operacao5 = OperacaoBancariaFactory.getInstance("Lazer", "Cinema", 50.0);

            List<Object> operacoes = List.of(operacao1, operacao2, operacao3, operacao4, operacao5);

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);

            campoOperacoes.setAccessible(true);

            campoOperacoes.set(contaCorrente, operacoes);

            Method buscarOperacoesSaida = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_BUSCAR_OPERACOES_SAIDA);

            List<?> result = (List<?>) buscarOperacoesSaida.invoke(contaCorrente);

            assertEquals(4, result.size());
        }

        @Test
        @DisplayName("Cenário 2 - Buscar operações do tipo saida com operações inválidas")
        void test2() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {

            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");

            Object operacao1 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Dona Maria", 17.50);
            Object operacao2 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Agua", 179.0);
            Object operacao3 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Seu João", 50.0);
            Object operacao4 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Energia", 234.0);
            Object operacao5 = OperacaoBancariaFactory.getInstance("Lazer", "Cinema", 50.0);

            List<Object> operacoes = List.of(operacao1, operacao2, operacao3, operacao4, operacao5);

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);

            campoOperacoes.setAccessible(true);

            campoOperacoes.set(contaCorrente, operacoes);

            Method buscarOperacoesSaida = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_BUSCAR_OPERACOES_SAIDA);

            List<?> result = (List<?>) buscarOperacoesSaida.invoke(contaCorrente);

            assertEquals(0, result.size());
        }

        @Test
        @DisplayName("Cenário 3 - Buscar operações do tipo saida com operações vazia")
        void test3() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {

            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);

            campoOperacoes.setAccessible(true);

            campoOperacoes.set(contaCorrente, List.of());

            Method buscarOperacoesSaida = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_BUSCAR_OPERACOES_SAIDA);

            List<?> result = (List<?>) buscarOperacoesSaida.invoke(contaCorrente);

            assertEquals(0, result.size());
        }
    }

    @Nested
    @DisplayName("5 - Buscar operações do tipo entrada (valores positivos)")
    class BuscarOperacoesDoTipoEntradaTests {

        @Test
        @DisplayName("Cenário 1 - Buscar operações do tipo entrada com operações válidas")
        void test1() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {

            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");

            Object operacao1 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Dona Maria", 17.50);
            Object operacao2 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Agua", -179.0);
            Object operacao3 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Seu João", 50.0);
            Object operacao4 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Energia", 234.0);
            Object operacao5 = OperacaoBancariaFactory.getInstance("Lazer", "Cinema", -50.0);

            List<Object> operacoes = List.of(operacao1, operacao2, operacao3, operacao4, operacao5);

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);

            campoOperacoes.setAccessible(true);

            campoOperacoes.set(contaCorrente, operacoes);

            Method buscarOperacoesEntrada = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_BUSCAR_OPERACOES_ENTRADA);

            List<?> result = (List<?>) buscarOperacoesEntrada.invoke(contaCorrente);

            assertEquals(3, result.size());
        }

        @Test
        @DisplayName("Cenário 2 - Buscar operações do tipo entrada com operações inválidas")
        void test2() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {

            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");

            Object operacao1 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Dona Maria", -17.50);
            Object operacao2 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Agua", -179.0);
            Object operacao3 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Seu João", -50.0);
            Object operacao4 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Energia", -234.0);
            Object operacao5 = OperacaoBancariaFactory.getInstance("Lazer", "Cinema", -50.0);

            List<Object> operacoes = List.of(operacao1, operacao2, operacao3, operacao4, operacao5);

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);

            campoOperacoes.setAccessible(true);

            campoOperacoes.set(contaCorrente, operacoes);

            Method buscarOperacoesEntrada = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_BUSCAR_OPERACOES_ENTRADA);

            List<?> result = (List<?>) buscarOperacoesEntrada.invoke(contaCorrente);

            assertEquals(0, result.size());
        }

        @Test
        @DisplayName("Cenário 3 - Buscar operações do tipo entrada com operações vazia")
        void test3() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {

            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);

            campoOperacoes.setAccessible(true);

            campoOperacoes.set(contaCorrente, List.of());

            Method buscarOperacoesEntrada = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_BUSCAR_OPERACOES_ENTRADA);

            List<?> result = (List<?>) buscarOperacoesEntrada.invoke(contaCorrente);

            assertEquals(0, result.size());
        }
    }

    @Nested
    @DisplayName("6 - Buscar operações por descrição")
    class BuscarOperacoesPorDescricaoTests {

        @Test
        @DisplayName("Cenário 1 - Buscar operações por descrição válida (valor exato)")
        void test1() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {

            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");

            Object operacao1 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Dona Maria", -17.50);
            Object operacao2 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Agua", -179.0);
            Object operacao3 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Seu João", -50.0);
            Object operacao4 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Energia", -234.0);
            Object operacao5 = OperacaoBancariaFactory.getInstance("Lazer", "Cinema", -50.0);

            List<Object> operacoes = List.of(operacao1, operacao2, operacao3, operacao4, operacao5);

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);

            campoOperacoes.setAccessible(true);

            campoOperacoes.set(contaCorrente, operacoes);

            Method buscarOperacoesPorDescricao = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_BUSCAR_OPERACOES_POR_DESCRICAO, String.class);

            final String VALOR_PROCURADO = "Cinema";

            List<?> result = (List<?>) buscarOperacoesPorDescricao.invoke(contaCorrente, VALOR_PROCURADO);

            Object operacao = result.get(0);

            Field campoDescricao = operacao.getClass().getDeclaredField(OperacaoBancariaUtils.ATRIBUTO_DESCRICAO);

            campoDescricao.setAccessible(true);

            assertEquals(1, result.size());
            assertEquals(VALOR_PROCURADO, campoDescricao.get(operacao));
        }

        @Test
        @DisplayName("Cenário 2 - Buscar operações por descrição válida (valor parcial)")
        void test2() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {

            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");
            Object operacao1 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Dona Maria", -17.50);
            Object operacao2 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Agua", -179.0);
            Object operacao3 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Seu João", -50.0);
            Object operacao4 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Energia", -234.0);
            Object operacao5 = OperacaoBancariaFactory.getInstance("Lazer", "Cinema", -50.0);
            Object operacao6 = OperacaoBancariaFactory.getInstance("Lazer", "Cinema 2", -50.0);

            List<Object> operacoes = List.of(operacao1, operacao2, operacao3, operacao4, operacao5, operacao6);

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);
            campoOperacoes.setAccessible(true);
            campoOperacoes.set(contaCorrente, operacoes);

            Method buscarOperacoesPorDescricao = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_BUSCAR_OPERACOES_POR_DESCRICAO, String.class);

            final String VALOR_PROCURADO = "Cine";

            final String DESCRICAO_ESPERADA1 = "Cinema";
            final String DESCRICAO_ESPERADA2 = "Cinema 2";

            List<?> result = (List<?>) buscarOperacoesPorDescricao.invoke(contaCorrente, VALOR_PROCURADO);

            Object operacaoEncontrada1 = result.get(0);
            Object operacaoEncontrada2 = result.get(1);

            Field campoDescricao1 = operacaoEncontrada1.getClass().getDeclaredField(OperacaoBancariaUtils.ATRIBUTO_DESCRICAO);
            Field campoDescricao2 = operacaoEncontrada2.getClass().getDeclaredField(OperacaoBancariaUtils.ATRIBUTO_DESCRICAO);

            campoDescricao1.setAccessible(true);
            campoDescricao2.setAccessible(true);

            assertEquals(2, result.size());
            assertEquals(DESCRICAO_ESPERADA1, campoDescricao1.get(operacaoEncontrada1));
            assertEquals(DESCRICAO_ESPERADA2, campoDescricao2.get(operacaoEncontrada2));
        }

        @Test
        @DisplayName("Cenário 3 - Buscar operações por descrição inválida")
        void test3() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {

            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");
            Object operacao1 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Dona Maria", -17.50);
            Object operacao2 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Agua", -179.0);
            Object operacao3 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Seu João", -50.0);
            Object operacao4 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Energia", -234.0);
            Object operacao5 = OperacaoBancariaFactory.getInstance("Lazer", "Cinema", -50.0);
            Object operacao6 = OperacaoBancariaFactory.getInstance("Lazer", "Cinema 2", -50.0);

            List<Object> operacoes = List.of(operacao1, operacao2, operacao3, operacao4, operacao5, operacao6);

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);
            campoOperacoes.setAccessible(true);
            campoOperacoes.set(contaCorrente, operacoes);

            Method buscarOperacoesPorDescricao = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_BUSCAR_OPERACOES_POR_DESCRICAO, String.class);

            final String VALOR_PROCURADO = "Academia";

            List<?> result = (List<?>) buscarOperacoesPorDescricao.invoke(contaCorrente, VALOR_PROCURADO);

            assertEquals(0, result.size());
        }

        @Test
        @DisplayName("Cenário 4 - Buscar operações por descrição nula")
        void test4() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {

            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");
            Object operacao1 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Dona Maria", -17.50);
            Object operacao2 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Agua", -179.0);
            Object operacao3 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Seu João", -50.0);
            Object operacao4 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Energia", -234.0);
            Object operacao5 = OperacaoBancariaFactory.getInstance("Lazer", "Cinema", -50.0);
            Object operacao6 = OperacaoBancariaFactory.getInstance("Lazer", "Cinema 2", -50.0);

            List<Object> operacoes = List.of(operacao1, operacao2, operacao3, operacao4, operacao5, operacao6);

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);
            campoOperacoes.setAccessible(true);
            campoOperacoes.set(contaCorrente, operacoes);

            Method buscarOperacoesPorDescricao = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_BUSCAR_OPERACOES_POR_DESCRICAO, String.class);

            List<?> result = (List<?>) buscarOperacoesPorDescricao.invoke(contaCorrente, (Object) null);

            assertEquals(0, result.size());
        }

        @Test
        @DisplayName("Cenário 5 - Buscar operações por descrição com com texto vazio")
        void test5() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {

            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");
            Object operacao1 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Dona Maria", -17.50);
            Object operacao2 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Agua", -179.0);
            Object operacao3 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Seu João", -50.0);
            Object operacao4 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Energia", -234.0);
            Object operacao5 = OperacaoBancariaFactory.getInstance("Lazer", "Cinema", -50.0);
            Object operacao6 = OperacaoBancariaFactory.getInstance("Lazer", "Cinema 2", -50.0);

            List<Object> operacoes = List.of(operacao1, operacao2, operacao3, operacao4, operacao5, operacao6);

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);
            campoOperacoes.setAccessible(true);
            campoOperacoes.set(contaCorrente, List.of());

            Method buscarOperacoesPorDescricao = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_BUSCAR_OPERACOES_POR_DESCRICAO, String.class);

            final String VALOR_PROCURADO = "";

            List<?> result = (List<?>) buscarOperacoesPorDescricao.invoke(contaCorrente, VALOR_PROCURADO);

            assertEquals(0, result.size());
        }

        @Test
        @DisplayName("Cenário 6 - Buscar operações por descrição com espaços em branco")
        void test6() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {

            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");
            Object operacao1 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Dona Maria", -17.50);
            Object operacao2 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Agua", -179.0);
            Object operacao3 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Seu João", -50.0);
            Object operacao4 = OperacaoBancariaFactory.getInstance("Gasto fixo", "Energia", -234.0);
            Object operacao5 = OperacaoBancariaFactory.getInstance("Lazer", "Cinema", -50.0);
            Object operacao6 = OperacaoBancariaFactory.getInstance("Lazer", "Cinema 2", -50.0);

            List<Object> operacoes = List.of(operacao1, operacao2, operacao3, operacao4, operacao5, operacao6);

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);
            campoOperacoes.setAccessible(true);
            campoOperacoes.set(contaCorrente, List.of());

            Method buscarOperacoesPorDescricao = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_BUSCAR_OPERACOES_POR_DESCRICAO, String.class);

            final String VALOR_PROCURADO = " ";

            List<?> result = (List<?>) buscarOperacoesPorDescricao.invoke(contaCorrente, VALOR_PROCURADO);

            assertEquals(0, result.size());
        }

        @Test
        @DisplayName("Cenário 7 - Buscar operações por descrição sem case sensitive")
        void test7() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {

            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");
            Object operacao1 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Dona Maria", -17.50);
            Object operacao2 = OperacaoBancariaFactory.getInstance("Gasto fixo", "agua", -179.0);
            Object operacao3 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Seu João", -50.0);
            Object operacao4 = OperacaoBancariaFactory.getInstance("Gasto fixo", "energia", -234.0);
            Object operacao5 = OperacaoBancariaFactory.getInstance("Lazer", "ciNema", -50.0);
            Object operacao6 = OperacaoBancariaFactory.getInstance("Lazer", "cinemA 2", -50.0);

            List<Object> operacoes = List.of(operacao1, operacao2, operacao3, operacao4, operacao5, operacao6);

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);
            campoOperacoes.setAccessible(true);
            campoOperacoes.set(contaCorrente, operacoes);

            Method buscarOperacoesPorDescricao = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_BUSCAR_OPERACOES_POR_DESCRICAO, String.class);

            final String VALOR_PROCURADO = "CInEMA";

            final String DESCRICAO_ESPERADA1 = "ciNema";
            final String DESCRICAO_ESPERADA2 = "cinemA 2";

            List<?> result = (List<?>) buscarOperacoesPorDescricao.invoke(contaCorrente, VALOR_PROCURADO);

            Object operacaoEncontrada1 = result.get(0);
            Object operacaoEncontrada2 = result.get(1);

            Field campoDescricao1 = operacaoEncontrada1.getClass().getDeclaredField(OperacaoBancariaUtils.ATRIBUTO_DESCRICAO);
            Field campoDescricao2 = operacaoEncontrada2.getClass().getDeclaredField(OperacaoBancariaUtils.ATRIBUTO_DESCRICAO);

            campoDescricao1.setAccessible(true);
            campoDescricao2.setAccessible(true);

            assertEquals(2, result.size());

            assertEquals(DESCRICAO_ESPERADA1, campoDescricao1.get(operacaoEncontrada1));
            assertEquals(DESCRICAO_ESPERADA2, campoDescricao2.get(operacaoEncontrada2));
        }

        @Test
        @DisplayName("Cenário 8 - Buscar operações por descrição e operaçoes esta vazia")
        void test8() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {

            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");
            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);
            campoOperacoes.setAccessible(true);
            campoOperacoes.set(contaCorrente, List.of());

            Method buscarOperacoesPorDescricao = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_BUSCAR_OPERACOES_POR_DESCRICAO, String.class);

            final String VALOR_PROCURADO = "Cinema";

            List<?> result = (List<?>) buscarOperacoesPorDescricao.invoke(contaCorrente, VALOR_PROCURADO);

            assertEquals(0, result.size());
        }
    }

    @Nested
    @DisplayName("7 - Buscar maior valor de operação")
    class BuscarMaiorValorOperacaoTests {

        @Test
        @DisplayName("Cenário 1 - Buscar maior valor de operação com operações válidas")
        void test1() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {

            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");
            Object operacao1 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Dona Maria", 17.50);
            Object operacao2 = OperacaoBancariaFactory.getInstance("Gasto fixo", "agua", -179.0);
            Object operacao3 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Seu João", -2.0);
            Object operacao4 = OperacaoBancariaFactory.getInstance("Gasto fixo", "energia", -234.0);
            Object operacao5 = OperacaoBancariaFactory.getInstance("Lazer", "ciNema", 50.0);
            Object operacao6 = OperacaoBancariaFactory.getInstance("Lazer", "cinemA 2", -50.0);
            Object operacao7 = OperacaoBancariaFactory.getInstance("Lazer", "cinemA 3", Double.MIN_VALUE);

            List<Object> operacoes = List.of(operacao1, operacao2, operacao3, operacao4, operacao5, operacao6, operacao7);

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);

            campoOperacoes.setAccessible(true);

            campoOperacoes.set(contaCorrente, operacoes);

            Method buscarMaiorValorOperacao = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_BUSCAR_MAIOR_VALOR);

            Object result = buscarMaiorValorOperacao.invoke(contaCorrente);

            assertEquals(50.0, result);
        }

        @Test
        @DisplayName("Cenário 2 - Buscar maior valor de operação com operações negativas")
        void test2() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {

            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");
            Object operacao1 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Dona Maria", -17.50);
            Object operacao2 = OperacaoBancariaFactory.getInstance("Gasto fixo", "agua", -179.0);
            Object operacao3 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Seu João", -2.0);
            Object operacao4 = OperacaoBancariaFactory.getInstance("Gasto fixo", "energia", -234.0);
            Object operacao5 = OperacaoBancariaFactory.getInstance("Lazer", "ciNema", -50.0);
            Object operacao6 = OperacaoBancariaFactory.getInstance("Lazer", "cinemA 2", -50.0);

            List<Object> operacoes = List.of(operacao1, operacao2, operacao3, operacao4, operacao5, operacao6);

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);

            campoOperacoes.setAccessible(true);

            campoOperacoes.set(contaCorrente, operacoes);

            Method buscarMaiorValorOperacao = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_BUSCAR_MAIOR_VALOR);

            Object result = buscarMaiorValorOperacao.invoke(contaCorrente);

            assertEquals(-2.0, result);
        }

        @Test
        @DisplayName("Cenário 3 - Buscar maior valor de operação com operações vazias")
        void test3() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {

            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);

            campoOperacoes.setAccessible(true);

            campoOperacoes.set(contaCorrente, List.of());

            Method buscarMaiorValorOperacao = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_BUSCAR_MAIOR_VALOR);

            Object result = buscarMaiorValorOperacao.invoke(contaCorrente);

            assertEquals(0.0, result);
        }

    }

    @Nested
    @DisplayName("8 - Buscar menor valor de operação")
    class BuscarMenorValorOperacaoTests {

        @Test
        @DisplayName("Cenário 1 - Buscar menor valor de operação com operações válidas")
        void test1() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {

            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");
            Object operacao1 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Dona Maria", 17.50);
            Object operacao2 = OperacaoBancariaFactory.getInstance("Gasto fixo", "agua", -179.0);
            Object operacao3 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Seu João", -2.0);
            Object operacao4 = OperacaoBancariaFactory.getInstance("Gasto fixo", "energia", -234.0);
            Object operacao5 = OperacaoBancariaFactory.getInstance("Lazer", "ciNema", 50.0);
            Object operacao6 = OperacaoBancariaFactory.getInstance("Lazer", "cinemA 2", -50.0);
            Object operacao7 = OperacaoBancariaFactory.getInstance("Lazer", "cinemA 3", Double.MAX_VALUE);

            List<Object> operacoes = List.of(operacao1, operacao2, operacao3, operacao4, operacao5, operacao6, operacao7);

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);

            campoOperacoes.setAccessible(true);

            campoOperacoes.set(contaCorrente, operacoes);

            Method buscarMenorValorOperacao = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_BUSCAR_MENOR_VALOR);

            Object result = buscarMenorValorOperacao.invoke(contaCorrente);

            assertEquals(-234.0, result);
        }

        @Test
        @DisplayName("Cenário 2 - Buscar menor valor de operação com operações positivas")
        void test2() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {

            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");
            Object operacao1 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Dona Maria", 17.50);
            Object operacao2 = OperacaoBancariaFactory.getInstance("Gasto fixo", "agua", 179.0);
            Object operacao3 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Seu João", 2.0);
            Object operacao4 = OperacaoBancariaFactory.getInstance("Gasto fixo", "energia", 234.0);
            Object operacao5 = OperacaoBancariaFactory.getInstance("Lazer", "ciNema", 50.0);
            Object operacao6 = OperacaoBancariaFactory.getInstance("Lazer", "cinemA 2", Double.MAX_VALUE);

            List<Object> operacoes = List.of(operacao1, operacao2, operacao3, operacao4, operacao5, operacao6);

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);

            campoOperacoes.setAccessible(true);

            campoOperacoes.set(contaCorrente, operacoes);

            Method buscarMenorValorOperacao = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_BUSCAR_MENOR_VALOR);

            Object result = buscarMenorValorOperacao.invoke(contaCorrente);

            assertEquals(2.0, result);
        }

        @Test
        @DisplayName("Cenário 3 - Buscar menor valor de operação com operações vazias")
        void test3() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {

            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);

            campoOperacoes.setAccessible(true);

            campoOperacoes.set(contaCorrente, List.of());

            Method buscarMenorValorOperacao = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_BUSCAR_MENOR_VALOR);

            Object result = buscarMenorValorOperacao.invoke(contaCorrente);

            assertEquals(0.0, result);
        }

        @Test
        @DisplayName("Cenário 4 - Buscar menor valor de operação com operações negativas")
        void test4() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {

            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");
            Object operacao1 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Dona Maria", -17.50);
            Object operacao2 = OperacaoBancariaFactory.getInstance("Gasto fixo", "agua", -179.0);
            Object operacao3 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Seu João", -2.0);
            Object operacao4 = OperacaoBancariaFactory.getInstance("Gasto fixo", "energia", Double.MAX_VALUE);
            Object operacao5 = OperacaoBancariaFactory.getInstance("Lazer", "ciNema", -50.0);
            Object operacao6 = OperacaoBancariaFactory.getInstance("Lazer", "cinemA 2", -20.0);

            List<Object> operacoes = List.of(operacao1, operacao2, operacao3, operacao4, operacao5, operacao6);

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);

            campoOperacoes.setAccessible(true);

            campoOperacoes.set(contaCorrente, operacoes);

            Method buscarMenorValorOperacao = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_BUSCAR_MENOR_VALOR);

            Object result = buscarMenorValorOperacao.invoke(contaCorrente);

            assertEquals(-179.0, result);
        }
    }

    @Nested
    @DisplayName("9 - Obter saldo")
    class ObterSaldoTests {

        @Test
        @DisplayName("Cenário 1 - Obter saldo com operações válidas (Saldo positivo)")
        void test1() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {

            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");

            Object operacao1 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Dona Maria", 17.50);
            Object operacao2 = OperacaoBancariaFactory.getInstance("Gasto fixo", "agua", -179.0);
            Object operacao3 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Seu João", -2.0);
            Object operacao4 = OperacaoBancariaFactory.getInstance("Gasto fixo", "energia", -234.0);
            Object operacao5 = OperacaoBancariaFactory.getInstance("Lazer", "ciNema", 500.0);
            Object operacao6 = OperacaoBancariaFactory.getInstance("Lazer", "cinemA 2", -50.0);

            List<Object> operacoes = List.of(operacao1, operacao2, operacao3, operacao4, operacao5, operacao6);

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);

            campoOperacoes.setAccessible(true);
            campoOperacoes.set(contaCorrente, operacoes);

            Method obterSaldo = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_OBTER_SALDO);

            Object result = obterSaldo.invoke(contaCorrente);

            assertEquals(52.5, result);
        }

        @Test
        @DisplayName("Cenário 2 - Obter saldo com operações válidas (Saldo negativo)")
        void test2() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {

            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");

            Object operacao1 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Dona Maria", 17.50);
            Object operacao2 = OperacaoBancariaFactory.getInstance("Gasto fixo", "agua", -179.0);
            Object operacao3 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Seu João", -2.0);
            Object operacao4 = OperacaoBancariaFactory.getInstance("Gasto fixo", "energia", -234.0);
            Object operacao5 = OperacaoBancariaFactory.getInstance("Lazer", "ciNema", 50.0);
            Object operacao6 = OperacaoBancariaFactory.getInstance("Lazer", "cinemA 2", -50.0);

            List<Object> operacoes = List.of(operacao1, operacao2, operacao3, operacao4, operacao5, operacao6);

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);

            campoOperacoes.setAccessible(true);
            campoOperacoes.set(contaCorrente, operacoes);

            Method obterSaldo = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_OBTER_SALDO);

            Object result = obterSaldo.invoke(contaCorrente);

            assertEquals(-397.5, result);
        }

        @Test
        @DisplayName("Cenário 3 - Obter saldo com operações válidas (Saldo zerado)")
        void test3() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {

            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");

            Method obterSaldo = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_OBTER_SALDO);

            Object result = obterSaldo.invoke(contaCorrente);

            assertEquals(0.0, result);
        }

        @Test
        @DisplayName("Cenário 4 - Obter saldo com operações somente com operacoes positivas")
        void test4() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {

            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");

            Object operacao1 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Dona Maria", 17.50);
            Object operacao2 = OperacaoBancariaFactory.getInstance("Gasto fixo", "agua", 179.0);
            Object operacao3 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Seu João", 2.0);
            Object operacao4 = OperacaoBancariaFactory.getInstance("Gasto fixo", "energia", 234.0);
            Object operacao5 = OperacaoBancariaFactory.getInstance("Lazer", "ciNema", 50.0);
            Object operacao6 = OperacaoBancariaFactory.getInstance("Lazer", "cinemA 2", 50.0);

            List<Object> operacoes = List.of(operacao1, operacao2, operacao3, operacao4, operacao5, operacao6);

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);

            campoOperacoes.setAccessible(true);
            campoOperacoes.set(contaCorrente, operacoes);

            Method obterSaldo = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_OBTER_SALDO);

            Object result = obterSaldo.invoke(contaCorrente);

            assertEquals(532.5, result);
        }

        @Test
        @DisplayName("Cenário 5 - Obter saldo com operações somente com operacoes negativas")
        void test5() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, InstantiationException, IllegalAccessException {

            Object contaCorrente = ContaCorrenteFactory.getInstance("João", "1234", "123456");

            Object operacao1 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Dona Maria", -17.50);
            Object operacao2 = OperacaoBancariaFactory.getInstance("Gasto fixo", "agua", -179.0);
            Object operacao3 = OperacaoBancariaFactory.getInstance("Alimentacao", "Comercio Seu João", -2.0);
            Object operacao4 = OperacaoBancariaFactory.getInstance("Gasto fixo", "energia", -234.0);
            Object operacao5 = OperacaoBancariaFactory.getInstance("Lazer", "ciNema", -50.0);
            Object operacao6 = OperacaoBancariaFactory.getInstance("Lazer", "cinemA 2", -50.0);

            List<Object> operacoes = List.of(operacao1, operacao2, operacao3, operacao4, operacao5, operacao6);

            Field campoOperacoes = contaCorrente.getClass().getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);

            campoOperacoes.setAccessible(true);
            campoOperacoes.set(contaCorrente, operacoes);

            Method obterSaldo = contaCorrente.getClass().getDeclaredMethod(ContaCorrenteUtils.METODO_OBTER_SALDO);

            Object result = obterSaldo.invoke(contaCorrente);

            assertEquals(-532.5, result);
        }
    }
}
