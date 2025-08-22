package programacao_funcional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

class Pedido {
    int id;
    String cliente;
    double valor;
    LocalDate data;

    public Pedido(int id, String cliente, double valor, LocalDate data) {
        this.id = id;
        this.cliente = cliente;
        this.valor = valor;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", cliente='" + cliente + '\'' +
                ", valor=" + valor +
                ", data=" + data +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }
}

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

        List<Pedido> pedidos = new ArrayList<>();

        pedidos.add(new Pedido(1, "Ana", 150.0, LocalDate.of(2025, 1, 10)));
        pedidos.add(new Pedido(2, "Bruno", 200.0, LocalDate.of(2025, 2, 5)));
        pedidos.add(new Pedido(3, "Ana", 300.0, LocalDate.of(2025, 2, 15)));
        pedidos.add(new Pedido(4, "Carlos", 400.0, LocalDate.of(2024, 12, 20)));
        pedidos.add(new Pedido(5, "Bruno", 100.0, LocalDate.of(2025, 3, 1)));
        pedidos.add(new Pedido(6, "Daniela", 250.0, LocalDate.of(2025, 3, 10)));
        pedidos.add(new Pedido(7, "Ana", 180.0, LocalDate.of(2025, 4, 2)));

        /**
         * 
         * Filtrar apenas pedidos do ano atual.
         * 
         * Agrupar os pedidos por cliente.
         * 
         * Calcular o valor total gasto por cada cliente.
         * 
         * Ordenar os clientes pelo maior valor gasto.
         * 
         * Retornar uma LinkedHashMap<String, Double> para manter a ordem.
         * 
         * 
         */

        pedidos.stream()
                .filter(p -> p.getData().getYear() == LocalDate.now().getYear())
                .collect(Collectors.groupingBy(Pedido::getCliente, Collectors.toList()))
                .entrySet()
                .stream()
                .sorted((e1, e2) -> {
                    return Double.compare(e2.getValue().stream().mapToDouble(Pedido::getValor).sum(),
                            e1.getValue().stream().mapToDouble(Pedido::getValor).sum());
                })
                .collect(
                        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new))
                .forEach((cliente, pedidosCliente) -> {
                    System.out.println(cliente);
                    System.out.println(pedidosCliente);
                });

    }
}