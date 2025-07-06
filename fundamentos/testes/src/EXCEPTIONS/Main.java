package testes.src.EXCEPTIONS;

public class Main {
    public static void main(String[] args) {
        String estado = "RJ";
        try {
            verificarEstado(estado);
        } catch (ExceptionExample e){
            System.err.println(e.getMessage());
        } catch (Exception e){
            System.err.println(e.getMessage());;
        } finally {
            System.out.println("finalização do programa");
        }

        String pais = "BR";
        verificarPais(pais);
    }

    static boolean verificarEstado(String estado) throws ExceptionExample, Exception {
        if (estado.equalsIgnoreCase("SP"))
            throw new ExceptionExample();
        else if (estado.equalsIgnoreCase("RJ"))
            throw new Exception("invalid state");

        return true;
    }

    static boolean verificarPais(String estado) throws RunTimeExceptionExample {
        if (estado.equalsIgnoreCase("BR"))
            throw new RunTimeExceptionExample();
        return true;
    }
}
