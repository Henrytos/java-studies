package school.sptech;

public abstract class Pet {

    private String nome;
    private Double peso;
    private Integer idade;

    public Pet() {
        nome = "";
        peso = 0.0;
        idade = 0;
    }

    public Pet(String nome, Double peso, Integer idade) {
        this.nome = nome;
        this.peso = peso;
        this.idade = idade;
    }

    public abstract void comer();
    public abstract void emitirSom();


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Pet{" +
               "nome='" + nome + '\'' +
               ", peso=" + peso +
               ", idade=" + idade +
               '}';
    }
}
