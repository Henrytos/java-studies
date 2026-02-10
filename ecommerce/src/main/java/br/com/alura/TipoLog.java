package br.com.alura;


public enum TipoLog {
    WARN("\u001B[33m"),
    ERROR("\u001B[31m"),
    FATAL("\u001B[31m"),
    INFO("\u001B[32m");

    private final String ansiColor;

    TipoLog(String ansiColor) {
        this.ansiColor = ansiColor;
    }

    public String getAnsiColor() {
        return ansiColor;
    }

    @Override
    public String toString() {
        return this.getAnsiColor().concat(this.name()).concat("\u001B[0m");
    }
}
