package school.sptech.factory;

import school.sptech.Carrinho;
import school.sptech.Celular;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarrinhoFactory {

  static Map<String, Field> campos() throws ReflectiveOperationException {
    Class<Carrinho> clazz = Carrinho.class;

    Map<String, Field> mapCampos = new HashMap<>();
    String[] nomeCampos = { "id", "produtos" };

    for (String campoNome : nomeCampos) {
      Field campo = clazz.getDeclaredField(campoNome);
      campo.trySetAccessible();

      mapCampos.put(campoNome, campo);
    }

    return mapCampos;
  }

  public static Object build(Integer id, List<?> produtos) throws ReflectiveOperationException {
    Object obj = new Carrinho();
    campos().get("id").set(obj, id);
    campos().get("produtos").set(obj, produtos);

    return obj;
  }
}
