package school.sptech.ex2;

public class Encomenda {
    String tamanho;
    String enderecoRemetente;
    String enderecoDestinatario;
    Double distancia;
    Double valorProduto;

    Double calcularFrete() {
        Double frete = switch (tamanho) {
            case "P" -> valorProduto * 0.01;
            case "M" -> valorProduto * 0.03;
            case "G" -> valorProduto * 0.05;
            default -> 0.0;
        };

        if (distancia > 200.00) {
            frete += 7.0;
        } else if (distancia >= 50) {
            frete += 5.0;
        } else {
            frete += 3.0;
        }

        return frete;
    }

    void aplicarCupomDeDesconto(Integer descoto) {
        valorProduto = valorProduto * (1.0 - Double.valueOf(descoto / 100.00));
    }

    Double valorTotalDaEncomenda() {
        return valorProduto + calcularFrete();
    }
}
