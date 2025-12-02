package school.sptech;

public class Musica extends Midia {

    private final String artista;

    private final String album;

    private final Genero genero;


    public Musica(String nome, Double duracaoMinutos) {
        super(nome, duracaoMinutos);
        this.artista = "";
        this.album = "";
        this.genero = null;
    }

    public Musica(String nome, Double duracaoMinutos, String artista, String album, Genero genero) {
        super(nome, duracaoMinutos);
        this.artista = artista;
        this.album = album;
        this.genero = genero;
    }

    @Override
    public Boolean buscar(String query) {
        return this.getNome().toLowerCase().contains(query.toLowerCase()) ||
               this.getAlbum().toLowerCase().contains(query.toLowerCase()) ||
               this.getArtista().toLowerCase().contains(query.toLowerCase()) ||
               this.getGenero().toString().toLowerCase().contains(query.toLowerCase());
    }

    public String getArtista() {
        return artista;
    }

    public String getAlbum() {
        return album;
    }

    public Genero getGenero() {
        return genero;
    }
}
