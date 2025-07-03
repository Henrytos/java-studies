import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalClasse {

    public static void main(String[] args) {
        BigDecimal litros = BigDecimal.valueOf(12.32342333);
        BigDecimal preco = BigDecimal.valueOf(6.54);

        BigDecimal valor = litros.multiply(preco);
        System.out.println(valor);

        BigDecimal valorFormatado = valor.setScale(2, RoundingMode.HALF_EVEN);
        System.out.println(valorFormatado);

        // dizima periodica da erro se não definimos escala
        System.out.println(BigDecimal.TEN.divide(BigDecimal.valueOf(3), 2 , RoundingMode.HALF_EVEN));
    }

}
