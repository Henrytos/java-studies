package school.sptech;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;


public class MusicaDao {

    private final JdbcTemplate jdbcTemplate;

    public MusicaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /* Escreva os métodos abaixo */
    public List<Musica> findAll() {
        List<Musica> musicas = this.jdbcTemplate.query("SELECT * FROM musica", new BeanPropertyRowMapper<>(Musica.class));
        return musicas;
    }

    public Musica findById(Integer id) {
        List<Musica> musicas = this.jdbcTemplate.query(
                "SELECT * FROM musica WHERE id = ?",
                new BeanPropertyRowMapper<>(Musica.class),
                id
        );

        if(musicas.isEmpty()){
            return null;
        }

        return musicas.get(0);
    }

    public List<Musica> findByNomeLike(String nome) {
        if(nome == null){
            return null;
        }

        List<Musica> musicas = this.jdbcTemplate.query(
                "SELECT * FROM musica WHERE nome ILIKE ?",
                new BeanPropertyRowMapper<>(Musica.class),
                "%"+nome+"%"
        );

        return musicas;
    }

    public void deleteById(Integer id) {
        if(id ==null){
            return;
        }

        this.jdbcTemplate.update(
                "DELETE FROM musica WHERE id = ?",
                id
        );
    }

    public void save(Musica musica) {
        if(musica.getId() == null){
            // INSERT
            this.jdbcTemplate.update(
                    """
                            INSERT INTO musica VALUES (
                                DEFAULT,
                                ?,
                                ?,
                                ?,
                                ?
                            )
                            """,
                    musica.getNome(),
                    musica.getArtista(),
                    musica.getAlbum(),
                    musica.getDuracao()
            );

            return; // break
        }

         //UPDATE

        this.jdbcTemplate.update(
                """
                        UPDATE musica SET nome = ?, artista = ?, album = ?, duracao = ? WHERE id = ? 
                        """,
                musica.getNome(), musica.getArtista(), musica.getAlbum(), musica.getDuracao(), musica.getId()
        );

    }

    public List<Musica> findByAlbumAndNomeLike(String album, String nome) {
        List<Musica> musicas = this.jdbcTemplate.query("SELECT * FROM musica WHERE album ILIKE ? AND nome ILIKE ?",
                new BeanPropertyRowMapper<>(Musica.class),
                "%".concat(album).concat("%"),
                "%".concat(nome).concat("%"));

        return musicas;
    }

    public List<Musica> findByDuracaoGreaterThan(Integer duracao) {
        List<Musica> musicas = this.jdbcTemplate.query("SELECT * FROM musica WHERE duracao > ?",
                new BeanPropertyRowMapper<>(Musica.class),
                duracao);

        return musicas;
    }

    public List<Musica> findByAlbum(String album) {
        List<Musica> musicas = this.jdbcTemplate.query("SELECT * FROM musica WHERE album = ?",
                new BeanPropertyRowMapper<>(Musica.class),
                album);

        return musicas;
    }

    public List<Musica> findByArtista(String artista) {
        List<Musica> musicas = this.jdbcTemplate.query("SELECT * FROM musica WHERE artista = ?",
                new BeanPropertyRowMapper<>(Musica.class),
                artista);

        return musicas;
    }
}
