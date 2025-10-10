package school.sptech.factory;

import school.sptech.Desenvolvedor;
import school.sptech.especialistas.DesenvolvedorWeb;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class DesenvolvedorWebFactory {

  static Map<String, Field> campos() throws ReflectiveOperationException {
    Class<DesenvolvedorWeb> clazz = DesenvolvedorWeb.class;
    Class<Desenvolvedor> superClazz = Desenvolvedor.class;

    Map<String, Field> mapCampos = new HashMap<>();
    String[] nomeCamposSuper = { "nome", "qtdHoras", "valorHora" };
    String[] nomeCampos = { "backend", "frontend", "sgbd", "horasMentoria" };

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

  public static Object build(String nome, Integer qtdHoras, Double valorHora,
      String frontend, String backend, String sgbd, Integer horasMentoria) throws ReflectiveOperationException {
    Object obj = new DesenvolvedorWeb();
    campos().get("nome").set(obj, nome);
    campos().get("qtdHoras").set(obj, qtdHoras);
    campos().get("valorHora").set(obj, valorHora);
    campos().get("frontend").set(obj, frontend);
    campos().get("backend").set(obj, backend);
    campos().get("sgbd").set(obj, sgbd);
    campos().get("horasMentoria").set(obj, horasMentoria);

    return obj;
  }
}
