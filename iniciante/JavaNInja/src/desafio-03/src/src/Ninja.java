import java.util.Objects;

public abstract class Ninja {
    private String nome;
    private int idade;
    private String missao;
    private NivelDeMissao nivelDificuldade;
    private StatusDaMisao statusMissao ;

    public Ninja(int idade, String nome, String missao, NivelDeMissao nivelDificuldade, StatusDaMisao statusMissao) {
        this.idade = idade;
        this.nome = nome;
        this.missao = missao;
        this.nivelDificuldade = nivelDificuldade;
        this.statusMissao = statusMissao;
    }

    public Ninja() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getMissao() {
        return missao;
    }

    public void setMissao(String missao) {
        this.missao = missao;
    }

    public NivelDeMissao getNivelDificuldade() {
        return nivelDificuldade;
    }

    public void setNivelDificuldade(NivelDeMissao nivelDificuldade) {
        this.nivelDificuldade = nivelDificuldade;
    }

    public StatusDaMisao getStatusMissao() {
        return statusMissao;
    }

    public void setStatusMissao(StatusDaMisao statusMissao) {
        this.statusMissao = statusMissao;
    }

    public void mostrarInformacoes(){
        System.out.println("Meu nome eh:".concat(nome));
        System.out.println("Idade eh:".concat(String.valueOf(idade)));
        System.out.println("Estou na misão :".concat(missao));
        System.out.println("Seu nivel de dificulda eh:".concat(nivelDificuldade.status));
        System.out.println("Atualmente esta no status:".concat(statusMissao.toString()));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ninja ninja = (Ninja) o;
        return idade == ninja.idade && Objects.equals(nome, ninja.nome) && Objects.equals(missao, ninja.missao) && Objects.equals(nivelDificuldade, ninja.nivelDificuldade) && Objects.equals(statusMissao, ninja.statusMissao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, idade, missao, nivelDificuldade, statusMissao);
    }

    @Override
    public String toString(){
        return this.nome;
    }
}
