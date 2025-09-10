import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaExemplo {
    public static void main(String[] args) {
        List<String> nomes = new ArrayList<>();

        List<Integer> idades = new ArrayList<>();
        idades.add(10);
        idades.remove(Integer.valueOf(10));

        System.out.println(idades);

        List<String> frutasIniciais =  List.of("maçã", "banana"); // ImmutableCollections (coleção imutavel)
        System.out.println(frutasIniciais.size());

        List<String> frutas = new ArrayList<>(frutasIniciais);
            frutas.add(1,"pitaya");

            frutas.addFirst("morango");

        System.out.println(frutas);

        frutas.set(0,"tomate");
        frutas.addAll(List.of("Sireguelo", "Fruto pao", "Carambola"));

        System.out.println(frutas);

        frutas.removeAll(List.of("Sireguelo", "Fruto pao"));

        for (int i = 0; i < frutas.size(); i++) {
            System.out.println(frutas.get(i));
        }

        for (int i = frutas.size() - 1; i >= 0; i--) {
            System.out.println(frutas.get(i));
        }
        
        frutas.forEach(element->{
            System.out.println(element);
        });

//        enhanced for
        // não posso alterar durante enhanced for
        for (String fruta : frutas) {
            System.out.println(fruta);

            if(fruta.equals("banana")){
               frutas.remove(fruta);
            }
        }

        frutas.clear();
    }
}
