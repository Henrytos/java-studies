package school.sptech;

import java.time.LocalDate;

public class Musica {
    private Integer id;
    private String nome;
    private String artista;
    private String album;
    private LocalDate dataLancamento;

    public Musica() {
    }

    public Musica(Integer id, String nome, String artista, String album, LocalDate dataLancamento) {
        this.id = id;
        this.nome = nome;
        this.artista = artista;
        this.album = album;
        this.dataLancamento = dataLancamento;
    }

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

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    @Override
    public String toString() {
        return "Musica{" +
               "id=" + id +
               ", nome='" + nome + '\'' +
               ", artista='" + artista + '\'' +
               ", album='" + album + '\'' +
               ", dataLancamento=" + dataLancamento +
               '}';
    }
}
