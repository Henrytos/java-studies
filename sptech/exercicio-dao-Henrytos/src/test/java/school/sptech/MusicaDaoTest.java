package school.sptech;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.junit5.api.DBRider;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import school.sptech.fixture.MusicaFixture;
import school.sptech.provider.DeleteByIdProvider;
import school.sptech.provider.FindByAlbumAndNomeLikeProvider;
import school.sptech.provider.FindByAlbumProvider;
import school.sptech.provider.FindByArtistaProvider;
import school.sptech.provider.FindByDuracaoGreaterThanProvider;
import school.sptech.provider.FindByIdProvider;
import school.sptech.provider.FindByNomeLikeProvider;

@DisplayName("MusicaDao")
@DBRider
@DBUnit(cacheConnection = false, alwaysCleanAfter = true, raiseExceptionOnCleanUp = true, user = "sa",
      password = "", url = "jdbc:h2:mem:db_musica", schema = "PUBLIC")
@DataSet(value = "datasets/input/musics.json", executeScriptsBefore = "schema.sql")
class MusicaDaoTest {

    ConexaoBanco conexaoBanco;

    @BeforeEach
    void setup() {
        conexaoBanco = new ConexaoBanco();
    }

    @AfterEach
    void finish() throws SQLException {
        conexaoBanco.getBasicDataSource().close();
    }

    @Nested
    @DisplayName("findAll()")
    class FindAllTest {

        @Test
        @DisplayName("Cenário 1 - 45 registros")
        @ExpectedDataSet(value = "datasets/input/musics.json")
        void testFindAll1() throws ReflectiveOperationException {
            JdbcTemplate template = conexaoBanco.getJdbcTemplate();

            Class<MusicaDao> clazz = MusicaDao.class;
            Method method = clazz.getDeclaredMethod("findAll");
            method.trySetAccessible();

            MusicaDao dao = new MusicaDao(template);

            List<?> result = (List<?>) method.invoke(dao);
            List<?> expected = MusicaFixture.all();

            Assertions.assertIterableEquals(expected, result);
        }

        @Test
        @DisplayName("Cenário 2 - 0 registros")
        @DataSet(value = "datasets/input/empty-musics.json", executeScriptsBefore = "schema.sql")
        @ExpectedDataSet(value = "datasets/input/empty-musics.json")
        void testFindAll2() throws ReflectiveOperationException {
            JdbcTemplate template = conexaoBanco.getJdbcTemplate();

            Class<MusicaDao> clazz = MusicaDao.class;
            Method method = clazz.getDeclaredMethod("findAll");
            method.trySetAccessible();

            MusicaDao dao = new MusicaDao(template);

            List<?> result = (List<?>) method.invoke(dao);
            List<?> expected = List.of();

            Assertions.assertIterableEquals(expected, result);
        }
    }

    @Nested
    @DisplayName("findById()")
    class FindByIdTest {

        @ParameterizedTest
        @DisplayName("Deve retornar a musica correta pelo id")
        @ArgumentsSource(FindByIdProvider.class)
        @ExpectedDataSet(value = "datasets/input/musics.json")
        void testFindById(Integer id, Musica expected)
              throws ReflectiveOperationException {
            JdbcTemplate template = conexaoBanco.getJdbcTemplate();

            Class<MusicaDao> clazz = MusicaDao.class;
            Method method = clazz.getDeclaredMethod("findById", Integer.class);
            method.trySetAccessible();

            MusicaDao dao = new MusicaDao(template);

            Object result = method.invoke(dao, id);

            assertEquals(expected, result);
        }
    }

    @Nested
    @DisplayName("findByNomeLike()")
    class FindByNomeLikeTest {

        @ParameterizedTest
        @DisplayName("Deve retornar as músicas que contém uma parte no nome")
        @ArgumentsSource(FindByNomeLikeProvider.class)
        @ExpectedDataSet(value = "datasets/input/musics.json")
        void testFindByNomeLike(String q, List<Musica> expected)
              throws ReflectiveOperationException {
            JdbcTemplate template = conexaoBanco.getJdbcTemplate();

            Class<MusicaDao> clazz = MusicaDao.class;
            Method method = clazz.getDeclaredMethod("findByNomeLike", String.class);
            method.trySetAccessible();

            MusicaDao dao = new MusicaDao(template);

            List<?> result = (List<?>) method.invoke(dao, q);

            Assertions.assertIterableEquals(expected, result);
        }
    }

    @Nested
    @DisplayName("findByArtista()")
    class FindByArtistaTest {

        @ParameterizedTest
        @DisplayName("Deve retornar as músicas de um determinado artista")
        @ArgumentsSource(FindByArtistaProvider.class)
        @ExpectedDataSet(value = "datasets/input/musics.json")
        void testFindArtista(String q, List<Musica> expected)
              throws ReflectiveOperationException {
            JdbcTemplate template = conexaoBanco.getJdbcTemplate();

            Class<MusicaDao> clazz = MusicaDao.class;
            Method method = clazz.getDeclaredMethod("findByArtista", String.class);
            method.trySetAccessible();

            MusicaDao dao = new MusicaDao(template);

            List<?> result = (List<?>) method.invoke(dao, q);

            Assertions.assertIterableEquals(expected, result);
        }
    }

    @Nested
    @DisplayName("findByAlbum()")
    class FindByAlbumTest {

        @ParameterizedTest
        @DisplayName("Deve retornar as músicas de um determinado album")
        @ArgumentsSource(FindByAlbumProvider.class)
        @ExpectedDataSet(value = "datasets/input/musics.json")
        void testFindByAlbum(String q, List<Musica> expected)
              throws ReflectiveOperationException {
            JdbcTemplate template = conexaoBanco.getJdbcTemplate();

            Class<MusicaDao> clazz = MusicaDao.class;
            Method method = clazz.getDeclaredMethod("findByAlbum", String.class);
            method.trySetAccessible();

            MusicaDao dao = new MusicaDao(template);

            List<?> result = (List<?>) method.invoke(dao, q);

            Assertions.assertIterableEquals(expected, result);
        }
    }

    @Nested
    @DisplayName("findByDuracaoGreaterThan()")
    class FindByDuracaoGreaterThanTest {

        @ParameterizedTest
        @DisplayName("Deve retornar as músicas com duração maior que a especificada")
        @ArgumentsSource(FindByDuracaoGreaterThanProvider.class)
        @ExpectedDataSet(value = "datasets/input/musics.json")
        void testFindByDuracaoGreaterThan(Integer duracao, List<Musica> expected)
              throws ReflectiveOperationException {
            JdbcTemplate template = conexaoBanco.getJdbcTemplate();

            Class<MusicaDao> clazz = MusicaDao.class;
            Method method = clazz.getDeclaredMethod("findByDuracaoGreaterThan", Integer.class);
            method.trySetAccessible();

            MusicaDao dao = new MusicaDao(template);

            List<?> result = (List<?>) method.invoke(dao, duracao);

            Assertions.assertIterableEquals(expected, result);
        }
    }

    @Nested
    @DisplayName("findByAlbumAndNomeLike()")
    class FindByAlbumAndNomeLikeTest {

        @ParameterizedTest
        @DisplayName("Deve retornar as músicas com o album e parte do nome especificados")
        @ArgumentsSource(FindByAlbumAndNomeLikeProvider.class)
        @ExpectedDataSet(value = "datasets/input/musics.json")
        void testFindByAlbumAndNomeLike(String album, String nome, List<Musica> expected)
              throws ReflectiveOperationException {
            JdbcTemplate template = conexaoBanco.getJdbcTemplate();

            Class<MusicaDao> clazz = MusicaDao.class;
            Method method = clazz.getDeclaredMethod("findByAlbumAndNomeLike", String.class,
                  String.class);
            method.trySetAccessible();

            MusicaDao dao = new MusicaDao(template);

            List<?> result = (List<?>) method.invoke(dao, album, nome);

            Assertions.assertIterableEquals(expected, result);
        }
    }

    @Nested
    @DisplayName("save()")
    class SaveTest {

        @Test
        @DisplayName("Cenário 1 - Inserindo música no banco")
        void save1() throws ReflectiveOperationException {
            JdbcTemplate template = conexaoBanco.getJdbcTemplate();

            Class<MusicaDao> clazz = MusicaDao.class;
            Method method = clazz.getDeclaredMethod("save", Musica.class);
            method.trySetAccessible();

            template.execute("ALTER TABLE musica ALTER COLUMN id RESTART WITH 46");

            MusicaDao dao = new MusicaDao(template);

            Musica novaMusica = new Musica(null, "Money", "Pink Floyd",
                  "The Dark Side of the Moon", 382);

            method.invoke(dao, novaMusica);

            Musica result = template.queryForObject("SELECT * FROM musica WHERE id = ?",
                  new BeanPropertyRowMapper<>(Musica.class), 46);

            Assertions.assertNotNull(result);

            Assertions.assertEquals(46, result.getId());
            Assertions.assertEquals(novaMusica.getNome(), result.getNome());
            Assertions.assertEquals(novaMusica.getArtista(), result.getArtista());
            Assertions.assertEquals(novaMusica.getAlbum(), result.getAlbum());
            Assertions.assertEquals(novaMusica.getDuracao(), result.getDuracao());
        }

        @Test
        @DisplayName("Cenário 2 - Inserindo música no banco vazio")
        @DataSet(value = "datasets/input/empty-musics.json", executeScriptsBefore = "schema.sql")
        void save2() throws ReflectiveOperationException {
            JdbcTemplate template = conexaoBanco.getJdbcTemplate();

            Class<MusicaDao> clazz = MusicaDao.class;
            Method method = clazz.getDeclaredMethod("save", Musica.class);
            method.trySetAccessible();

            MusicaDao dao = new MusicaDao(template);

            Musica novaMusica = new Musica(null, "Money", "Pink Floyd",
                  "The Dark Side of the Moon", 382);

            method.invoke(dao, novaMusica);

            Musica result = template.queryForObject("SELECT * FROM musica WHERE id = ?",
                  new BeanPropertyRowMapper<>(Musica.class), 1);

            Assertions.assertNotNull(result);

            Assertions.assertEquals(1, result.getId());
            Assertions.assertEquals(novaMusica.getNome(), result.getNome());
            Assertions.assertEquals(novaMusica.getArtista(), result.getArtista());
            Assertions.assertEquals(novaMusica.getAlbum(), result.getAlbum());
            Assertions.assertEquals(novaMusica.getDuracao(), result.getDuracao());
        }

        @Test
        @DisplayName("Cenário 3 - Deve atualizar uma música")
        void save3() throws ReflectiveOperationException {
            JdbcTemplate template = conexaoBanco.getJdbcTemplate();

            Class<MusicaDao> clazz = MusicaDao.class;
            Method method = clazz.getDeclaredMethod("save", Musica.class);
            method.trySetAccessible();

            MusicaDao dao = new MusicaDao(template);

            Musica novaMusica = new Musica(5, "Money", "Pink Floyd",
                  "The Dark Side of the Moon", 382);

            method.invoke(dao, novaMusica);

            Musica result = template.queryForObject("SELECT * FROM musica WHERE id = ?",
                  new BeanPropertyRowMapper<>(Musica.class), 5);

            Assertions.assertNotNull(result);

            Assertions.assertEquals(novaMusica.getId(), result.getId());
            Assertions.assertEquals(novaMusica.getNome(), result.getNome());
            Assertions.assertEquals(novaMusica.getArtista(), result.getArtista());
            Assertions.assertEquals(novaMusica.getAlbum(), result.getAlbum());
            Assertions.assertEquals(novaMusica.getDuracao(), result.getDuracao());
        }
    }

    @Nested
    @DisplayName("deleteById()")
    class DeleteByIdTest {

        @ParameterizedTest
        @DisplayName("Deve deletar a música com id especificado")
        @ArgumentsSource(DeleteByIdProvider.class)
        void deleteById1(Integer id) throws ReflectiveOperationException {
            JdbcTemplate template = conexaoBanco.getJdbcTemplate();

            Class<MusicaDao> clazz = MusicaDao.class;
            Method method = clazz.getDeclaredMethod("deleteById", Integer.class);
            method.trySetAccessible();

            MusicaDao dao = new MusicaDao(template);

            method.invoke(dao, id);

            Integer result = template.queryForObject("SELECT COUNT(*) FROM musica WHERE id = ?",
                  Integer.class, id);

            assertEquals(0, result);
        }
    }
}
