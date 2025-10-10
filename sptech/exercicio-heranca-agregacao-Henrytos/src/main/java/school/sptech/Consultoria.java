package school.sptech;

import school.sptech.especialistas.DesenvolvedorMobile;
import school.sptech.especialistas.DesenvolvedorWeb;

import java.util.ArrayList;
import java.util.List;

public class Consultoria {

    private String nome;
    private Integer vagas;
    private List<Desenvolvedor> desenvolvedores = new ArrayList<>();


    public Consultoria() {
    }

    public Consultoria(String nome, Integer vagas) {
        this.nome = nome;
        this.vagas = vagas;
    }

    public void contratar(Desenvolvedor desenvolvedor) {
        if (this.desenvolvedores.size() >= this.vagas) {
            return;
        }

        this.desenvolvedores.add(desenvolvedor);
    }

    public void contratarFullstack(DesenvolvedorWeb desenvolvedor) {
        if (desenvolvedor.isFullstack()) {
            this.contratar(desenvolvedor);
        }

    }

    public Double getTotalSalarios() {
        Double total = 0.0;
        for (Desenvolvedor desenvolvedor : this.desenvolvedores) {
            total += desenvolvedor.calcularSalario();
        }

        return total;
    }

    public List<Desenvolvedor> buscarPorTecnologia(String tecnologia) {
        return this.desenvolvedores.stream().filter(dev ->
        {
            if (dev instanceof DesenvolvedorMobile mobile) {
                return mobile.getPlataforma().contains(tecnologia) || mobile.getLinguagem().contains(tecnologia);
            }

            if (dev instanceof DesenvolvedorWeb desenvolvedorWeb) {
                return desenvolvedorWeb.getSgbd().contains(tecnologia) || desenvolvedorWeb.getBackend().contains(tecnologia) || desenvolvedorWeb.getFrontend().contains(tecnologia);
            }

            return false;
        }).toList();

    }

    public Double getTotalSalariosPorTecnologia(String tecnologia){
        List<Desenvolvedor> desenvolvedores = this.buscarPorTecnologia(tecnologia);

        Double total = Double.valueOf(0.0);

        for (Desenvolvedor desenvolvedor : desenvolvedores) {
            total += desenvolvedor.calcularSalario();
        }

        return total;
    }

    public Integer qtdDesenvolvedoresMobile() {
        return this.desenvolvedores.stream().filter(dev -> dev instanceof DesenvolvedorMobile).toList().size();
    }

    public List<Desenvolvedor> buscarPorSalarioMaiorIgualQue(Double salario) {
        return this.desenvolvedores.stream().filter(dev -> dev.calcularSalario() >= salario).toList();
    }

    public Desenvolvedor buscarMenorSalario() {
        if (this.desenvolvedores.isEmpty()) {
            return null;
        }

        return this.desenvolvedores.stream().sorted((a, b) -> {
            if (a.calcularSalario() < b.calcularSalario()) {
                return -1;
            }
            return 1;
        }).toList().get(0);
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getVagas() {
        return vagas;
    }

    public void setVagas(Integer vagas) {
        this.vagas = vagas;
    }

    public List<Desenvolvedor> getDesenvolvedores() {
        return desenvolvedores;
    }

    public void setDesenvolvedores(List<Desenvolvedor> desenvolvedores) {
        this.desenvolvedores = desenvolvedores;
    }
}
