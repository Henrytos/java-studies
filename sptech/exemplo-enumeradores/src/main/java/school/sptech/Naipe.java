package school.sptech;

public enum Naipe {
    COPAS("♥\uFE0F"),
    ESPADAS("♠\uFE0F"),
    OUROS("\uD83D\uDD36"),
    PAUS("\uD83C\uDCAC");

    private final String unicode;

    Naipe(String unicode) { //ele smpre será privado
        this.unicode = unicode;
    }

    @Override
    public String toString() {
        return unicode;
    }

}
