package school.sptech.factory;

import school.sptech.Episodio;
import school.sptech.util.ObjectFieldBuilder;

import java.util.ArrayList;
import java.util.List;

public class EpisodioFactory {

    public static Object build(String nome, Double duracaoMinutos, String podcast) throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(Episodio.class)
            .with("nome", nome)
            .with("duracaoMinutos", duracaoMinutos)
            .with("podcast", podcast)
            .build();
    }

  public static Object build(String nome, Double duracaoMinutos, String podcast, List<?> avaliacoes) throws ReflectiveOperationException {
    return new ObjectFieldBuilder<>(Episodio.class)
        .with("nome", nome)
        .with("duracaoMinutos", duracaoMinutos)
        .with("podcast", podcast)
        .with("avaliacoes", new ArrayList<>(avaliacoes))
        .build();
  }
}
