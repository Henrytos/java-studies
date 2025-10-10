package school.sptech.utils;

import school.sptech.auxiliar.Metodo;

public class OperacaoBancariaUtils {

    public static final String ATRIBUTO_CATEGORIA = "categoria";
    public static final String ATRIBUTO_DESCRICAO = "descricao";
    public static final String ATRIBUTO_VALOR = "valor";

    public static final String METODO_GET_CATEGORIA = "getCategoria";
    public static final String METODO_GET_DESCRICAO = "getDescricao";
    public static final String METODO_GET_VALOR = "getValor";

    // Especificação

    public static final String ESPECIFICACAO_ATRIBUTO_CATEGORIA = "categoria";
    public static final String ESPECIFICACAO_ATRIBUTO_DESCRICAO = "descricao";
    public static final String ESPECIFICACAO_ATRIBUTO_VALOR = "valor";

    public static final Metodo ESPECIFICACAO_METODO_GET_CATEGORIA = new Metodo("getCategoria", new Class<?>[] { });
    public static final Metodo ESPECIFICACAO_METODO_GET_DESCRICAO = new Metodo("getDescricao", new Class<?>[] { });
    public static final Metodo ESPECIFICACAO_METODO_GET_VALOR = new Metodo("getValor", new Class<?>[] { });
}
