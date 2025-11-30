package school.sptech;

public enum Simbolo {
    A("1"),
    DOIS("2"),
    TRES("3"),
    QUARTO("4"),
    CINCO("5"),
    SEIS("6"),
    SETE("7"),
    OUITO("8"),
    DEZ("9"),
    J("10"),
    Q("11"),
    K("12");
    private String numero;

    Simbolo(String numero) {
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    static Simbolo valueOf(Integer numero){
        for (Simbolo value : values()) {
            if(value.getNumero().equals(numero.toString()))
                return value;
        }
    throw new IllegalArgumentException();
    }
}
