package school.sptech.factory;

import school.sptech.Celular;
import school.sptech.Produto;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class CelularFactory {

  static Map<String, Field> campos() throws ReflectiveOperationException {
    Class<Celular> clazz = Celular.class;
    Class<Produto> superClazz = Produto.class;

    Map<String, Field> mapCampos = new HashMap<>();
    String[] nomeCamposSuper = { "nome", "preco", "peso" };
    String[] nomeCampos = { "garantiaMeses", "marca" };

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

  public static Object build(String nome, Double preco, Double peso, Integer garantiaMeses, String marca) throws ReflectiveOperationException {
    Object obj = new Celular();
    campos().get("nome").set(obj, nome);
    campos().get("preco").set(obj, preco);
    campos().get("peso").set(obj, peso);
    campos().get("garantiaMeses").set(obj, garantiaMeses);
    campos().get("marca").set(obj, marca);

    return obj;
  }
}
