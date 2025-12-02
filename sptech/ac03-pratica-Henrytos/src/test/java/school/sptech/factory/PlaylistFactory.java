package school.sptech.factory;

import school.sptech.util.ObjectFieldBuilder;

import java.util.ArrayList;
import java.util.List;

public class PlaylistFactory {

  public static Object build(String nome, List<?> musicas, List<?> avaliacoes) throws ReflectiveOperationException {
    return new ObjectFieldBuilder<>(school.sptech.Playlist.class)
        .with("nome", nome)
        .with("musicas", new ArrayList<>(musicas))
        .with("avaliacoes", new ArrayList<>(avaliacoes))
        .build();
  }
}
