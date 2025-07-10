import java.time.LocalDate;
import java.util.UUID;

public class Autor extends Pessoa {

    public Autor(
            UUID id,
            String nome,
            LocalDate dataNascimento) {

        super(id, nome, dataNascimento);
    }

}
