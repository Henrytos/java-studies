package school.sptech;

public enum CALORIE_EXPENDITURE {
    HEATING(12.00),
    AEROBIC(20.00),
    BODYBUILDING(25.00);


    Double COST;

    CALORIE_EXPENDITURE(Double COST){
        this.COST = COST;
    }

}
