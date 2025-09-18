package school.sptech;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

    private String nome;
    private Usuario usuario;
    private List<Musica> musicas = new ArrayList<>();

    public Playlist(String nome, Usuario usuario) {
        this.nome = nome;
        this.usuario = usuario;
    }

    public void adicionarMusica(Musica musica){

        this.musicas.add(musica);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    @Override
    public String toString() {
        return "Playlist{" +
               "nome='" + nome + '\'' +
               ", usuario=" + usuario +
               ", musicas=" + musicas +
               '}';
    }
}
