package POO;

public class MAIN {
    public static void main(String[] args) {
        MsnMessanger msnMessanger = new MsnMessanger();
        msnMessanger.enviarMenssagem();
        msnMessanger.receberMenssagem();

        // metodos protegidos
        //msnMessanger.salvarHistoricoDeMenssagem();
        //msnMessanger.verificarSeEstaConcetado();
    }
}
