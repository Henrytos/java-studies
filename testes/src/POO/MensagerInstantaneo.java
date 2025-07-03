package POO;

public class MensagerInstantaneo {

    public void enviarMenssagem(){
        if (this.verificarSeEstaConcetado())
            System.out.println("ENVIANDO MENSSAGEM");
    }

    public void  receberMenssagem(){
        if (this.verificarSeEstaConcetado())
            System.out.println("RECEBER MENSSAGEM");
    }

    private boolean verificarSeEstaConcetado(){
        System.out.println("VERIFICANDO SE ESTA CONECTADO");
        return true;
    }

    private void salvarHistoricoDeMenssagem(){
        System.out.println("SALVANDO HISTORICO DE MENSSAGEM");
    }
}
