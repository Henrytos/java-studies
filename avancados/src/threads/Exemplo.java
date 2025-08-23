package threads;

class PrintMessage implements Runnable {
    String message;

    public PrintMessage(String message) {
        this.message = message;
    }


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        System.out.println(message);
    }
}


public class Exemplo {

    public static void main(String[] args) {

        System.out.println(Thread.activeCount());
//      Thread é a menor unidade de um processo
        Thread thread1 = new Thread(new PrintMessage("hello thread 1"), "T1");
        Thread thread2 = new Thread(()-> System.out.println(Thread.currentThread().getName()), "T2");

        /**
         * permitindo que um programa realize
         * múltiplas tarefas de forma concorrente ou paralela
         * dentro do mesmo espaço de memória e recursos do sistema
         */

        thread1.start();
        thread2.start();

        System.out.println(Thread.activeCount());


    }
}
