package school.sptech;

import java.util.Arrays;

public class Partida {

    private final Integer[][] matriz;
    private int jogadorAtual;
    private Integer vencedor;

    public Partida() {
        this.matriz = new Integer[3][3];
        this.jogadorAtual = 1;
    }

    public boolean inserirValor(int x, int y) {
        System.out.println("X=" + x);
        System.out.println("Y=" + y);
        Integer jogador = this.matriz[y][x];

        if (jogador != null) {
            return false;
        }

        this.matriz[y][x] = this.jogadorAtual;
        return true;
    }

    public void alternarJogador() {

        if (this.jogadorAtual == 1) {
            this.jogadorAtual = 2;
            return;
        }

        this.jogadorAtual = 1;
    }

    public void validarJogada() {
        for (int i = 0; i < this.matriz.length; i++) {
            if (matriz[i][0] != null && matriz[i][1] != null && matriz[i][2] != null) {
                if (matriz[i][0] == 1 && matriz[i][1] == 1 && matriz[i][2] == 1) {
                    this.vencedor = 1;
                }
                if (matriz[i][0] == 2 && matriz[i][1] == 2 && matriz[i][2] == 2) {
                    this.vencedor = 2;
                }
            }
            if (matriz[0][i] != null && matriz[1][i] != null && matriz[2][i] != null) {
                if (matriz[0][i] == 1 && matriz[1][i] == 1 && matriz[2][i] == 1) {
                    this.vencedor = 1;
                }
                if (matriz[0][i] == 2 && matriz[1][i] == 2 && matriz[2][i] == 2) {
                    this.vencedor = 2;
                }
            }

            if (matriz[0][0] != null && matriz[1][1] != null && matriz[2][2] != null) {
                if (matriz[0][0] == 1 && matriz[1][1] == 1 && matriz[2][2] == 1) {
                    this.vencedor = 1;
                }
                if (matriz[0][0] == 2 && matriz[1][1] == 2 && matriz[2][2] == 2) {
                    this.vencedor = 2;
                }
            }

            if (matriz[0][2] != null && matriz[1][1] != null && matriz[2][0] != null) {
                if (matriz[0][2] == 1 && matriz[1][1] == 1 && matriz[2][0] == 1) {
                    this.vencedor = 1;
                }
                if (matriz[0][2] == 2 && matriz[1][1] == 2 && matriz[2][0] == 2) {
                    this.vencedor = 2;
                }
            }
        }



        Boolean estaCheio = true;

        for (Integer[] integers : this.matriz) {
            for (Integer integer : integers) {
                if (integer == null)
                    estaCheio = false;
            }
        }

        if (estaCheio)
        {
            System.out.println(estaCheio);
            this.vencedor = 0;
        }

    }

    public String getMensagem() {
        String mensagem = "";
        if(this.vencedor != null){
            if(this.vencedor != 0)
                mensagem="O jogador "+this.vencedor+" venceu";
            else
                mensagem="Empate!";
        }else
            mensagem =  "Vez do jogador "+this.jogadorAtual;

        return mensagem;
    }

    public Integer[][] getMatriz() {
        return matriz;
    }
}
