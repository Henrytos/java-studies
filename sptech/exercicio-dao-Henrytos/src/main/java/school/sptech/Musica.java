package school.sptech;

import java.util.Objects;

public class Musica {

    private Integer id;
    private String nome;
    private String artista;
    private String album;
    private Integer duracao;

    public Musica() {
    }

    public Musica(Integer id, String nome, String artista, String album, Integer duracao) {
        this.id = id;
        this.nome = nome;
        this.artista = artista;
        this.album = album;
        this.duracao = duracao;
    }

    // <editor-fold desc="Getters e Setters">

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }
    // </editor-fold>

    @Override
    public String toString() {
        return "Musica{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", artista='" + artista + '\'' +
                ", album='" + album + '\'' +
                ", duracao=" + duracao +
                '}';
    }

    // <editor-fold desc="Ignore por enquanto :D">
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Musica musica = (Musica) o;
        return Objects.equals(id, musica.id) && Objects.equals(nome, musica.nome) && Objects.equals(artista, musica.artista) && Objects.equals(album, musica.album) && Objects.equals(duracao, musica.duracao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, artista, album, duracao);
    }
    // </editor-fold>
}
