package school.sptech.factory;

import school.sptech.Retangulo;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class RetanguloFactory {

  static Map<String, Field> campos() throws ReflectiveOperationException {
    Class<Retangulo> clazz = Retangulo.class;
    Class<?> superClazz = clazz.getSuperclass();

    Map<String, Field> mapCampos = new HashMap<>();
    String[] nomeCamposSuper = { "cor", "espessura" };
    String[] nomeCampos = { "base", "altura" };

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

  public static Object build(String cor, Integer espessura, Double base, Double altura) throws ReflectiveOperationException {
    Object obj = new Retangulo();
    campos().get("cor").set(obj, cor);
    campos().get("espessura").set(obj, espessura);
    campos().get("base").set(obj, base);
    campos().get("altura").set(obj, altura);

    return obj;
  }
}
