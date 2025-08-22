package generics;
import java.util.ArrayList;
import java.util.List;

public class LowerBoundedWildcardExample {

        public static void showListOfNumbers(List<? super Integer> elements){
            System.out.println(elements);
        }

        public static void main(String[] args) {
            List<Number> numbers = new ArrayList<>();
            numbers.add(10);
            numbers.add(20);

            List<Integer> integers = new ArrayList<>();
            integers.add(10);
            integers.add(20);

            showListOfNumbers(numbers);
            showListOfNumbers(integers);
            
        }
}
