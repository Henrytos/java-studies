package school.sptech.factory;

import school.sptech.Operacao;
import school.sptech.utils.OperacaoBancariaUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class OperacaoBancariaFactory {

    public static Object getInstance(Object categoria, Object descricao, Object valor) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        Class<Operacao> operacaoBancariaClass = Operacao.class;
        Constructor<?> constructor = operacaoBancariaClass.getConstructors()[0];

        Object[] args = new Object[constructor.getParameterCount()];

        Object operacaoBancaria = constructor.newInstance(args);

        Field campoCategoria = operacaoBancariaClass.getDeclaredField(OperacaoBancariaUtils.ATRIBUTO_CATEGORIA);
        Field campoDescricao = operacaoBancariaClass.getDeclaredField(OperacaoBancariaUtils.ATRIBUTO_DESCRICAO);
        Field campoValor = operacaoBancariaClass.getDeclaredField(OperacaoBancariaUtils.ATRIBUTO_VALOR);

        campoCategoria.trySetAccessible();
        campoDescricao.trySetAccessible();
        campoValor.trySetAccessible();

        campoCategoria.set(operacaoBancaria, categoria);
        campoDescricao.set(operacaoBancaria, descricao);
        campoValor.set(operacaoBancaria, valor);

        return operacaoBancaria;
    }
}
