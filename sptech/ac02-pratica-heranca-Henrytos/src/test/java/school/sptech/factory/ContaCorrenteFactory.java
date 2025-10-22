package school.sptech.factory;

import school.sptech.ContaCorrente;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ContaCorrenteFactory {

  public static Map<String, Field> campos() throws ReflectiveOperationException {
    Class<ContaCorrente> clazz = ContaCorrente.class;

    Map<String, Field> mapCampos = new HashMap<>();
    String[] nomeCampos = { "numero", "titular", "saldo" };

    for (String campoNome : nomeCampos) {
      Field campo = clazz.getDeclaredField(campoNome);
      campo.trySetAccessible();

      mapCampos.put(campoNome, campo);
    }

    return mapCampos;
  }

  public static Object build(String numero, String titular, Double saldo) throws ReflectiveOperationException {
    Class<ContaCorrente> clazz = ContaCorrente.class;

    Constructor<?> constructor = clazz.getDeclaredConstructors()[0];
    Object[] args = new Object[constructor.getParameterCount()];

    Object obj = constructor.newInstance(args);
    campos().get("numero").set(obj, numero);
    campos().get("titular").set(obj, titular);
    campos().get("saldo").set(obj, saldo);

    return obj;
  }
}
