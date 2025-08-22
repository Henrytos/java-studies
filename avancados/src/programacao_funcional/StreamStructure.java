package programacao_funcional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

record Pessoa(
        String nome,
        int idade,
        String cidade) {
};

public class StreamStructure {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // utilizar multiplas threads
        numbers.parallelStream()
                .forEach((n) -> {
                    System.out.println(n);
                });

        System.out.println(numbers);

        Pessoa[] pessoas = {
                new Pessoa("Ana", 25, "São Paulo"),
                new Pessoa("Bruno", 17, "São Paulo"),
                new Pessoa("Carlos", 30, "Rio de Janeiro"),
                new Pessoa("Daniela", 22, "São Paulo"),
                new Pessoa("Eduardo", 19, "Rio de Janeiro"),
                new Pessoa("Fernanda", 16, "Curitiba"),
                new Pessoa("Gabriel", 40, "Curitiba"),
                new Pessoa("Helena", 28, "São Paulo"),
                new Pessoa("Igor", 35, "Rio de Janeiro")
        };

        Arrays.asList(pessoas)
                .stream()
                .filter(p -> p.idade() > 18)
                .collect(Collectors.groupingBy(p -> p.cidade()))
                .forEach((cidade, pessoasCidade) -> {
                    System.out.println(cidade.toUpperCase());
                    pessoasCidade.parallelStream().forEach(System.out::println);
                    System.out.println();
                });
        ;

    }
}