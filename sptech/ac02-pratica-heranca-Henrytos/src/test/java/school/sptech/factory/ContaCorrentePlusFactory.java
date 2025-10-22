package school.sptech.factory;

import school.sptech.ContaCorrentePlus;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ContaCorrentePlusFactory {

    public static Map<String, Field> campos() throws ReflectiveOperationException {
        Class<ContaCorrentePlus> clazz = ContaCorrentePlus.class;
        Class<?> superClazz = clazz.getSuperclass();

        Map<String, Field> mapCampos = new HashMap<>();
        String[] nomeCamposSuper = { "numero", "titular", "saldo" };
        String[] nomeCampos = { "pontos" };

        for (String campoNome : nomeCamposSuper) {
            Field campo = superClazz.getDeclaredField(campoNome);
            campo.trySetAccessible();

            mapCampos.put(campoNome, campo);
        }

        for (String campoNome : nomeCampos) {
            Field campo = clazz.getDeclaredField(campoNome);
            campo.trySetAccessible();

            mapCampos.put(campoNome, campo);
        }

        return mapCampos;
    }

    public static Object build(String numero, String titular, Double saldo, Integer pontos) throws ReflectiveOperationException {
        Class<ContaCorrentePlus> clazz = ContaCorrentePlus.class;

        Constructor<?> constructor = clazz.getDeclaredConstructors()[0];
        Object[] args = new Object[constructor.getParameterCount()];

        Object obj = constructor.newInstance(args);
        campos().get("numero").set(obj, numero);
        campos().get("titular").set(obj, titular);
        campos().get("saldo").set(obj, saldo);
        campos().get("pontos").set(obj, pontos);

        return obj;
    }
}
