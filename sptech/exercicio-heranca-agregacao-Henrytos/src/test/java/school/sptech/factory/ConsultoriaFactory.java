package school.sptech.factory;

import school.sptech.Consultoria;
import school.sptech.Desenvolvedor;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsultoriaFactory {

  static Map<String, Field> campos() throws ReflectiveOperationException {
    Class<Consultoria> clazz = Consultoria.class;

    Map<String, Field> mapCampos = new HashMap<>();
    String[] nomeCampos = { "nome", "vagas", "desenvolvedores" };

    for (String campoNome : nomeCampos) {
      Field campo = clazz.getDeclaredField(campoNome);
      campo.trySetAccessible();

      mapCampos.put(campoNome, campo);
    }

    return mapCampos;
  }

  public static Object build(String nome, Integer vagas, List<?> desenvolvedores) throws ReflectiveOperationException {
    Object obj = new Consultoria();
    campos().get("nome").set(obj, nome);
    campos().get("vagas").set(obj, vagas);
    campos().get("desenvolvedores").set(obj, desenvolvedores);

    return obj;
  }
}
