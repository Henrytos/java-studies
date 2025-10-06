package school.sptech;

public class Aluno {
    private final String ra;
    private final String nome;
    private Double notaAc1;
    private Double notaAc2;
    private Double notaAc3;

    public Aluno(String ra, String nome) {
        this.ra = ra;
        this.nome = nome;
    }

    public String getRa() {
        return ra;
    }

    public String getNome() {
        return nome;
    }

    public Double getNotaAc1() {
        return notaAc1;
    }

    public void setNotaAc1(Double notaAc1) {
        this.notaAc1 = notaAc1;
    }

    public Double getNotaAc2() {
        return notaAc2;
    }

    public void setNotaAc2(Double notaAc2) {
        this.notaAc2 = notaAc2;
    }

    public Double getNotaAc3() {
        return notaAc3;
    }

    public void setNotaAc3(Double notaAc3) {
        this.notaAc3 = notaAc3;
    }

    public double calcularMediaFinal(){
        return this.notaAc1 * 0.25 + this.notaAc2 * 0.35 + this.notaAc3 * 0.40;
    }

    @Override
    public String toString() {
        return "Aluno{" +
               "ra='" + ra + '\'' +
               ", nome='" + nome + '\'' +
               ", notaAc1=" + notaAc1 +
               ", notaAc2=" + notaAc2 +
               ", notaAc3=" + notaAc3 +
               '}';
    }
}
