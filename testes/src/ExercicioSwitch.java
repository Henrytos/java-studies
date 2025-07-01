public class ExercicioSwitch {

    public static void main(String[] args) {
        String plan = "T";

        switch (plan){
            case "T": {
                System.out.println("INTERNET ILIMITADA");
            }
            case "P":{
                System.out.println("BANDA LARGA");
            }
            case "E":{
                System.out.println("INTERNET TURBO");
                break;
            }
            default:
                System.out.println("SEM INTERNET");
        };

    }
}
