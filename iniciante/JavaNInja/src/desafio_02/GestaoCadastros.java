package desafio_02;

import java.util.Scanner;

public  class GestaoCadastros {
    public static void main(String[] args) {
        String[] ninjas = new String[10];
        int opcao = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("---BEM VINDO A GESTÃO DE KONOHA---");

        while(opcao!=3){
            System.out.println("(1) Cadastrar um novo ninja");
            System.out.println("(2) ver todos os ninjas cadastrados");
            System.out.println("(3) sair do programa");
            opcao = scanner.nextInt();

            switch (opcao){
                case 1:
                    System.out.println("Porfavor informe o nome do ninja:");
                    String nome = scanner.next();
                    int ultimaPosicao = 0;

                    for (int i=0; i<ninjas.length ;i++){
                        if(ninjas[i] ==null){
                            ultimaPosicao = i;
                            break;
                        }
                    }

                    ninjas[ultimaPosicao]=nome;
                    System.out.println("Ninja cadastrado com sucesso");
                    break;
                case 2:
                    for (int i=1;i<=ninjas.length && ninjas[i-1]!=null;i++){
                        System.out.println("Ninja "+i+" - "+ninjas[i-1]);
                    }
                    break;
                case 3:
                    System.out.println("Finalizando programa....");
                    break;
                default:
                    System.out.println("Opção invalida, se desejar só selecionar opção que encerra o programa");
            }

        }

    }

}