package school.sptech;

public abstract class Midia {

    private final String nome;
    private final Double duracaoMinutos;



    public Midia(String nome, Double duracaoMinutos) {
        this.nome = nome;
        this.duracaoMinutos = duracaoMinutos;
    }

    abstract public Boolean buscar(String query);

    public String getNome() {
        return nome;
    }

    public Double getDuracaoMinutos() {
        return duracaoMinutos;
    }
}
