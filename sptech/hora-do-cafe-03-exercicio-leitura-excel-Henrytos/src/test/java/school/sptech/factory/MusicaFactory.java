package school.sptech.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import school.sptech.Musica;

public class MusicaFactory {

    static Map<String, Field> campos() throws ReflectiveOperationException {
        Class<Musica> clazz = Musica.class;

        Map<String, Field> mapCampos = new HashMap<>();

        String[] nomeCampos = {"id", "nome", "artista", "album", "dataLancamento"};

        for (String campoNome : nomeCampos) {
            Field campo = clazz.getDeclaredField(campoNome);
            campo.trySetAccessible();

            mapCampos.put(campoNome, campo);
        }

        return mapCampos;
    }

    public static Musica build(Integer id, String nome, String artista, String album,
          LocalDate dataLancamento) {
        var clazz = Musica.class;
        try {
            var obj = clazz.getDeclaredConstructor().newInstance();
            campos().get("id").set(obj, id);
            campos().get("nome").set(obj, nome);
            campos().get("artista").set(obj, artista);
            campos().get("album").set(obj, album);
            campos().get("dataLancamento").set(obj, dataLancamento);
            return obj;
        } catch (Exception e) {
            throw new RuntimeException("A classe musica não foi definida corretamente");
        }
    }
}
