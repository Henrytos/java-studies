package lambdas;

public class Main {

    public static void main(String[] args) {
        MyValue myValue = () -> {
            System.out.println("olá lambda");
            return 2.8;
        };
        RaizQuadrada raizQuadrada = Math::sqrt;

        System.out.println("Meu valor eh " + myValue.getValue());
        System.out.println("Raiz quadrada " + raizQuadrada.calcular(9.0));

        OperadorBinario<String> operadorBinarioString = (String nome, String sobrenome) ->{
            return nome.concat(" ").concat(sobrenome);
        };

        System.out.println(operadorBinarioString.somar("henry", "franz"));

        OperadorBinario<Integer> operadorBinarioInteger =
                Integer::sum;

        System.out.println(operadorBinarioInteger.somar(10, 20));

    }
}
