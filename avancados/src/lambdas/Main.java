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
    }
}
