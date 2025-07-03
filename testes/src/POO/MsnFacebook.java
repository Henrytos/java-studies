package POO;

public class MsnFacebook extends MensagerInstantaneo {

    public void enviarMenssagem(){
        if (this.verificarSeEstaConcetado())
            System.out.println("ENVIANDO MENSSAGEM FACEBOOK");
    }

    public void  receberMenssagem(){
        if (this.verificarSeEstaConcetado())
            System.out.println("RECEBER MENSSAGEM FACEBOOK");
    }

    protected boolean verificarSeEstaConcetado(){
        System.out.println("VERIFICANDO SE ESTA CONECTADO FACEBOOK");
        return true;
    }

    protected void salvarHistoricoDeMenssagem(){
        System.out.println("SALVANDO HISTORICO DE MENSSAGEM FACEBOOK");
    }
}
