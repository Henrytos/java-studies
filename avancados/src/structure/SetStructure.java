package structure;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SetStructure {

    public static void main(String[] args) {
        Set<String> setMatematica = new HashSet<>(Arrays.asList("Alice", "Bob", "Carlos"));
        Set<String> setFisica = new HashSet<>(Arrays.asList("Bob", "David", "Alice"));

        System.out.println(setMatematica);
        System.out.println(setFisica);

        setMatematica.retainAll(setFisica);

        System.out.println(setMatematica);

        Set<Integer> sets = new HashSet<>(Arrays.asList(1, 2, 3));
        Set<Integer> sets2 = new HashSet<>(Arrays.asList(3, 4));

        sets2.retainAll(sets);

        System.out.println(sets);
        System.out.println(sets2);

        sets.removeAll(sets2);
        sets2.removeAll(sets2);

    }

}