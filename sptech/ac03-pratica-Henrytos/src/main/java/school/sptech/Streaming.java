package school.sptech;

import java.util.ArrayList;
import java.util.List;

public class Streaming {

    private final String nome;
    private List<Midia> midias ;
    private List<Playlist> playlists ;

    public Streaming(String nome) {
        this.nome = nome;
        this.midias = new ArrayList<>();
        this.playlists = new ArrayList<>();
    }

    public Streaming(String nome, List<Midia> midias) {
        this.nome = nome;
        this.midias = midias;
        this.playlists = new ArrayList<>();
    }

    public void adicionarMidia(Midia midia) {
        this.midias.add(midia);
    }

    public List<Midia> buscarMidia(String query) {
        return this.midias.stream().filter(m -> m.buscar(query)).toList();
    }

    public List<Musica> buscarMusicaPorGenero(Genero genero) {
        return this.midias.stream().filter(m -> m instanceof Musica).map(m -> (Musica) m).filter(m -> m.getGenero().equals(genero)).toList();
    }

    public Integer getQuantidadeEpisodios(){
        return this.midias.stream().filter(m-> m instanceof Episodio).map(m-> (Episodio) m).toList().size();
    }

    public void adicionarPlaylist(String nome){
        Playlist playlist = new Playlist(nome);

        this.playlists.add(playlist);
    }

    public void adicionarMusicaNaPlaylist(Musica musica, String nomePlaylist){
        this.playlists.forEach(p->{
            if(p.getNome().equalsIgnoreCase(nomePlaylist))
                p.adicionarMusica(musica);
        });
    }

    public void avaliarMidia(String nome, Integer nota){
        this.midias.forEach(m->{
            if(m.getNome().equalsIgnoreCase(nome) && m instanceof Episodio episodio)
                episodio.avaliar(nota);
        });
    }

    public void avaliarPlaylist(String nome, Integer nota){
        this.playlists.forEach( p ->{
            if(p.getNome().equalsIgnoreCase(nome))
                p.avaliar(nota);
        });
    }

    public List<Avaliavel> buscarPorMediaMaior(Double media){
        List<Avaliavel> avaliaveis = new ArrayList<>();

        this.midias.forEach(m-> {
            if( m instanceof Episodio episodio){
                if(episodio.calcularMedia() > media)
                    avaliaveis.add(episodio);
            }
        });

        this.playlists.forEach(m-> {
            if( m instanceof Playlist playlist){
                if(playlist.calcularMedia() > media)
                    avaliaveis.add(playlist);
            }
        });

        return avaliaveis;
    }


    public String getNome() {
        return nome;
    }

    public List<Midia> getMidias() {
        return midias;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }
}
