package school.sptech.factory;

import school.sptech.Desenvolvedor;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class DesenvolvedorFactory {

  static Map<String, Field> campos() throws ReflectiveOperationException {
    Class<Desenvolvedor> clazz = Desenvolvedor.class;

    Map<String, Field> mapCampos = new HashMap<>();
    String[] nomeCampos = { "nome", "qtdHoras", "valorHora" };

    for (String campoNome : nomeCampos) {
      Field campo = clazz.getDeclaredField(campoNome);
      campo.trySetAccessible();

      mapCampos.put(campoNome, campo);
    }

    return mapCampos;
  }

  public static Object build(String nome, Integer qtdHoras, Double valorHora) throws ReflectiveOperationException {
    Object obj = new Desenvolvedor();
    campos().get("nome").set(obj, nome);
    campos().get("qtdHoras").set(obj, qtdHoras);
    campos().get("valorHora").set(obj, valorHora);

    return obj;
  }
}
