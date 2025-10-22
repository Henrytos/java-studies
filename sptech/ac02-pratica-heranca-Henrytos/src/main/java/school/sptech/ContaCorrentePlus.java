package school.sptech;

import java.lang.reflect.Field;
import java.util.Objects;

public class ContaCorrentePlus extends ContaCorrente {
    private Integer pontos;

    public ContaCorrentePlus(String numero, String titular) {
        super(numero, titular);
        this.pontos = 0;
    }

    public ContaCorrentePlus() {
    }

    public void trocarPontos() {
        if(this.pontos < 100)
            return;

        super.depositar(this.pontos * 0.05);
        this.pontos = 0;
    }

    public void depositar(Double valor) {
        super.depositar(valor);

        if(valor == null || valor <= 0.0)
            return;

        Double divisor = 2.00;

        if (pontos < 1000)
            divisor=10.00;
        else if (pontos< 10000)
            divisor=5.00;

        this.pontos+= (int) (valor/divisor);
    }


    public Integer getPontos() {
        return pontos;
    }


    // <editor-fold desc="Não alterar - Será usado nos testes" state="collapsed">
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ContaCorrentePlus that = (ContaCorrentePlus) o;

        try {
            Class<ContaCorrentePlus> clazz = ContaCorrentePlus.class;
            Field pontos = clazz.getDeclaredField("pontos");
            return Objects.equals(pontos.get(this), pontos.get(that));
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
    //</editor-fold>
}
