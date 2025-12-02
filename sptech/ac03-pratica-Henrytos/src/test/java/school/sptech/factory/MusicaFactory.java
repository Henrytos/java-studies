package school.sptech.factory;

import school.sptech.Musica;
import school.sptech.util.ObjectFieldBuilder;

public class MusicaFactory {

    public static Object build(String nome, Double duracaoMinutos, String artista, String album, String genero) throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(Musica.class)
            .with("nome", nome)
            .with("duracaoMinutos", duracaoMinutos)
            .with("artista", artista)
            .with("album", album)
            .withEnum("genero", genero)
            .build();
    }
}
