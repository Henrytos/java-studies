package school.sptech;

import java.util.ArrayList;
import java.util.List;

public class Playlist implements Avaliavel {

    private final String nome;

    private List<Integer> avaliacoes;

    private List<Musica> musicas;

    public Playlist() {
        this.nome = "";
        this.avaliacoes = new ArrayList<>();
        this.musicas = new ArrayList<>();
    }

    public Playlist(String nome) {
        this.nome = nome;
        this.avaliacoes = new ArrayList<>();
        this.musicas = new ArrayList<>();
    }


    public void adicionarMusica(Musica musica){
        this.musicas.add(musica);
    }

    public Double calcularDuracaoMinutos(){
        return this.musicas.stream().mapToDouble(m -> m.getDuracaoMinutos()).sum();
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

    public String getNome() {
        return nome;
    }

    public List<Integer> getAvaliacoes() {
        return avaliacoes;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }
}
