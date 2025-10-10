package school.sptech;

import java.math.BigDecimal;

public class ContadorVariado {

    public static void main(String[] args) {

        StringBuilder stringBuilder = new StringBuilder();

        BigDecimal INCREMENT = new BigDecimal("0.15");
        BigDecimal MAX_VALUE = new BigDecimal("5.00");

        for (BigDecimal number = new BigDecimal("0.15"); number.compareTo(MAX_VALUE) <= 0; number = number.add(INCREMENT)){
            stringBuilder.append(number.floatValue()).append(" ");
        }

        System.out.println(stringBuilder);

    }

}
