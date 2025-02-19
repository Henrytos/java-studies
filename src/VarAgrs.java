public class VarAgrs {

    public static void main(String[] args) {
        System.out.println("henry");
        int number = SumArray.execute (1, 2, 3, 4, 5, 6);
        System.out.println(number);
    }


}

class SumArray {
    public static int execute(int... numbers) { // não pode ser paramtros posterior (int myFavoriteNumber, int... numbers ) X
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }

        return sum;
    }


}