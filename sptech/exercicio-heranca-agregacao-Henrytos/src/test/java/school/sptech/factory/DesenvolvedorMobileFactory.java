package school.sptech.factory;

import school.sptech.Desenvolvedor;
import school.sptech.especialistas.DesenvolvedorMobile;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class DesenvolvedorMobileFactory {

  static Map<String, Field> campos() throws ReflectiveOperationException {
    Class<DesenvolvedorMobile> clazz = DesenvolvedorMobile.class;
    Class<Desenvolvedor> superClazz = Desenvolvedor.class;

    Map<String, Field> mapCampos = new HashMap<>();
    String[] nomeCamposSuper = { "nome", "qtdHoras", "valorHora" };
    String[] nomeCampos = { "plataforma", "linguagem", "horasPrototipacao" };

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
       String plataforma, String linguagem, Integer horasPrototipacao) throws ReflectiveOperationException {
    Object obj = new DesenvolvedorMobile();
    campos().get("nome").set(obj, nome);
    campos().get("qtdHoras").set(obj, qtdHoras);
    campos().get("valorHora").set(obj, valorHora);
    campos().get("plataforma").set(obj, plataforma);
    campos().get("linguagem").set(obj, linguagem);
    campos().get("horasPrototipacao").set(obj, horasPrototipacao);

    return obj;
  }
}
