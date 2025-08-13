package school.sptech;

public class Exemplo03 {
    public static void main(String[] args) {
        String valueOne = "manuel";
        String valueTwo = "manuel";

//        string aponta na mesma memoria caso ficar

        if(valueOne.equalsIgnoreCase(valueTwo)){
            System.out.println("valores iguais");
        }else {
            System.out.println("valores diferentes");
        }


        Double valor4 = 10.0;
        double valor5 = 10.0;

        if(valor4.equals(valor5)){
            System.out.println("IGUAIS");
        }
    }
}