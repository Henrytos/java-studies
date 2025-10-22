package school.sptech;

import java.lang.reflect.Field;
import java.util.Objects;

public class ContaCorrente {
    private final String numero;
    private final String titular;
    private Double saldo;

    public ContaCorrente() {
    this("","");
    }

    public ContaCorrente(String numero, String titular) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = 0.0;
    }


    public void depositar(Double valor){
        if(valor == null || valor <= 0.0)
            return;

        this.saldo+=valor;
    }

    public void sacar(Double valor){
        if(valor == null || valor <= 0.0 || valor > this.saldo)
            return;

        this.saldo-=valor;
    }


    public String getNumero() {
        return numero;
    }


    public String getTitular() {
        return titular;
    }

    public Double getSaldo() {
        return saldo;
    }


    // <editor-fold desc="Não alterar - Será usado nos testes" state="collapsed">
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ContaCorrente that = (ContaCorrente) o;

        try {
            Class<ContaCorrente> clazz = ContaCorrente.class;
            Field numero = clazz.getDeclaredField("numero");
            Field titular = clazz.getDeclaredField("titular");
            Field saldo = clazz.getDeclaredField("saldo");

            return Objects.equals(numero.get(this), numero.get(that)) && Objects.equals(titular.get(this), titular.get(that)) && Objects.equals(saldo.get(this), saldo.get(that));
        } catch (ReflectiveOperationException e)  {
            throw new RuntimeException(e);
        }
    }
    //</editor-fold>
}
