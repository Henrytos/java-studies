package school.sptech;

import java.util.ArrayList;
import java.util.List;

public class Episodio extends Midia implements Avaliavel {

    private final String podcast;

    private final List<Integer> avaliacoes;

    public Episodio(String nome, Double duracaoMinutos, String podcast) {
        super(nome, duracaoMinutos);
        this.podcast = podcast;
        this.avaliacoes = new ArrayList<>();
    }

    @Override
    public Boolean buscar(String query) {
        return this.getNome().toLowerCase().contains(query.toLowerCase()) || getPodcast().toLowerCase().contains(query.toLowerCase());
    }



    public String getPodcast() {
        return podcast;
    }

    @Override
    public void avaliar(Integer nota) {
        if(nota ==null || nota < 0 || nota > 10)
            return;
        this.avaliacoes.add(nota);
    }



    @Override
    public Double calcularMedia() {
        if(this.avaliacoes.isEmpty())
            return 0.0;

        return this.avaliacoes.stream().mapToDouble(a -> Double.valueOf(a.toString())).sum() / (double) this.avaliacoes.size();
    }

    public List<Integer> getAvaliacoes() {
        return avaliacoes;
    }
}
