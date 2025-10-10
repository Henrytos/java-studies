package school.sptech;

import java.text.NumberFormat;
import java.util.Locale;

public class CalculateChangeInRealUseCase {

    public static void main(String[] args) {
        Integer valueProductInCents = 1000;
        Integer quantityOfProductsSold = 20;

        Double salePaidInReal = quantityOfProductsSold * (valueProductInCents / 100.00);
        Double amountPaidInReal = 230.00;

        if (salePaidInReal > amountPaidInReal) {
            System.out.println("Alguem precisa guardar mais dinheiro :(");
            return;
        }
        Double changeInReal = amountPaidInReal - salePaidInReal;

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        String message = """
                Seu troco será de %s
                """.formatted(numberFormat.format(changeInReal));

        System.out.println(message);
    }

}
