package school.sptech;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //        EXCEPTION(consigo realizar um tratamento em tempo de execução) -> {
        //        NULL POINTER,
        //        OUT BOUND EXCEPTION,
        //        CLASS_CAST_EXCEPTION,
        //        ArrayIndexOutBoundsException,
        //          InputMismatchException(conversão falha do scanner)
        //        }
//UNCHECKED EXCEPTION (Não sou obrigado a tratar) throw s
//CHECKED EXCEPTION (Sou obrigado a tratar)


        //        ERRORS(não consigo  tratar em tempo de execução) -> {
        //        StackOverFlowError,
        //        OutOfMemoryError
        //        }

        try (
                Scanner scanner = new Scanner(System.in);
        ) {

            System.out.println("DIgite um numero(1)");
            Integer numero1 = scanner.nextInt();

            System.out.println("DIgite um numero(2)");
            Integer numero2 = scanner.nextInt();

            Calculadora calculadora = new Calculadora();
            System.out.println("Divisão:" + calculadora.dividir(numero1, numero2));

        }
        catch (NumeroNegativoException e){
            System.out.println("Numero negativo idiota");
        }
        catch (ArithmeticException e) {
            System.out.println("ArithmeticException");
        } catch (InputMismatchException e) {
            System.out.println("ArithmeticException");
        } catch (Exception e) {
            System.out.println("Exception");

            System.out.println(e.getClass());
            e.printStackTrace();
        }

    }

}
