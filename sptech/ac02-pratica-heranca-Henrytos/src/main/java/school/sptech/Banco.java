package school.sptech;

import java.util.ArrayList;
import java.util.List;

public class Banco {

    private String nome;
    private List<ContaCorrente> contas;

    public Banco() {
        contas = new ArrayList<>();
    }

    public Banco(String nome) {
        this();
        this.nome = nome;
    }

    public void criarConta(String numero, String titular){
        ContaCorrente contaCorrente = new ContaCorrente(numero,  titular);

        if(numero==null || titular == null || numero.isBlank() || titular.isBlank())
            return;

        this.contas.add(contaCorrente);
    }

    public void criarContaPlus(String numero, String titular){
        ContaCorrentePlus contaCorrente = new ContaCorrentePlus(numero,  titular);

        if(numero==null || titular == null || numero.isBlank() || titular.isBlank())
            return;

        this.contas.add(contaCorrente);
    }

    public ContaCorrente buscarPorNumero(String numero){
        return this.contas.stream().filter(c-> c.getNumero().equals(numero)).findFirst().orElse(null);
    }


    public void removerPorNumero(String numero){
        this.contas.remove(this.buscarPorNumero(numero));
    }

    public List<ContaCorrentePlus> buscarContasPlus(){
        List<ContaCorrentePlus> contaCorrentePlus = new ArrayList<>();

        for (ContaCorrente conta : this.contas) {
            if(conta instanceof ContaCorrentePlus correntePlus)
                contaCorrentePlus.add(correntePlus);
        }

        return contaCorrentePlus;
    }

    public List<ContaCorrentePlus> buscarContasComPontosMaior(Integer pontos){
        return this.buscarContasPlus().stream().filter(c-> c.getPontos()>=pontos).toList();
    }

    public String getNome() {
        return nome;
    }

    public List<ContaCorrente> getContas() {
        return contas;
    }

}