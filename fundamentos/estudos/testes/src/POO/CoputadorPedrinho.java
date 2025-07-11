package testes.src.POO;

import POO.MensagerInstantaneo;

public class CoputadorPedrinho {
    public static void main(String[] args) {
        MensagerInstantaneo mi;
        String tipo = "msnf";

        if (tipo == "msnf") {
            mi = new MsnFacebook();
        } else if (tipo == "msnt") {
            mi = new MsnTelegram();
        } else {
            mi = new MsnMessanger();
        }

        mi.enviarMenssagem();
        mi.receberMenssagem();

        // metodos protegidos
        //msnMessanger.salvarHistoricoDeMenssagem();
        //msnMessanger.verificarSeEstaConcetado();
    }
}
