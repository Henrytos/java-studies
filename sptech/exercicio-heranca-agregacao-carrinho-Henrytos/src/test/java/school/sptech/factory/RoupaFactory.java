package school.sptech.factory;

import school.sptech.Produto;
import school.sptech.Roupa;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class RoupaFactory {

  static Map<String, Field> campos() throws ReflectiveOperationException {
    Class<Roupa> clazz = Roupa.class;
    Class<Produto> superClazz = Produto.class;

    Map<String, Field> mapCampos = new HashMap<>();
    String[] nomeCamposSuper = { "nome", "preco", "peso" };
    String[] nomeCampos = { "cor", "tamanho", "material" };

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

  public static Object build(String nome, Double preco, Double peso, String cor, Double tamanho, String material) throws ReflectiveOperationException {
    Object obj = new Roupa();
    campos().get("nome").set(obj, nome);
    campos().get("preco").set(obj, preco);
    campos().get("peso").set(obj, peso);
    campos().get("cor").set(obj, cor);
    campos().get("tamanho").set(obj, tamanho);
    campos().get("material").set(obj, material);

    return obj;
  }
}
