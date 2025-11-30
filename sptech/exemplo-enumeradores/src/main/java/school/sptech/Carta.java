package school.sptech;

public class Carta {

    private Simbolo simbolo;
    private Naipe naipe;

    public Carta(Simbolo simbolo, Naipe naipe) {
        this.simbolo = simbolo;
        this.naipe = naipe;
    }

    public Simbolo getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(Simbolo simbolo) {
        this.simbolo = simbolo;
    }

    public Naipe getNaipe() {
        return naipe;
    }

    public void setNaipe(Naipe naipe) {
        this.naipe = naipe;
    }

    @Override
    public String toString() {
        return "Sua carta eh %s".formatted(simbolo+""+naipe);
    }
}
