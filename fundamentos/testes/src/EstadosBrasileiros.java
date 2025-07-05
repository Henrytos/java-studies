package testes.src;

public enum EstadosBrasileiros {
    SAO_PAULO("são paulo", "SP"),
    RIO_DE_JANEIRO("rio de janeiro", "RJ");

    ;

    private String nome;
    private String sigla;

    EstadosBrasileiros(String nome, String sigla){
        this.nome = nome;
        this.sigla = sigla;
    }

    public String  getNome(){
        return this.nome;
    }

    public String  getSigla(){
        return this.sigla;
    }

}
