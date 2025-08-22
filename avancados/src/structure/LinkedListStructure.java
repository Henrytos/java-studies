package structure;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedListStructure {
    public static void main(String[] args) {
        // armazenar uma coleção de elementos de forma linear
        // onde cada elemento(nó) contem dados de referencias
        LinkedList<String> linkedList = new LinkedList<>();

        linkedList.add("henry");
        linkedList.add("franz");
        linkedList.add("ramos");
        linkedList.add("arcaya");

        // OTIMOS PARA INSERÇÃO
        linkedList.addFirst("o");
        linkedList.add(1, "nathalia");
        linkedList.addLast(".");
        System.out.println(linkedList);

        // OTIMOS PARA REMOÇÂO
        linkedList.removeFirst();
        linkedList.removeLast();
        System.out.println(linkedList);

        // METODOS DE REMOÇÃO
        // linkedList.removeFirst();
        // linkedList.removeLast();
        // linkedList.remove(element);
        // linkedList.remove(index);
        // linkedList.clear();

        // METODO DE ACESSO
        linkedList.get(0);
        linkedList.getFirst();
        linkedList.getLast();
        linkedList.size();
        linkedList.contains("henry");

        // ITERACAO - MUTABILIDADE
        LinkedList<String> newLIst = new LinkedList<>(linkedList);
        Iterator<String> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();

            System.out.println(element);
        }

        linkedList.clear();
        System.out.println(linkedList);
        System.out.println(newLIst);

        ListIterator<String> listIterator = newLIst.listIterator();
        while (listIterator.hasNext()) {

            String element = listIterator.next();
            System.out.println(
                    listIterator.previousIndex());
            System.out.println(
                    listIterator.nextIndex());

            System.out.println(element);
        }

    }

}
