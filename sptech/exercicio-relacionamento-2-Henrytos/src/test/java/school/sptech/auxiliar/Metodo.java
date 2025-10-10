package school.sptech.auxiliar;

public class Metodo {

    private String nome;
    private Class<?>[] args;

    public Metodo(String nome, Class<?>[] args) {
        this.nome = nome;
        this.args = args;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Class<?>[] getArgs() {
        return args;
    }

    public void setArgs(Class<?>[] args) {
        this.args = args;
    }
}

