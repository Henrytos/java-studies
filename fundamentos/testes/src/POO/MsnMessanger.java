package testes.src.POO;

import POO.MensagerInstantaneo;

public class MsnMessanger extends MensagerInstantaneo {

    public void enviarMenssagem() {
        if (this.verificarSeEstaConcetado())
            System.out.println("ENVIANDO MENSSAGEM MENSSAGER");
    }

    public void receberMenssagem() {
        if (this.verificarSeEstaConcetado())
            System.out.println("RECEBER MENSSAGEM MENSSAGER");
    }

    protected boolean verificarSeEstaConcetado() {
        System.out.println("VERIFICANDO SE ESTA CONECTADO MENSSAGER");
        return true;
    }

    protected void salvarHistoricoDeMenssagem() {
        System.out.println("SALVANDO HISTORICO DE MENSSAGEM MENSSAGER");
    }
}
