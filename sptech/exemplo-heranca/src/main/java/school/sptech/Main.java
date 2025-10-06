package school.sptech;

public class Main {

    public static void main(String[] args) {

        Aluno aluno1 = new Aluno("01251020", "henry franz");


        aluno1.setNotaAc1(8.0);
        aluno1.setNotaAc2(9.0);
        aluno1.setNotaAc3(10.0);

        System.out.println(aluno1.calcularMediaFinal());

        Faculdade faculdade1 = new Faculdade("SPTECH");

        AlunoPos alunoPos1 = new AlunoPos("01251030", "NAMIE");
        alunoPos1.setNotaAc1(8.0);
        alunoPos1.setNotaAc2(9.0);
        alunoPos1.setNotaAc3(10.0);
        alunoPos1.setNotaTcc(10.0);
        System.out.println(alunoPos1.calcularMediaFinal());
        faculdade1.matricularAluno(aluno1);
        faculdade1.matricularAluno(alunoPos1);

        System.out.println(alunoPos1.getClass().equals(AlunoPos.class));
        faculdade1.exibirALunos();
        faculdade1.exibirMeidas();
        faculdade1.exibirNotasTcc();
    }

}
