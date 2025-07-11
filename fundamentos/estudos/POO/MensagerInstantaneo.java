package POO;

public abstract class MensagerInstantaneo {

    public abstract void enviarMenssagem();

    public abstract void  receberMenssagem();

    protected abstract boolean verificarSeEstaConcetado();

    protected abstract void salvarHistoricoDeMenssagem();
}
