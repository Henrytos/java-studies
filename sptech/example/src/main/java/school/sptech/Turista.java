package school.sptech;

public class Turista extends Hospede {

    private Boolean guiaTuristico;


    public Turista(Boolean guiaTuristico) {
        this.guiaTuristico = guiaTuristico;
    }

    public Turista(String nome, String documento, Integer quarto, Integer dias, Double consumo, Boolean guiaTuristico) {
        super(nome, documento, quarto, dias, consumo);
        this.guiaTuristico = guiaTuristico;
    }

    @Override
    public Double calcularDiaria() {
        Double valor = 1500.00;

        if (this.guiaTuristico)
            valor += 500.00;

        return valor;
    }

    public Boolean getGuiaTuristico() {
        return guiaTuristico;
    }

    public void setGuiaTuristico(Boolean guiaTuristico) {
        this.guiaTuristico = guiaTuristico;
    }

    @Override
    public String toString() {
        return "Turista{" +
               "guiaTuristico=" + guiaTuristico +
               "} " + super.toString();
    }
}
