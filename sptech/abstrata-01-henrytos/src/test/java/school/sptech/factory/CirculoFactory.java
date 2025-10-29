package school.sptech.factory;

import school.sptech.Circulo;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class CirculoFactory {

  static Map<String, Field> campos() throws ReflectiveOperationException {
    Class<Circulo> clazz = Circulo.class;
    Class<?> superClazz = clazz.getSuperclass();

    Map<String, Field> mapCampos = new HashMap<>();
    String[] nomeCamposSuper = { "cor", "espessura" };
    String[] nomeCampos = { "raio" };

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

  public static Object build(String cor, Integer espessura, Double raio) throws ReflectiveOperationException {
    Object obj = new Circulo();
    campos().get("cor").set(obj, cor);
    campos().get("espessura").set(obj, espessura);
    campos().get("raio").set(obj, raio);

    return obj;
  }
}
