package school.sptech.factory;

import school.sptech.Streaming;
import school.sptech.util.ObjectFieldBuilder;

import java.util.ArrayList;
import java.util.List;

public class StreamingFactory {

    public static Object build(String nome, List<?> midias) throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(Streaming.class)
            .with("nome", nome)
            .with("midias", new ArrayList<>(midias))
            .build();
    }

    public static Object build(String nome, List<?> midias, List<?> playlists) throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(Streaming.class)
            .with("nome", nome)
            .with("midias", new ArrayList<>(midias))
            .with("playlists", new ArrayList<>(playlists))
            .build();
    }
}
