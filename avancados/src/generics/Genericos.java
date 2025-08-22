package generics;
import java.util.ArrayList;
import java.util.List;


// EVITANDO TYPE CHECK (RUNTIME EXCEPTION )
// T => Type => tipo
class Lista<T>{
    private final  List<T> list = new ArrayList<>();

    public void adicionar(T elemento){
        this.list.add(elemento);
    }

    public T obter(Integer indice){
        return list.get(indice);
    }

    public static <T> T obeterDeUmaLista(Lista<T> lista, Integer indice){
        return  lista.obter(indice);
    }
}

public class Genericos {

    public static void main(String[] args) {
        Lista<Integer> numeros = new Lista<>();

        //        NUMEROS ADICIONADOS

        numeros.adicionar(1);
        numeros.adicionar(2);
        System.out.println(numeros.obter(0));
        System.out.println(numeros.obter(1));

        //        strings ADICIONADOS

        numeros.adicionar(3);
        numeros.adicionar(4);
        System.out.println(numeros.obter(2));
        System.out.println(numeros.obter(3));

        System.out.println(Lista.obeterDeUmaLista(numeros,1));

    }
}
