package school.sptech;

public class Onibus {
    private Integer qtdPassageiros;
    private Double valorPassagem;



    public void cobrarPassagem(BilheteUnico bilheteUnico) {
        if (bilheteUnico.getBloqueado()) {
            System.out.println("bilhete único bloqueado");
            return;
        }

        if (bilheteUnico.getSaldo() < this.valorPassagem) {
            System.out.println("Não há saldo suficiente para realizar a operação");
            return;
        }

        Double valorAPagar = bilheteUnico.getEstudante() ? this.valorPassagem / 2.0 : this.valorPassagem;

        bilheteUnico.setSaldo(bilheteUnico.getSaldo() - valorAPagar);

        qtdPassageiros++;
    }

    public void cobrarPassagem(Double dinheiro) {
        if (dinheiro < this.valorPassagem) {
            System.out.println("Dinheiro insuficiente para realizar operação");
            return;
        }

        qtdPassageiros++;
    }



    public Onibus() {
    }


    public Onibus(Integer qtdPassageiros, Double valorPassagem) {
        this.qtdPassageiros = qtdPassageiros;
        this.valorPassagem = valorPassagem;
    }

    public Integer getQtdPassageiros() {
        return qtdPassageiros;
    }


    public Double getValorPassagem() {
        return valorPassagem;
    }

}
