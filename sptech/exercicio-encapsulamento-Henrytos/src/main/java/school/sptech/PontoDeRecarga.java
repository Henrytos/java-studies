package school.sptech;

public class PontoDeRecarga {

    private Integer qtdConsultasRealizadas;
    private Integer qtdRecargasRealizadas;

    public PontoDeRecarga() {
    }

    public PontoDeRecarga(Integer qtdConsultasRealizadas, Integer qtdRecargasRealizadas) {
        this.qtdConsultasRealizadas = qtdConsultasRealizadas;
        this.qtdRecargasRealizadas = qtdRecargasRealizadas;
    }

    public Double consultarSaldo(BilheteUnico bilhete) {
        if (bilhete == null || bilhete.getBloqueado()) {
            System.out.println("bilhete único bloquead");
            return 0.0;
        }

        this.qtdConsultasRealizadas++;
        return bilhete.getSaldo();
    }

    public void recarregar(BilheteUnico bilhete, Double valor) {
        if (valor == null || valor < 5.0) {
            System.out.println("Valor mínimo de recarga não atingido");
            return;
        }

        if(bilhete.getBloqueado()){
            System.out.println("bilhete único bloqueado");
            return;
        }

        bilhete.setSaldo(bilhete.getSaldo() + valor);
        this.qtdRecargasRealizadas++;
    }

    public Integer getQtdConsultasRealizadas() {
        return qtdConsultasRealizadas;
    }

    public Integer getQtdRecargasRealizadas() {
        return qtdRecargasRealizadas;
    }

    public void bloquear(BilheteUnico bilhete){
        bilhete.setBloqueado(true);
    }
}
