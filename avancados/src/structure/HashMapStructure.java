package structure;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class HashMapStructure {

    // interfaces são contratos estabelecidos que devem ser seguidos por suas
    // implementações

    // estrutura de dado de chave e valor

    // chaves são unicas (id)

    // não existe um valor associado a duas chave , somente uma chave unica e seu
    // valor,
    // caso quebre a regra irá ter incoesão de dados

    public static void main(String[] args) {
        Map<String, String> registros = new HashMap<>();

        // ADICIONAR
        registros.put("henry", "henry");
        registros.put("ana", "ana");
        registros.put("mioki", "mioki");
        registros.put("beatriz", "beatriz");
        registros.put("dioggo", "dioggo");
        System.out.println("----------------LISTA DE REGISTRO----------------");
        System.out.println(registros);
        System.out.println("----------------LISTA DE REGISTRO----------------");

        registros.put("henry", "henry gabriel");

        // EXIBIR
        System.out.println(registros);
        System.out.println(registros.get("henry"));

        // ITERAR
        for (Entry<String, String> reg : registros.entrySet()) {
            System.out.println(reg.getKey());
            System.out.println(reg.getValue());
        }

        // REMOVE
        registros.remove("henry");
        registros.clear();
        System.out.println(registros);

        // ATUALIZAR
        registros.put("henry", "henry franz");
        System.out.println(registros);

        registros.replace("henry", "henry gabriel");
        System.out.println(registros);

    }
    /*
     * 
     * HashMap:
     * Não garante qualquer ordem. A ordem dos elementos é aleatória, baseada na
     * função de hash das chaves.
     * 
     * LinkedHashMap:
     * Mantém a ordem de inserção dos elementos. A travessia dos elementos ocorre na
     * ordem em que foram adicionados ao mapa.
     * 
     * TreeMap:
     * Mantém as chaves ordenadas. A ordenação pode ser a natural dos elementos (se
     * as chaves implementarem Comparable) ou personalizada, se um Comparator for
     * fornecido na criação do TreeMap.
     * 
     */
}
