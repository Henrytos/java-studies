package school.sptech.ex1;

public class Bolo {
    String sabor;
    Double valor;
    Integer quantidadeVendida;
    Integer quantidadeEmEstoque;

    public void venderBolo(Integer quantidadeDesejada){
        if(quantidadeDesejada<1 || quantidadeDesejada>quantidadeEmEstoque){
            return;
        }

        quantidadeVendida+=quantidadeDesejada;
        quantidadeEmEstoque-=quantidadeDesejada;
    }

    public  void aumentarEstoque(Integer quantidadeASerAdicionada){
        if(quantidadeASerAdicionada < 1){
            return;
        }

        quantidadeEmEstoque+=quantidadeASerAdicionada;
    }

    public Integer quantidadeDisponivel(){
        return quantidadeEmEstoque;
    }

    public  Double totalVendido(){
        return quantidadeVendida * valor;
    }
}
