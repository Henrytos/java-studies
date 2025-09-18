package school.sptech;

import java.util.Locale;

public class BilheteUnico {
    String numero; // variavel de instância - atributo
    String nomeUsuario;
    String cor;
    Double saldo;
    Boolean idoso;
    Boolean estudante;

    Boolean recarregar(Double valor) {
        if (valor < 5.00 || valor > 200.00) {
            System.out.println("Valor invalido para recarga!!");
            return false;
        }

        saldo += valor;
        System.out.println("Recarga realizada com sucesso");
        return true;
    }

    void passarBilhete(Boolean integracao) {
        if (idoso || integracao) {
            System.out.println("subsidion R$0,00");
            return;
        }

        Double valorDaPassagem = estudante ? 2.6 : 5.2;

        if (valorDaPassagem > saldo) {
            System.out.println("Saldo insuficiente");
            Double diferença = valorDaPassagem-saldo;

            Locale locale = new Locale("pt", "BR");

            System.out.println("Falta:R$%s,00".formatted());
            return;
        }

        saldo -= valorDaPassagem;
        System.out.println("Passou com sucesso");
        System.out.println("VALOR DA PASSAGEM "+ valorDaPassagem);
        System.out.println("SALDO "+ saldo);

    }

    public void printarInformacoes(){
        System.out.println("BILHETE "+numero);
        String menssagem = """
                {
                    numero: %s
                    nomeUsario: %s
                    cor: %s
                    saldo: %s
                    idoso: %s
                    estudante: %s    
                }
                """;
        System.out.println(menssagem.formatted(numero, nomeUsuario, cor, saldo, idoso, estudante));

    }

    @Override
    public String toString() {
        return """
                {
                    numero: %s
                    nomeUsario: %s
                    cor: %s
                    saldo: %s
                    idoso: %s
                    estudante: %s    
                }
                """.formatted(numero, nomeUsuario, cor, saldo, idoso, estudante);
    }

    @Override
    public int hashCode() {
        return Integer.valueOf(this.numero);
    }
}
