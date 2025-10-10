package school.sptech;

public class Calculadora {

    public Integer dividir(Integer n1, Integer n2) throws NumeroNegativoException {

        if (n1 < 0 || n2 < 0) {
            throw new NumeroNegativoException();
        }

        return n1 / n2;
    }
}
