package school.sptech;

public class BilheteUnico {
    private String titular;
    private Integer ano ;
    private Boolean estudante;
    private Double saldo;
    private Boolean bloqueado;

    public BilheteUnico() {
    }

    public BilheteUnico(String titular, Integer ano, Boolean estudante, Double saldo, Boolean bloqueado) {
        this.titular = titular;
        this.ano = ano;
        this.estudante = estudante;
        this.saldo = saldo;
        this.bloqueado = bloqueado;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Boolean getEstudante() {
        return estudante;
    }

    public void setEstudante(Boolean estudante) {
        this.estudante = estudante;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Boolean getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(Boolean bloqueado) {
        this.bloqueado = bloqueado;
    }
}
