package school.sptech.factory;

import school.sptech.Quadrado;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class QuadradoFactory {

  static Map<String, Field> campos() throws ReflectiveOperationException {
    Class<Quadrado> clazz = Quadrado.class;
    Class<?> superClazz = clazz.getSuperclass();

    Map<String, Field> mapCampos = new HashMap<>();
    String[] nomeCamposSuper = { "cor", "espessura" };
    String[] nomeCampos = { "lado" };

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

  public static Object build(String cor, Integer espessura, Double lado) throws ReflectiveOperationException {
    Object obj = new Quadrado();
    campos().get("cor").set(obj, cor);
    campos().get("espessura").set(obj, espessura);
    campos().get("lado").set(obj, lado);

    return obj;
  }
}
