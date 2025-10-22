package school.sptech.factory;

import school.sptech.Banco;
import school.sptech.ContaCorrente;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BancoFactory {

    public static Map<String, Field> campos() throws ReflectiveOperationException {
        Class<Banco> clazz = Banco.class;

        Map<String, Field> mapCampos = new HashMap<>();
        String[] nomeCampos = { "nome", "contas" };

        for (String campoNome : nomeCampos) {
            Field campo = clazz.getDeclaredField(campoNome);
            campo.trySetAccessible();

            mapCampos.put(campoNome, campo);
        }

        return mapCampos;
    }

    public static Object build(String nome, List<?> contas) throws ReflectiveOperationException {
        Class<Banco> clazz = Banco.class;

        Constructor<?> constructor = clazz.getDeclaredConstructors()[0];
        Object[] args = new Object[constructor.getParameterCount()];

        Object obj = constructor.newInstance(args);
        campos().get("nome").set(obj, nome);
        campos().get("contas").set(obj, contas);

        return obj;
    }
}
