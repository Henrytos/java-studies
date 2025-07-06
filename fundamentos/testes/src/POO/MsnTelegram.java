package testes.src.POO;

import POO.MensagerInstantaneo;

public class MsnTelegram extends MensagerInstantaneo {

    public void enviarMenssagem() {
        if (this.verificarSeEstaConcetado()) System.out.println("ENVIANDO MENSSAGEM TELEGRAM");
    }

    public void receberMenssagem() {
        if (this.verificarSeEstaConcetado()) System.out.println("RECEBER MENSSAGEM TELEGRAM");
    }

    protected boolean verificarSeEstaConcetado() {
        System.out.println("VERIFICANDO SE ESTA CONECTADO TELEGRAM");
        return true;
    }

    protected void salvarHistoricoDeMenssagem() {
        System.out.println("SALVANDO HISTORICO DE MENSSAGEM TELEGRAM");
    }
}
