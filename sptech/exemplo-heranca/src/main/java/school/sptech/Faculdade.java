package school.sptech;

import java.util.ArrayList;
import java.util.List;

public class Faculdade {

    private String nome;

    private List<Aluno> alunos;

    public Faculdade(String nome) {
        this.nome = nome;
        this.alunos = new ArrayList<>();
    }

    public void matricularAluno(Aluno aluno) {
        this.alunos.add(aluno);
    }

    public void exibirALunos() {
        System.out.println("ALunos da " + this.nome);
        for (Aluno aluno : this.alunos) {
            System.out.println(aluno);
        }
    }

    public void exibirMeidas() {
        System.out.println("Meida dos alunos:");

        for (Aluno aluno : this.alunos) {
            System.out.println(aluno.calcularMediaFinal());
        }
    }

    public void exibirNotasTcc() {

        for (Aluno aluno : this.alunos) {
//            if(aluno.getClass().equals(AlunoPos.class)){
//                System.out.println(((AlunoPos) aluno).getNotaTcc());
//            }
//
//            if(aluno instanceof AlunoPos){
//                System.out.println(((AlunoPos) aluno).getNotaTcc());
//            }
            if(aluno instanceof  AlunoPos alunoPos){
                System.out.println(alunoPos.getNotaTcc());
            }

        }
    }
}
