package testes.src;

import java.time.LocalDate;

public  class Main {

    public static void main(String[] args) {

        for(EstadosBrasileiros estadosBrasileiro: EstadosBrasileiros.values()){
            System.out.println("ESTADO LOCALIZADO");
            System.out.println("Nome do estado ".concat(estadosBrasileiro.getNome()));
            System.out.println("Sigla do estado ".concat(estadosBrasileiro.getSigla()));
            System.out.println();
        }
    }
}