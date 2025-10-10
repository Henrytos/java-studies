package school.sptech.factory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import school.sptech.VendasCidade;

public class VendasCidadeFactory {

    static Map<String, Field> campos() throws ReflectiveOperationException {
        Class<VendasCidade> clazz = VendasCidade.class;

        Map<String, Field> mapCampos = new HashMap<>();

        String[] nomeCampos = {"cidade", "faturamentoTotal"};

        for (String campoNome : nomeCampos) {
            Field campo = clazz.getDeclaredField(campoNome);
            campo.trySetAccessible();

            mapCampos.put(campoNome, campo);
        }

        return mapCampos;
    }

    public static VendasCidade build(String cidade, Double faturamentoTotal) {
        var clazz = VendasCidade.class;
        try {
            var obj = clazz.getDeclaredConstructor().newInstance();
            campos().get("cidade").set(obj, cidade);
            campos().get("faturamentoTotal").set(obj, faturamentoTotal);
            return obj;
        } catch (Exception e) {
            throw new RuntimeException("A classe musica não foi definida corretamente");
        }
    }
}
