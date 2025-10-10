package school.sptech.ex3;

public class Funcionario {
    String nome;
    String cargo;
    Double salario;

    void reajustarSalario(Integer reajuste) {
        salario = salario + salario * (reajuste / 100.00);
    }

    Double calcularValorHora() {
        return salario / 220.00;
    }

    Double calcularHoraExtra(Integer horasTrabalhadas, Integer percentualAdicionado) {
        return Double.valueOf(horasTrabalhadas) * calcularValorHora() * ((100 + percentualAdicionado)/100.00);
    }
}
