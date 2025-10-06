package school.sptech;

public class AlunoPos extends Aluno {

    private Double notaTcc;

    public AlunoPos(String ra, String nome) {
        super(ra, nome);
    }

    public Double getNotaTcc() {
        return notaTcc;
    }

    public void setNotaTcc(Double notaTcc) {
        this.notaTcc = notaTcc;
    }

    @Override
    public double calcularMediaFinal() {
        return getNotaAc1() * 0.10 + getNotaAc2() * 0.15 + getNotaAc3() * 0.25 + this.notaTcc * 0.5;
    }

    @Override
    public String toString() {
        return "AlunoPos{" +
               "notaTcc=" + notaTcc +
               "} " + super.toString();
    }
}
