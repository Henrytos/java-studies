package school.sptech.factory;

import school.sptech.ContaCorrente;
import school.sptech.utils.ContaCorrenteUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class ContaCorrenteFactory {

    public static Object getInstance(
            Object nomeConta,
            Object numeroConta,
            Object agenciaConta
    ) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        Class<ContaCorrente> contaCorrenteClass = ContaCorrente.class;

        Constructor<?> constructor = contaCorrenteClass.getConstructors()[0];

        Object[] args = new Object[constructor.getParameterCount()];

        Object contaCorrente = constructor.newInstance(args);

        Field campoTitular = contaCorrenteClass.getDeclaredField(ContaCorrenteUtils.ATRIBUTO_TITULAR);
        Field campoNumero = contaCorrenteClass.getDeclaredField(ContaCorrenteUtils.ATRIBUTO_NUMERO);
        Field campoAgencia = contaCorrenteClass.getDeclaredField(ContaCorrenteUtils.ATRIBUTO_AGENCIA);
        Field campoOperacoes = contaCorrenteClass.getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);

        campoTitular.trySetAccessible();
        campoNumero.trySetAccessible();
        campoAgencia.trySetAccessible();
        campoOperacoes.trySetAccessible();

        campoTitular.set(contaCorrente, nomeConta);
        campoNumero.set(contaCorrente, numeroConta);
        campoAgencia.set(contaCorrente, agenciaConta);

        campoOperacoes.set(contaCorrente, new ArrayList<>());

        return contaCorrente;
    }

}
