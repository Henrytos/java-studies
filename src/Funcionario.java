import java.lang.reflect.Array;
import java.util.ArrayList;

public class Funcionario {
    private String nome;
    private int idade;
    private double[] salarios;

    public Funcionario(String nome, int idade, double[] salarios){
        this.nome = nome;
        this.idade = idade;
        this.salarios = salarios;
    }

    public void imprimeMediaSalario() {
        double mediaSalaio = 0;
        for (double salario: this.salarios){
            mediaSalaio += salario;
        }
        mediaSalaio /= this.salarios.length;

        mediaSalaio = Math.ceil(mediaSalaio);

        System.out.printf("media de salario "+ mediaSalaio);
    }
}
