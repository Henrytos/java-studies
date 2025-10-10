package school.sptech;

import java.util.Arrays;

public class CalculateExpenditureCalorieUseCase {

    public static void main(String[] args) {
        Double heatingTimeInMinutes = 10.00;
        Double aerobicTimeInMinutes = 10.00;
        Double bodybuildingTimeInMinutes = 10.00;

        Double totalTimeInMinutes = sum( heatingTimeInMinutes,
                aerobicTimeInMinutes,
                bodybuildingTimeInMinutes);
        Double totalCostCalorie = execute(heatingTimeInMinutes, aerobicTimeInMinutes, bodybuildingTimeInMinutes);

        String message = """
                Olá, Jorge. Você fez um total de %.1f minutos de exercícios e perdeu cerca de %.2f calorias.
                """.formatted(totalTimeInMinutes, totalCostCalorie);

        System.out.println(message);
    }

    private static Double execute(Double heatingTimeInMinutes, Double aerobicTimeInMinutes, Double bodybuildingTimeInMinutes) {
        return heatingTimeInMinutes * CALORIE_EXPENDITURE.HEATING.COST + aerobicTimeInMinutes * CALORIE_EXPENDITURE.AEROBIC.COST + bodybuildingTimeInMinutes * CALORIE_EXPENDITURE.BODYBUILDING.COST;
    }

    private static Double sum(Double... numbers) {
        return Arrays.stream(numbers).reduce(0.0, Double::sum);
    }
}
