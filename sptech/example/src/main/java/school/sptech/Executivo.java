package school.sptech;

public class Executivo extends Hospede {

    private Boolean vip;
    private Boolean internet;

    public Executivo() {
    }

    public Executivo(Boolean vip, Boolean internet) {
        this.vip = vip;
        this.internet = internet;
    }

    public Executivo(String nome, String documento, Integer quarto, Integer dias, Double consumo) {
        super(nome, documento, quarto, dias, consumo);
        this.vip = false;
        this.internet = false;
    }
    public Executivo(String nome, String documento, Integer quarto, Integer dias, Double consumo, Boolean vip, Boolean internet) {
        super(nome, documento, quarto, dias, consumo);
        this.vip = vip;
        this.internet = internet;
    }

    public Boolean getVip() {
        return vip;
    }

    public void setVip(Boolean vip) {
        this.vip = vip;
    }

    public Boolean getInternet() {
        return internet;
    }

    public void setInternet(Boolean internet) {
        this.internet = internet;
    }

    @Override
    public Double calcularDiaria() {
        Double valor = 1500.00;

        if (this.vip)
            valor += 1000.00;

        if (this.internet)
            valor += 500.00;

        return valor;
    }
}
