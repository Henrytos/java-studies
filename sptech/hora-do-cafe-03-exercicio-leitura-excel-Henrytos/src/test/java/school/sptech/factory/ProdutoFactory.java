package school.sptech.factory;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import school.sptech.Produto;

public class ProdutoFactory {

    static Map<String, Field> campos() throws ReflectiveOperationException {
        Class<Produto> clazz = Produto.class;

        Map<String, Field> mapCampos = new HashMap<>();

        String[] nomeCampos = {"id", "nome", "valor", "marca", "dataLancamento"};

        for (String campoNome : nomeCampos) {
            Field campo = clazz.getDeclaredField(campoNome);
            campo.trySetAccessible();

            mapCampos.put(campoNome, campo);
        }

        return mapCampos;
    }

    public static Produto build(Integer id, String nome, Double valor, String marca,
          LocalDate dataLancamento) {
        var clazz = Produto.class;
        try {
            var obj = clazz.getDeclaredConstructor().newInstance();
            campos().get("id").set(obj, id);
            campos().get("nome").set(obj, nome);
            campos().get("valor").set(obj, valor);
            campos().get("marca").set(obj, marca);
            campos().get("dataLancamento").set(obj, dataLancamento);
            return obj;
        } catch (Exception e) {
            throw new RuntimeException("A classe produto não foi definida corretamente");
        }
    }
}
