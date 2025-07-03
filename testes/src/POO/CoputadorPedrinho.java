package POO;

public class CoputadorPedrinho {
    public static void main(String[] args) {
        MsnMessanger msnMessanger = new MsnMessanger();
        msnMessanger.enviarMenssagem();
        msnMessanger.receberMenssagem();

        MsnFacebook msnFacebook = new MsnFacebook();
        msnFacebook.enviarMenssagem();
        msnFacebook.receberMenssagem();

        MsnTelegram msnTelegram = new MsnTelegram();
        msnTelegram.enviarMenssagem();
        msnTelegram.receberMenssagem();


        // metodos protegidos
        //msnMessanger.salvarHistoricoDeMenssagem();
        //msnMessanger.verificarSeEstaConcetado();
    }
}
