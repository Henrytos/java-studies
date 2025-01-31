package app.vercel.henry_franz.intro;

public class Matriz {
    public static void main(String[] args) {
        String[][] messesComDiasDoAno = new String[12][2];

        String[] messesDoAno = {"janeiro", "fevereiro", "março", "maio", "abril", "junho", "julho", "agosto", "setembro", "otubro", "novembro", "dezembro"};
        int[] diasDoAno = { 31, 28, 28, 30, 30, 30, 31, 31, 30, 30, 30, 31};

        for (int i = 0; i < messesComDiasDoAno.length; i++) {
            messesComDiasDoAno[i][0] = messesDoAno[i];
            messesComDiasDoAno[i][1] = String.valueOf(diasDoAno[i]);
        }

        for (String[] mesComDia: messesComDiasDoAno){
            for (String dado: mesComDia){
                System.out.print(dado+" ");
            }
            System.out.println();
        }
    }
}
