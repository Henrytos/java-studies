package school.sptech;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ContaCorrente {
    private String titular;
    private String agencia;
    private String numero;
    private List<Operacao> operacoes = new ArrayList<>();

    public ContaCorrente() {
    }

    public ContaCorrente(String titular, String agencia, String numero, List<Operacao> operacoes) {
        this.titular = titular;
        this.agencia = agencia;
        this.numero = numero;
        this.operacoes = operacoes;
    }

    public void adicionarOperacao(String categoria, String descricao, Double valor) {
        if (
                categoria == null
                ||
                descricao == null
                ||
                valor == null
                ||
                valor <= 0
                ||
                categoria.isBlank()
                ||
                descricao.isBlank()
        ) {
            return;
        }

        Operacao operacao = new Operacao(categoria, descricao, valor);

        this.operacoes.add(operacao);
    }

    List<Operacao> buscarOperacoesPorCategoria(String categoria) {
        return this.operacoes.stream().filter(o -> o.getCategoria().equalsIgnoreCase(categoria)).toList();
    }

    List<Operacao> buscarOperacoesPorValor(Double valor) {
        return this.operacoes.stream().filter(o -> o.getValor().equals(valor)).toList();
    }

    List<Operacao> buscarOperacoesSaida() {
        return this.operacoes.stream().filter(o -> o.getValor() < 0).toList();
    }

    List<Operacao> buscarOperacoesEntrada() {
        return this.operacoes.stream().filter(o -> o.getValor() > 0).toList();
    }

    List<Operacao> buscarOperacoesPorDescricao(String descricao){
        if(descricao == null){
            return new ArrayList<>();
        }

        return this.operacoes.stream().filter(o->o.getDescricao().toLowerCase().contains(descricao.toLowerCase())).toList();
    }

    Double buscarMaiorValor(){
        if(this.operacoes.isEmpty()){
            return 0.0;
        }

        return this.operacoes.stream().sorted((a,b)->{
            if(a.getValor() < b.getValor()){
                return 1;
            }

            return -1;
        }).toList().get(0).getValor();
    }

    Double buscarMenorValor(){
        if(this.operacoes.isEmpty()){
            return 0.0;
        }

        return this.operacoes.stream().sorted((a,b)->{
            if(a.getValor() < b.getValor()){
                return -1;
            }

            return 1;
        }).findFirst().orElse(null).getValor();
    }

    Double obterSaldo(){
        return this.operacoes.stream().mapToDouble(Operacao::getValor).sum();
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
