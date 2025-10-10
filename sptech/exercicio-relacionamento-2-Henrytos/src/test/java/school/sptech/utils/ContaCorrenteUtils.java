package school.sptech.utils;

import school.sptech.auxiliar.Metodo;

public class ContaCorrenteUtils {

    public static final String ATRIBUTO_TITULAR = "titular";
    public static final String ATRIBUTO_NUMERO = "numero";
    public static final String ATRIBUTO_AGENCIA = "agencia";
    public static final String ATRIBUTO_OPERACOES = "operacoes";

    public static final String METODO_GET_TITULAR = "getTitular";
    public static final String METODO_GET_NUMERO = "getNumero";
    public static final String METODO_GET_AGENCIA = "getAgencia";
    public static final String METODO_GET_OPERACOES = "getOperacoes";

    public static final String METODO_ADICIONAR_OPERACAO = "adicionarOperacao";

    public static final String METODO_BUSCAR_OPERACOES_POR_CATEGORIA = "buscarOperacoesPorCategoria";

    public static final String METODO_BUSCAR_OPERACOES_POR_VALOR = "buscarOperacoesPorValor";

    public static final String METODO_BUSCAR_OPERACOES_SAIDA = "buscarOperacoesSaida";

    public static final String METODO_BUSCAR_OPERACOES_ENTRADA = "buscarOperacoesEntrada";

    public static final String METODO_BUSCAR_OPERACOES_POR_DESCRICAO = "buscarOperacoesPorDescricao";

    public static final String METODO_BUSCAR_MAIOR_VALOR = "buscarMaiorValor";

    public static final String METODO_BUSCAR_MENOR_VALOR = "buscarMenorValor";

    public static final String METODO_OBTER_SALDO = "obterSaldo";

    // Especificação

    public static final String ESPECIFICACAO_ATRIBUTO_TITULAR = "titular";
    public static final String ESPECIFICACAO_ATRIBUTO_NUMERO = "numero";
    public static final String ESPECIFICACAO_ATRIBUTO_AGENCIA = "agencia";
    public static final String ESPECIFICACAO_ATRIBUTO_OPERACOES = "operacoes";

    //1.
    public static final Metodo ESPECIFICACAO_METODO_ADICIONAR_OPERACAO = new Metodo("adicionarOperacao", new Class<?>[] { String.class, String.class, Double.class });

    //2.
    public static final Metodo ESPECIFICACAO_METODO_BUSCAR_OPERACOES_POR_CATEGORIA = new Metodo("buscarOperacoesPorCategoria", new Class<?>[] { String.class });

    //3.
    public static final Metodo ESPECIFICACAO_METODO_BUSCAR_OPERACOES_POR_VALOR = new Metodo("buscarOperacoesPorValor", new Class<?>[] { Double.class });

    //4.
    public static final Metodo ESPECIFICACAO_METODO_BUSCAR_OPERACOES_SAIDA = new Metodo("buscarOperacoesSaida", new Class<?>[] { });

    //5.
    public static final Metodo ESPECIFICACAO_METODO_BUSCAR_OPERACOES_ENTRADA = new Metodo("buscarOperacoesEntrada", new Class<?>[] { });

    //6.
    public static final Metodo ESPECIFICACAO_METODO_BUSCAR_OPERACOES_POR_DESCRICAO = new Metodo("buscarOperacoesPorDescricao", new Class<?>[] { String.class });

    //7.
    public static final Metodo ESPECIFICACAO_METODO_BUSCAR_MAIOR_VALOR = new Metodo("buscarMaiorValor", new Class<?>[] { });

    //8.
    public static final Metodo ESPECIFICAO_METODO_BUSCAR_MENOR_VALOR = new Metodo("buscarMenorValor", new Class<?>[] { });

    //9.
    public static final Metodo ESPECIFICACAO_METODO_OBTER_SALDO = new Metodo("obterSaldo", new Class<?>[] { });
}
